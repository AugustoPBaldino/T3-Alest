import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeDictionary {
    private WordTree raiz;

    public void construirArvore(String arquivo) {
        // Abrir o arquivo e ler as palavras e seus significados
        List<Palavra> palavras = lerArquivoPalavras(arquivo);

        // Construir a árvore a partir das palavras
        raiz = new WordTree(); // Criar o nó raiz vazio

        for (Palavra palavra : palavras) {
            inserirPalavra(palavra);
        }
    }

    private List<Palavra> lerArquivoPalavras(String arquivo) {
        List<Palavra> palavras = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(arquivo));
            scanner.useDelimiter(";");

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");

                if (partes.length == 2) {
                    String palavra = partes[0].trim();
                    String significado = partes[1].trim();
                    palavras.add(new Palavra(palavra, significado));
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return palavras;
    }

    private void inserirPalavra(Palavra palavra) {
        WordTree noAtual = raiz;

        for (int i = 0; i < palavra.getPalavra().length(); i++) {
            char caractere = palavra.getPalavra().charAt(i);
            Palavra filhoEncontrado = null;

            // Procurar se o caractere já possui um nó filho
            for (Palavra filho : noAtual.getFilhos()) {
                if (filho.getPalavra().getPalavra().equals(String.valueOf(caractere))) {
                    filhoEncontrado = filho;
                    break;
                }
            }

            if (filhoEncontrado != null) {
                // O caractere já possui um nó filho, avançar para o próximo nível
                noAtual = filhoEncontrado;
            } else {
                // O caractere não possui um nó filho, criar um novo nó
                Palavra novoFilho = new Palavra("", "");
                noAtual.getFilhos().add(novoFilho);
                noAtual = novoFilho;
            }
        }

        // Adicionar a palavra completa no último nível da árvore
        noAtual.setPalavra(palavra);
    }

    public List<Palavra> buscarPalavras(String prefixo) {
        List<Palavra> palavrasEncontradas = new ArrayList<>();

        // Encontrar o nó correspondente ao prefixo na árvore
        Palavra noAtual = raiz;

        for (int i = 0; i < prefixo.length(); i++) {
            char caractere = prefixo.charAt(i);
            Palavra filhoEncontrado = null;

            for (Palavra filho : noAtual.getFilhos()) {
                if (filho.getPalavra().getPalavra().equals(String.valueOf(caractere))) {
                    filhoEncontrado = filho;
                    break;
                }
            }

            if (filhoEncontrado != null) {
                noAtual = filhoEncontrado;
            } else {
                // O prefixo não existe na árvore, retornar lista vazia
                return palavrasEncontradas;
            }
        }

        // Percorrer todos os nós abaixo do nó encontrado e adicionar as palavras na lista
        percorrerSubarvore(noAtual, palavrasEncontradas);

        return palavrasEncontradas;
    }

    private void percorrerSubarvore(Palavra noAtual, List<Palavra> palavrasEncontradas) {
        if (noAtual.getPalavra().getPalavra().length() > 0) {
            palavrasEncontradas.add(noAtual.getPalavra());
        }

        for (Palavra filho : noAtual.getFilhos()) {
            percorrerSubarvore(filho, palavrasEncontradas);
        }
    }
}
