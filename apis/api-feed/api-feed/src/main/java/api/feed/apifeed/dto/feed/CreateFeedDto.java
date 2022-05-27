package api.feed.apifeed.dto.feed;
import java.time.LocalDateTime;

public class CreateFeedDto {
    private Long codigo;

    private String descricao;

    private LocalDateTime dataPublicacao;

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }
}