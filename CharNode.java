import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CharNode {
    private char character;
    private String significado;
    private CharNode father;
    private List<CharNode> subtrees;

    public CharNode(char element, String significado) {
        this.father = null;
        this.character = element;
        this.significado = significado;
        this.subtrees = new ArrayList<>();
    }

    public CharNode addChild(String word, String significado) {
    if (word.length() == 0) {
        return this.father;
    }

    char primeiraLetra = word.charAt(0);
    CharNode childNode = null;

    for (CharNode node : subtrees) {
        if (node.character == primeiraLetra) {
            childNode = node;
            break;
        }
    }

    if (childNode == null) {
        childNode = new CharNode(primeiraLetra, word.length() == 1 ? significado : "");
        subtrees.add(childNode);
    }

    return childNode.addChild(word.substring(1), significado);
}

public void printTree(String word) {
    String currentWord = word + this.character; // Atualiza a palavra atual com o caractere atual

    if (!significado.isEmpty()) {
        System.out.println(currentWord + " - significado: " + significado);
    }

    for (CharNode node : subtrees) {
        node.printTree(currentWord); // Passa a palavra atualizada para a chamada recursiva
    }
}


    public void printTreeWithoutSignificado(String word) {
        if (this.character != '.') {
            word += this.character;
        }
        if (!significado.isEmpty()) {
            System.out.println(word);
        }

        for (CharNode node : subtrees) {
            node.printTreeWithoutSignificado(word);
        }
    }

    public void printNodes(int nivel) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= nivel; i++) {
            builder.append("-");
        }
        String nivelString = builder.toString();

        System.out.print(nivel + nivelString + this.character);
        if (!significado.isEmpty()) {
            System.out.println(" significado: " + significado);
        } else {
            System.out.println();
        }

        nivel++;

        for (CharNode node : subtrees) {
            node.printNodes(nivel);
        }
    }

    public CharNode findPrefix(String word) {
        if (word.isEmpty()) {
            return this;
        }

        for (CharNode node : subtrees) {
            if (node.character == word.charAt(0)) {
                return node.findPrefix(word.substring(1));
            }
        }

        System.out.println("Prefixo não existe no dicionário");
        return null;
    }

    public void findSingleWord(String word, String conc) {
        if (word.isEmpty() && !significado.isEmpty()) {
            System.out.println(conc + ", significado: " + significado);
            return;
        }

        for (CharNode node : subtrees) {
            if (node.character == word.charAt(0)) {
                conc += word.charAt(0);
                node.findSingleWord(word.substring(1), conc);
                return;
            }
        }

        System.out.println("Palavra não encontrada");
    }

    public void ordenaAlfabetica() {
        subtrees.sort(Comparator.comparing(node -> node.character));

        for (CharNode node : subtrees) {
            node.ordenaAlfabetica();
        }
    }

    public static Comparator<CharNode> compareCharacters = new Comparator<CharNode>() {
        public int compare(CharNode a, CharNode b) {
            return Character.compare(a.character, b.character);
        }
    };
}
