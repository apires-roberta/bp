package betterplace.betterplacebcd.services.dashboard;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService implements  IDashboardService{
    @Autowired
    CampanhaRepository _campanhaRepository;
    @Autowired
    DoacoesRepository _doacoesRepository;
    @Override
    public Integer getMaiorCampanha(String diaInicio, String diaFinal, Integer idOng){
        Integer idCampanha = _campanhaRepository.getIdCampanha(diaInicio, diaFinal, idOng);
        if(idCampanha==null){
            return null;
        }
        return idCampanha;
    }

    @Override
    public List<Object> getDoacoesDia(String diaInicio, String diaFinal, Integer idCampanha){
        List<Object> doacoes = _doacoesRepository.getDoacoesDia(diaInicio, diaFinal, idCampanha);
        if(doacoes==null){
            return null;
        }
        return doacoes;
    }

    @Override
    public List<Object> getDoacoesMes(String d1, String d2, String d3, String d4, String d5, String d6, String d7, String d8, String d9, String d10, Integer idCampanha){
        List<Object> semana = _doacoesRepository.getDoacoesMes(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10, idCampanha);
        if(semana==null){
            return null;
        }
        return semana;
    }

    @Override
    public List<Object> getDoacoesValor(Integer idCampanha){
        List<Object> valor = _doacoesRepository.getDoacoesValor(idCampanha);
        if(valor==null){
            return null;
        }
        return valor;
    }

    @Override
    public List<Object> getProcedure(String estado){
        List<Object> dadosEstado = _doacoesRepository.getProcedure(estado);
        if(dadosEstado==null){
            return null;
        }
        return dadosEstado;
    }

}
