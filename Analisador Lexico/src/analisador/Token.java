package analisador;
/**
 *
 * @author Frank Brown
 */
public class Token {
    
    public String palavra;
    public String classe;
    public int linha;
    

    public Token(String palavra, String classe, int linha) {
        this.palavra = palavra;
        this.classe = classe;
        this.linha = linha;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
    
    
    
}
