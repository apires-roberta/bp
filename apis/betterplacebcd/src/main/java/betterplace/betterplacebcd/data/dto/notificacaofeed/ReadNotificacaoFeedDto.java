package betterplace.betterplacebcd.data.dto.notificacaofeed;

import javax.validation.constraints.NotNull;

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