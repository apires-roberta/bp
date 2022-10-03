package betterplace.betterplacebcd.services.campanha;

import betterplace.betterplacebcd.data.dto.campanha.CreateCampanhaDto;
import betterplace.betterplacebcd.data.dto.campanha.ReadCampanhaDto;

import java.util.List;

public interface ICampanhaService {
    void postCampanha(CreateCampanhaDto novaCampanha);
    List<ReadCampanhaDto> getAllCampanhas();
    void alterarValor(Integer cod, Double valorNovo);
    void apagarCampanha(Integer cod);
    List<ReadCampanhaDto> getCampanhaByFkOng(Integer fkOng);

    ReadCampanhaDto getCampanhaById(Integer cod);

    void alterarDisponibilidadeCampanha(Integer idCampanha);

    List<ReadCampanhaDto> getRecomendacoesByIdCampanha(int idCampanha);
}
