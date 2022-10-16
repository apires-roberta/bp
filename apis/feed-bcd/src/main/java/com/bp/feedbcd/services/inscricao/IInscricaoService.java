package com.bp.feedbcd.services.inscricao;

import com.bp.feedbcd.data.dto.inscricao.CreateInscricaoDto;
import com.bp.feedbcd.data.dto.usuario.ReadUsuarioDto;

import java.util.List;

public interface IInscricaoService {

    Boolean createInscricao(CreateInscricaoDto novaInscricao);

    List<ReadUsuarioDto> getInscritosOng(Integer idOng);

    List<Integer> getInscricoesDoador(Integer idDoador);

    Boolean deleteInscricao(CreateInscricaoDto delInscricao);
}
