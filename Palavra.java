import java.util.ArrayList;
import java.util.List;



public class Palavra {
    private String palavra;
    private String significado;
    private List<Palavra> filhos;
    

    public Palavra(String palavra, String significado) {
        this.palavra = palavra;
        this.significado = significado;
        this.filhos = new ArrayList<>();
    }

    public String getPalavra() {
        return palavra;
    }

    public String getSignificado() {
        return significado;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public List<Palavra> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<Palavra> filhos) {
        this.filhos = filhos;
    }

    @Override
    public String toString() {
        return palavra + ": " + significado + "\n";
    }

    
}
