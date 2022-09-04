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

            ReadUsuarioDto ong = _ongService.getOngById(novaInscricao.getFkOng());
            ReadUsuarioDto doador = _doadorService.getUsuarioById(novaInscricao.getFkDoador());

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
}