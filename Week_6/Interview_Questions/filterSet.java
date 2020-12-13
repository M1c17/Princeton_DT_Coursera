import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

public class filterSet {

/**
 *  The {@code AllowFilter} class provides a client for reading in an <em>allowlist</em>
 *  of words from a file; then, reading in a sequence of words from standard input,
 *  printing out each word that appears in the file.
 *  It is useful as a test client for various symbol table implementations.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
//  public static void main(String[] args) {
//    // create empty set of string
//    SET<String> set = new SET<String>();
//    // open file
//    In in = new In(args[0]);
//
//    // read whitelist
//    while(!in.isEmpty()) {
//      set.add(in.readString());
//    }
//
//    while(!StdIn.isEmpty()) {
//      String word = StdIn.readString();
//      if(set.contains(words)) {
//        StdOut.println(word);
//      }
//    }
//  }

/**
 *  The {@code BlockFilter} class provides a client for reading in a <em>blocklist</em>
 *  of words from a file; then, reading in a sequence of words from standard input,
 *  printing out each word that <em>does not</em> appear in the file.
 *  It is useful as a test client for various symbol table implementations.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
//  public static void main(String[] args) {
//    // create empty set
//    SET<String> set = new SET<String>();
//    In in = new In(args[0]);
//
//    while(!in.isEmpty()) {
//      set.add(in.readString());
//    }
//
//    while(!StdIn.isEmpty()) {
//      String word = StdIn.readString();
//      if(!set.contains(word)) {
//        StdOut.println(word);
//      }
//    }
//  }

  /**
   *  The {@code LookupCSV} class provides a data-driven client for reading in a
   *  key-value pairs from a file; then, printing the values corresponding to the
   *  keys found on standard input. Both keys and values are strings.
   *  The fields to serve as the key and value are taken as command-line arguments.
   *  <p>
   *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
   *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
   *
   *  @author Robert Sedgewick
   *  @author Kevin Wayne
   */
//  public static void main(String[] args) {
//    // process input file
//    In in = new In(args[0]);
//    int keyField = Integer.parseInt(args[1]);
//    int valField = Integer.parseInt(args[2]);
//
//    // build symbol table
//    ST<String, String> st = new ST<String, String>();
//    while(!in.isEmpty()) {
//      String line = in.readLine();
//      String[] tokens = line.split(",");
//      String key = tokens[keyField];
//      String value = tokens[valField];
//      st.put(key, value);
//    }
//
//    while(!StdIn.isEmpty()) {
//      String s = StdIn.readString();
//      if(st.contains(s)) {
//        StdOut.println(st.get(s));
//      } else {
//        StdOut.println("Not found");
//      }
//    }
//  }

  /**
   *  The {@code FileIndex} class provides a client for indexing a set of files,
   *  specified as command-line arguments. It takes queries from standard input
   *  and prints each file that contains the given query.
   *  <p>
   *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
   *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
   *
   *  @author Robert Sedgewick
   *  @author Kevin Wayne
   */
//  public static void main(String[] args) {
//    ST<String, SET<File>> st = new ST<String, SET<File>>();
//
//    // created inverted index of all files
//    StdOut.println("Indexing files");
//    for(String filename: args) {
//      StdOut.println(" " + filename);
//      File file = new File(filename);
//      In in = new In(file);
//      // for each word in file add file to corresponding set
//      while(!in.isEmpty()) {
//        String key = in.readString();
//        if(!st.contains(key)) {
//          st.put(key, new SET<File>());
//        }
//        SET<File> set = st.get(key);
//        set.add(file);
//      }
//    }
//
//    while(!StdIn.isEmpty()) {
//      String query = StdIn.readString();
//      if(st.contains(query)) {
//        SET<File> set = st.get(query);
//        for(File file: set) {
//          // print all the files with this query
//          StdOut.println(" " + file.getName());
//        }
//      }
//    }
//  }

  public static void main(String[] args) {
    In in = new In(args[0]);
    String[] words = StdIn.readAll().split("//s+");
    ST<String, SET<Integer>> st = new ST<String, SET<Integer>>();
    for(int i = 0; i < words.length; i++) {
      String s = words[i];
      if(!st.contains(s)) {
        st.put(s, new SET<Integer>());
      } else {
        SET<Integer> set = st.get(s);
        set.put(i);
      }
    }
    while(!StdIn.isEmpty()) {
      String query = StdIn.readString();
      SET<Integer> set = st.get(query);
      for(int k : set) {
        // print words[k-4] to words[k+4]
      }
    }

  }


}
