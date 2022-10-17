package com.bp.feedbcd.services.inscricao;

import com.bp.feedbcd.data.dto.inscricao.CreateInscricaoDto;
import com.bp.feedbcd.data.dto.usuario.ReadUsuarioDto;
import com.bp.feedbcd.entidade.Doador;
import com.bp.feedbcd.entidade.Inscricao;
import com.bp.feedbcd.entidade.Ong;
import com.bp.feedbcd.repository.InscricaoRepository;
import com.bp.feedbcd.servicesreferences.IDoadorService;
import com.bp.feedbcd.servicesreferences.IOngService;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InscricaoService implements IInscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private IOngService _ongService;
    @Autowired
    private IDoadorService _doadorService;
    @Autowired
    ModelMapper _mapper;

    @Override
    public Boolean createInscricao(CreateInscricaoDto novaInscricao) {
        try {
            if (inscricaoRepository.existsByOngCodAndDoadorCod(novaInscricao.getFkOng(), novaInscricao.getFkDoador()))
                return false;

            ReadUsuarioDto ong = verificaOngExiste(novaInscricao.getFkOng());
            ReadUsuarioDto doador = verificaDoadorExiste(novaInscricao.getFkDoador());

            Inscricao inscricao = new Inscricao(_mapper.map(ong, Ong.class), _mapper.map(doador, Doador.class));
            inscricaoRepository.save(inscricao);
            return true;
        }catch (FeignException.NotFound notFound){
            return null;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<ReadUsuarioDto> getInscritosOng(Integer idOng) {
        try {
            _ongService.getOngById(idOng); //Apenas para ver se a Ong existe, se não existir vai lançar a exceção NotFound
            List<Inscricao> inscricoesOng = inscricaoRepository.findByOngCod(idOng);

            List<ReadUsuarioDto> doadores = new ArrayList<>();
            for (Inscricao inscricao : inscricoesOng) {
                doadores.add(_mapper.map(inscricao.getDoador(), ReadUsuarioDto.class));
            }
            return doadores;
        }catch (FeignException.NotFound notFound){
            return null;
        }
    }

    @Override
    public List<Integer> getInscricoesDoador(Integer idDoador) {
        try {
            verificaDoadorExiste(idDoador);
            List<Integer> inscricoes = inscricaoRepository.findByDoadorCod(idDoador);

            return inscricoes;
        }catch (FeignException.NotFound notFound){
            return null;
        }
    }

    @Override
    public void deleteInscricao(CreateInscricaoDto delInscricao) {
        try {
            Inscricao inscricao = inscricaoRepository.findByOngCodAndDoadorCod(delInscricao.getFkOng(), delInscricao.getFkDoador());
            if (inscricao == null)
                throw new IllegalArgumentException();

            inscricaoRepository.delete(inscricao);
        }catch (IllegalArgumentException notFound){
            throw notFound;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public void existeInscricao(CreateInscricaoDto existeInscricao) {
        try {
            Inscricao inscricao = inscricaoRepository.findByOngCodAndDoadorCod(existeInscricao.getFkOng(), existeInscricao.getFkDoador());
            if (inscricao == null)
                throw new IllegalArgumentException();
        }catch (IllegalArgumentException notFound){
            throw notFound;
        }catch (Exception ex){
            throw ex;
        }
    }

    private ReadUsuarioDto verificaDoadorExiste(Integer idDoador) {
        return _doadorService.getUsuarioById(idDoador);
    }
    private ReadUsuarioDto verificaOngExiste(Integer idOng) {
        return _ongService.getOngById(idOng);
    }
}