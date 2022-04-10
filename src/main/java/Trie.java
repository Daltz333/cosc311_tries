import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode rootNode;

    // function that adds words and creates the tree
    // this should set the root node if necessary
    public void addWord() {
    }

    // function that searches through the tree and returns possible matches
    // I recommend implementing this by descending from the root node
    // and going through the children until there are no more char matches
    public String search() {
        return "";
    }

    // this class represents a character or string in the Trie tree
    // it should contain pointers to it's children and a boolean on
    // if it can make a word complete=
    public static class TrieNode {
        public boolean isWord;
        public String value;
        public ArrayList<TrieNode> children;

        // can pass a new arraylist to store the children for each node
        public TrieNode(boolean isWord, String value, ArrayList<TrieNode> children) {
            this.isWord = isWord;
            this.value = value;
            this.children = children;
        }

        // adds a TrieNode child to the current node
        // useful function for adding children
        public void addChild(TrieNode child) {
            this.children.add(child);
        }

        // removes child from children if exists
        public void removeChild(TrieNode child) {
            this.children.remove(child);
        }
    }
}
