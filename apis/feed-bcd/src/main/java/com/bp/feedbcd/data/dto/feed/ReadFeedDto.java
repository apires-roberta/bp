package com.bp.feedbcd.data.dto.feed;

import com.bp.feedbcd.entidade.Ong;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ReadFeedDto {
    private Long codigo;
    private Ong ong;
    private LocalDateTime dataPublicacao;
    @NotNull
    @NotBlank
    private String descricao;
    @Column(length = 50_000_000)
    private byte[] fotoFeed;
    @Column(length = 50_000_000)
    private byte[] fotoPerfilOng;
}