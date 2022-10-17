package com.bp.feedbcd.data.dto.inscricao;

public class CreateInscricaoDto {
    private Integer fkOng;
    private Integer fkDoador;

    public CreateInscricaoDto() {
    }

    public CreateInscricaoDto(Integer fkOng, Integer fkDoador) {
        this.fkOng = fkOng;
        this.fkDoador = fkDoador;
    }

    public Integer getFkOng() {
        return fkOng;
    }

    public Integer getFkDoador() {
        return fkDoador;
    }

}
