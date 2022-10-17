package betterplace.betterplacebcd.classes.ed;
public class HashTable {
    ListaLigada[] tabela;

    public HashTable(int tamanho) {
        this.tabela = new ListaLigada[tamanho];

        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaLigada();
        }
    }

    public int funcaoHash(int num){
        return num % tabela.length; //Usar o lenght pra não faltar nem sobrar colunas
    }

    public void insere (int num){
        tabela[funcaoHash(num)].insereNode(num);
    }

    public void insere(Integer idCampanha, int idTipoCampanha){
        tabela[funcaoHash(idTipoCampanha)].insereNode(idCampanha);
    }

    public boolean existe(int num){
        return tabela[funcaoHash(num)].buscaNode(num) == null ? false : true;
    }

    public boolean remove (int num){
        return tabela[funcaoHash(num)].removeNode(num);
    }

    public void exibe() {
        for (int i = 0; i < tabela.length; i++) {
            System.out.println("Lista " + (i+1));
            tabela[i].exibe();
        }
    }

    public Node busca(int idObj) {
        return tabela[funcaoHash(idObj)].getHead().getNext();
    }
}