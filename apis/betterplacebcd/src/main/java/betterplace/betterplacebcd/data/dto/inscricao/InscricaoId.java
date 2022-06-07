package betterplace.betterplacebcd.data.dto.inscricao;

import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.entidade.Ong;

import java.io.Serializable;
import java.util.Objects;

public class InscricaoId implements Serializable {
    //Classe que vai ser de ID no inscricaoRepository pq ela tem as chaves compostas
    private Ong ong;
    private Doador doador;

    public InscricaoId() {
    }

    public InscricaoId(Ong ong, Doador doador) {
        this.ong = ong;
        this.doador = doador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscricaoId that = (InscricaoId) o;
        return ong.equals(that.ong) && doador.equals(that.doador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ong, doador);
    }
}
