package betterplace.betterplacebcd.data.enums;

public enum TipoCampanha {
    FOME(1), SAUDE(2), ROUPA(3), OUTROS(4);

    private final Integer idTipoCampanha;
    TipoCampanha(Integer valorOpcao){
        idTipoCampanha = valorOpcao;
    }

    public Integer getIdTipoCampanha(){
        return idTipoCampanha;
    }
}