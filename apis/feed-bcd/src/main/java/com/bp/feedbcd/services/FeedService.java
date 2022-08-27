package com.bp.feedbcd.services;

import com.bp.feedbcd.data.dto.feed.CreateFeedDto;
import com.bp.feedbcd.data.dto.usuario.ReadUsuarioDto;
import com.bp.feedbcd.entidade.Feed;
import com.bp.feedbcd.entidade.Ong;
import com.bp.feedbcd.repository.FeedRepository;
import com.bp.feedbcd.servicesreferences.IOngService;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService implements IFeedService{
    @Autowired
    private FeedRepository repository;
    @Autowired
    private IOngService _ongService;
    @Autowired
    private ModelMapper _mapper;

    @Override
    public List<Feed> getFeeds() {
        List<Feed> feeds = repository.findAllByOrderByDataPublicacaoDesc();
        if (feeds.isEmpty())
            return null;

        return feeds;
    }

    @Override
    public Long createFeed(CreateFeedDto novoFeed) {
        try {
            ReadUsuarioDto usuarioDto = _ongService.getOngById(novoFeed.getIdOng());

            if (usuarioDto == null)
                return null;

            Feed feed = new Feed(_mapper.map(usuarioDto, Ong.class), novoFeed.getDescricao());
            repository.save(feed);
            return feed.getCodigo();
        }catch (FeignException.NotFound notFound){
            return null;
        }
    }

    @Override
    public boolean atualizarFotosFeed(byte[] fotoFeed, Long idFeed) {
        Feed novoFeed = repository.findByCodigo(idFeed);
        ReadUsuarioDto ong = _ongService.getOngById(novoFeed.getOng().getCod());
        if (novoFeed == null || ong == null)
            return false;

        novoFeed.setFotoFeed(fotoFeed);

        novoFeed.setFotoPerfilOng(ong.getFotoPerfil());
        repository.save(novoFeed);
        return true;
    }

    @Override
    public boolean deleteFeed(long idFeed) {
        if (!repository.existsById(idFeed))
            return false;

        repository.deleteById(idFeed);
        return true;
    }
}