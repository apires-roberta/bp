package betterplace.betterplacebcd.services.doacao;

import betterplace.betterplacebcd.data.dto.doacao.CreateDoacaoDto;
import betterplace.betterplacebcd.data.dto.doacao.ReadDoacaoDto;

import java.util.List;

public interface IDoacaoService {
    void doar (CreateDoacaoDto doacaoDto);

    List<ReadDoacaoDto> getDoacoesByIdCampanha(Integer idCampanha);
}
