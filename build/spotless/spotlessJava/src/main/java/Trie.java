import java.util.ArrayList;

public class Trie {
  private TrieNode rootNode;

  // function that adds words and creates the tree
  // this should set the root node if necessary
  public void addWord(String word) {
    // Initializes rootNode. null rootNodes won't work properly. (NullPointer)
    // Maybe we can initialize when we create the Trie instead?
    if (rootNode == null) rootNode = new TrieNode(false, "*");

    // Descend the Trie starting from rootNode
    TrieNode current = rootNode;
    for (int i = 0; i < word.length(); i++) {
      String character = "" + word.charAt(i);
      // This will be the Node we are trying to add
      TrieNode next = new TrieNode(false, character);

      // This is the current node's list of children.
      // Used to check if this letter already exists.
      ArrayList<TrieNode> children = current.getChildren(current);
      boolean childFound = false;

      // If this is the last character of the word, the node we add needs to be isWord=true
      if (i == word.length() - 1) {
        current.addChild(new TrieNode(true, character));
      }

      // loop checking for existing character value amongst children.
      for (int j = 0; j < children.size(); j++) {
        if (children.get(j).value.equals(character)) {
          current = children.get(j);
          childFound = true;
          break;
        }
      }

      // If the current character does not exist as a child, add it and continue through word.
      if (childFound == false) {
        current.addChild(next);
        current = next;
      }
    }
  }

  // function that searches through the tree and returns possible matches
  public String search(String word) {
    int depth = -1;

    // Descend the Trie starting from rootNode
    TrieNode current = rootNode;

    // boolean that stops searching if no matching child value exists
    boolean breakPath = false;

    // progress through word one character at a time
    for (int i = 0; i < word.length(); i++) {
      String character = "" + word.charAt(i);
      // Gather all children of current node to check for matching values
      ArrayList<TrieNode> children = current.getChildren(current);

      // search children for equivalent node with equivalent String
      for (int j = 0; j < children.size(); j++) {
        // if we find matching child, that child is our next node
        if (children.get(j).value.equals(character)) {
          current = children.get(j);
          depth++;
          break;
        }
        // breakPath becomes true if we have exhausted all available children
        if (j == children.size() - 1) breakPath = true;
      }
      // no way to progress further -> stop searching!
      if (breakPath) break;
      // if final character happens to be a word...
      if (current.isWord && i == word.length() - 1 && i == depth) {
        return "Word found: " + word;
      }
    }
    return "Invalid word/not currently present.";
  }

  // this class represents a character or string in the Trie tree
  // it should contain pointers to it's children and a boolean on
  // if it can make a word complete=
  public static class TrieNode {
    public boolean isWord;
    public String value;
    public ArrayList<TrieNode> children = new ArrayList(1);

    // can pass a new arraylist to store the children for each node
    public TrieNode(boolean isWord, String value) {
      this.isWord = isWord;
      this.value = value;
    }

    // adds a TrieNode child to the current node
    // useful function for adding children
    public void addChild(TrieNode child) {
      for (int j = 0; j < children.size(); j++) {
        if (children.get(j).value.equals(child.value) && child.isWord) {
          children.get(j).isWord = true;
          return;
        }
      }
      children.add(child);
    }

    // allows Trie.addWord and Trie.search to access children for transversal
    public ArrayList<TrieNode> getChildren(TrieNode t) {
      return t.children;
    }

    // removes child from children if exists
    public void removeChild(TrieNode child) {
      this.children.remove(child);
    }
  }
}
