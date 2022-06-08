package betterplace.betterplacebcd.entidade;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Doador extends Usuario {
    @CPF @NotNull
    private String cpf;
    @ManyToMany
    @JoinTable (name = "Inscricao", joinColumns = @JoinColumn (name = "doador_cod"),
            inverseJoinColumns = @JoinColumn(name = "ong_cod"))
    private List<Ong> ongs;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

//    public List<Inscricao> getInscricao() {
//        return inscricao;
//    }
//
//    public void setInscricao(List<Inscricao> inscricao) {
//        this.inscricao = inscricao;
//    }

    public List<Ong> getOngs() {
        return ongs;
    }

    public void setOngs(List<Ong> ongs) {
        this.ongs = ongs;
    }
}