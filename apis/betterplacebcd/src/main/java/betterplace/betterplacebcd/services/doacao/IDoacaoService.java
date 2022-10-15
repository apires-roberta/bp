package betterplace.betterplacebcd.services.doacao;

import betterplace.betterplacebcd.data.dto.doacao.CreateDoacaoDto;
import betterplace.betterplacebcd.data.dto.doacao.ReadDoacaoDto;

import java.util.List;

public interface IDoacaoService {
    Integer doar (CreateDoacaoDto doacaoDto);

    List<ReadDoacaoDto> getDoacoesByIdCampanha(Integer idCampanha);

    ReadDoacaoDto getDoacaoByIdDoacao(Integer idDoacao);

    ReadDoacaoDto getUltimaDoacaoDoadorCampanha(Integer idDoador, Integer idCampanha);

    Double getTotalRecebidoOng(Integer idOng);
}
