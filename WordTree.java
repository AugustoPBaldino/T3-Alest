// 4645G-04 - Algoritmos e Estruturas de Dados I
// 2023-1

import java.util.ArrayList;
import java.util.List;

public class WordTree {
    
    // Classe interna
    public class CharNode {
        private char character;
	    private String significado;
        private boolean isFinal;
        private CharNode father;
        private List<CharNode> children;

        public CharNode(char character) {
        
        this.character = character;
        this.significado = "";
        this.isFinal = false;
        this.father = null;
        this.children = new ArrayList<>();
        }
        
        public CharNode(char character, boolean isFinal) {
        this.character = character;
        this.isFinal = isFinal;
        this.children = new ArrayList<>();
    }

        /**
        * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
        * @param character - caracter a ser adicionado
        * @param isfinal - se é final da palavra ou não
        */
        public CharNode addChild (char character, boolean isfinal) {
            CharNode child = findChildChar(character);
        if (child == null) {
            child = new CharNode(character, isFinal);
            children.add(child);
        } else {
            child.isFinal = isFinal;
        }
        return child;
        }
        
        public int getNumberOfChildren () {
            return children.size();
        }
        
        public CharNode getChild (int index) {
            if (index >= 0 && index < children.size()) {
                return children.get(index);
            }
            return null;
        }

        /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
         * @return a palavra
         */
        private String getWord() {
            StringBuilder wordBuilder = new StringBuilder();
            CharNode currentNode = this;

            while (currentNode != null) {
                wordBuilder.insert(0, currentNode.character);
                currentNode = currentNode.father;
            }

            return wordBuilder.toString();
        }
        
        /**
        * Encontra e retorna o nodo que tem determinado caracter.
        * @param character - caracter a ser encontrado.
        */
        public CharNode findChildChar (char character) {
            for (CharNode child : children) {
            if (child.character == character) {
                return child;
            }
        }
        return null;
        }
        
    }


    
    // Atributos
    private CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;
    


    // Construtor
    public WordTree() {
      //...
    }


    
    // Metodos
    public int getTotalWords() {
        //...
    }

    public int getTotalNodes() {
        //...
    }
    

    
    
    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * @param word
     * @return o nodo final encontrado
     */
    private CharNode findCharNodeForWord(String word) {
        //...
    }

    /**
    * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
    * Tipicamente, um método recursivo.
    * @param prefix
    */
    public List<String> searchAll(String prefix) {
        //...
    }   

}
