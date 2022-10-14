package com.bp.feedbcd.services.feed;

import com.bp.feedbcd.data.dto.feed.CreateFeedDto;
import com.bp.feedbcd.data.dto.feed.ReadFeedDto;
import com.bp.feedbcd.data.dto.usuario.ReadUsuarioDto;
import com.bp.feedbcd.entidade.Feed;
import com.bp.feedbcd.entidade.Ong;
import com.bp.feedbcd.repository.FeedRepository;
import com.bp.feedbcd.services.notificacao.INotificacaoService;
import com.bp.feedbcd.servicesreferences.IOngService;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedService implements IFeedService{
    @Autowired
    private FeedRepository _repository;
    @Autowired
    private IOngService _ongService;
    @Autowired
    private INotificacaoService _notificacaoService;
    @Autowired
    private ModelMapper _mapper;

    @Override
    public List<ReadFeedDto> getFeeds() {
        List<Feed> feeds = _repository.findAllByOrderByDataPublicacaoDesc();
        List<ReadFeedDto> feedsDto = new ArrayList<>();

        if (feeds.isEmpty())
            return feedsDto;

        for (Feed feed : feeds) {
            feedsDto.add(_mapper.map(feed, ReadFeedDto.class));
        }

        return feedsDto;
    }

    @Override
    public Long createFeed(CreateFeedDto novoFeed) {
        try {
            ReadUsuarioDto usuarioDto = _ongService.getOngById(novoFeed.getIdOng());

            if (usuarioDto == null)
                return null;

            Feed feed = new Feed(_mapper.map(usuarioDto, Ong.class), novoFeed.getDescricao());
            _repository.save(feed);
            _notificacaoService.createNotificacao(usuarioDto.getCod());
            return feed.getCodigo();
        }catch (FeignException.NotFound notFound){
            return null;
        }
    }

    @Override
    public boolean atualizarFotosFeed(byte[] fotoFeed, Long idFeed) {
        Feed novoFeed = _repository.findByCodigo(idFeed);
        if (novoFeed == null)
            return false;

        novoFeed.setFotoFeed(fotoFeed);
        _repository.save(novoFeed);
        return true;
    }

    @Override
    public boolean deleteFeed(long idFeed) {
        if (!_repository.existsById(idFeed))
            return false;

        _repository.deleteById(idFeed);
        return true;
    }

    @Override
    public List<ReadFeedDto> getFeedsByIdOng(Integer idOng) {
        verificaOngExiste(idOng);
        
        List<Feed> feeds = _repository.findByOngCod(idOng);
        List<ReadFeedDto> feedsDto = new ArrayList<>();
        if (feeds.isEmpty())
            return feedsDto;

        for (Feed feed : feeds) {
            feedsDto.add(_mapper.map(feed, ReadFeedDto.class));
        }

        return feedsDto;
    }

    private void verificaOngExiste(Integer idOng) {
        _ongService.getOngById(idOng);
    }
}