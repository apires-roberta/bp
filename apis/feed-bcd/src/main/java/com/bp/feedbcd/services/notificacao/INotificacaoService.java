package com.bp.feedbcd.services.notificacao;

import java.util.List;

public interface INotificacaoService {
    void createNotificacao(Integer idOng);
    List<Object> get10Notificacoes(Integer idDoador);
    void deleteNotificacao();
    void redoNotificacao();

}
