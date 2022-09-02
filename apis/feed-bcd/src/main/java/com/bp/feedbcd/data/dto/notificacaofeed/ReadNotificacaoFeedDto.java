package com.bp.feedbcd.data.dto.notificacaofeed;

public class ReadNotificacaoFeedDto {
    private Integer id;
    private String nomeOng;

    public ReadNotificacaoFeedDto(Integer id, String nomeOng) {
        this.id = id;
        this.nomeOng = nomeOng;
    }

    public ReadNotificacaoFeedDto() {
    }

    public Integer getId() {
        return id;
    }
    public String getNomeOng() {
        return nomeOng;
    }
}