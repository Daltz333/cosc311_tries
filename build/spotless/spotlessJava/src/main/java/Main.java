import java.io.*;
import java.util.Scanner;

public class Main {
  // obviously everything here is just a showcase that Trie.java should work
  // Everything here can be deleted to make way for actual input of words into our Trie via file IO,
  // and user IO prompting for searching the Trie
  public static void main(String[] args) {
    Trie trie = new Trie();
    /**
     * trie.addWord("a"); trie.addWord("apple"); trie.addWord("banner"); trie.addWord("bogus");
     * trie.addWord("master"); trie.addWord("mine"); trie.addWord("man"); trie.addWord("criticism");
     * trie.addWord("critic"); trie.addWord("collar"); trie.addWord("cops"); trie.addWord("silver");
     * trie.addWord("boss"); trie.addWord("bossy"); trie.addWord("flim"); trie.addWord("flam");
     * trie.addWord("flak"); trie.addWord("pizza"); System.out.println(trie.search("bossi")); //
     * checking to see if "a" triggers WORD FOUND for all a-words // checking to see if double
     * matching characters ending words triggers WORD FOUND for words with // one less character
     * that matches the double. // Like, apple ends in e, applee ends in e, but they don't match.
     * There was a while where if // would say applee was found, and I fixed it :)
     * System.out.println(trie.search("applee")); System.out.println(trie.search("apples"));
     * System.out.println(trie.search("cope")); System.out.println(trie.search("ma")); // OMFG I'm
     * so glad I checked this. When adding critic and criticism, the Trie went c-r-i-t-i- // then
     * had a c(true) and c(false) children. // had to fix that, so that only one c child is created,
     * and if it is later added with // isWord=true, it would just change the isWord of that child
     * rather than adding another. System.out.println(trie.search("critic"));
     * System.out.println(trie.search("critics"));
     */
    InputStream is = Main.class.getResourceAsStream("words.txt");

    if (is == null) {
      System.out.println("Dictionary was not found!");
      return;
    }

    BufferedReader bufReader = new BufferedReader(new InputStreamReader(is));

    String[] lines = bufReader.lines().toArray(String[]::new);

    for (String line : lines) {
      trie.addWord(line);
    }

    boolean exit = false;
    Scanner scanner = new Scanner(System.in);

    while (!exit) {
      System.out.print("Query? ");

      System.out.println(trie.search(scanner.nextLine()));
    }
  }
}
