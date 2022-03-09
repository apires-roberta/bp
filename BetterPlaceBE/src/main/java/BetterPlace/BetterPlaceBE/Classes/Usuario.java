package BetterPlace.BetterPlaceBE.Classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    protected String nome;
    protected String email;
    protected String senha;
    protected String telefone;
    protected String usuario;
    protected Boolean logado;
    protected List<Usuario> listaUsuario = new ArrayList();

    public String autenticar(String usuario, String senha){
        String retorno =String.format("Usuário %s não encontrado", usuario);
        for (Usuario usuarios:listaUsuario){
            if(usuarios.usuario.equalsIgnoreCase(usuario) && usuarios.senha.equalsIgnoreCase(senha)){
                usuarios.logado = true;
                retorno = String.format("Usuário %s agora está autenticado", usuarios.nome);
            }
        }
        return retorno;
    }

    public String desautenticar(String usuario) {
        String retorno = String.format("Usuário %s não encontrado", usuario);
        for (Usuario usuarios : listaUsuario) {
            if (usuarios.usuario.equalsIgnoreCase(usuario)) {
                if (usuarios.logado) {
                    usuarios.logado = false;
                    retorno = String.format("Logoff do usuário %s concluído", usuarios.nome);
                } else {
                    retorno = String.format("Usuário %s NÃO está autenticado", usuarios.nome);
                }
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", usuario='" + usuario + '\'' +
                ", logado=" + logado +
                '}';
    }

    public String mostrarGeral(){
        String mostrar = "";
        for (Usuario usu: listaUsuario){
            mostrar+=usu.toString();
        }
        return mostrar;
    }
}
