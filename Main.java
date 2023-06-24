import java.util.List;

public class Main {
    public static void main(String[] args) {
        TreeDictionary treeDictionary = new TreeDictionary();
        treeDictionary.construirArvore("dicionario.csv");

        String prefixo = "ab";
        List<Palavra> palavrasEncontradas = treeDictionary.buscarPalavras(prefixo);

        if (palavrasEncontradas.isEmpty()) {
            System.out.println("Nenhuma palavra encontrada com o prefixo: " + prefixo);
        } else {
            System.out.println("Palavras encontradas com o prefixo " + prefixo + ":");
            for (Palavra palavra : palavrasEncontradas) {
                System.out.println(palavra.getPalavra() + " - " + palavra.getSignificado());
            }
        }
    }
}
