import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.In;

public class Solver {
  private boolean solvable;
  private int minMoves;
  private SearchNode lastNode;

  /*
  * We define a search node of the game to be a board, the number of moves made to reach the board,
  * and the previous search node.
  * Then, delete from the priority queue the search node with the minimum priority,
  * and insert onto the priority queue all neighboring search nodes (those that can be reached in one move from the dequeued search node).
  * Repeat this procedure until the search node dequeued corresponds to the goal board.
  *
  */
  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if(initial == null) throw new IllegalArgumentException("Null argument");

    int moves = 0;
    int twin_moves = 0;

    LinkedQueue<Board> neighbors = new LinkedQueue<>();
    LinkedQueue<Board> twin_neighbors = new LinkedQueue<>();

    // Create MinPQ of searchNodes
    MinPQ<SearchNode> MinSearchNodes = new MinPQ<>();
    MinPQ<SearchNode> MinTwin_searchNodes = new MinPQ<>();

    // insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue.
    SearchNode searchNode = new SearchNode(initial, moves, null);
    SearchNode twin_searchNode = new SearchNode(initial.twin(), twin_moves, null);

    // the root of the game tree is the initial state
    MinSearchNodes.insert(searchNode);
    MinTwin_searchNodes.insert(twin_searchNode);

    boolean solved = false;
    boolean twin_solved =  false;

    //System.out.println("Goal: " + MinSearchNodes.delMin().getCurrentBoard());

    SearchNode current_min;
    SearchNode twin_current_min;

    // while the board is not solved
    // keep
    while(!solved && !twin_solved) {
      current_min = MinSearchNodes.delMin();
      SearchNode previous = current_min.getPrevious();
      Board temp = current_min.getCurrentBoard();
      solved = temp.isGoal();

      twin_current_min = MinTwin_searchNodes.delMin();
      SearchNode previous_twin = twin_current_min.getPrevious();
      Board temp_twin = twin_current_min.getCurrentBoard();
      twin_solved = temp_twin.isGoal();

      // enqueue all the neighbors
      for(Board b: temp.neighbors()) {
        neighbors.enqueue(b);
      }

      // enqueue all the twin neighbors
      for(Board b: temp_twin.neighbors()) {
        twin_neighbors.enqueue(b);
      }

      while(neighbors.size() > 0) {
        Board board = neighbors.dequeue();
        int move = current_min.getMoves();
        move++;

        // don’t enqueue a neighbor if its board is the same as the board of the previous search node in the game tree.
        if(previous != null && previous.getCurrentBoard().equals(board)) {
          continue;
        }

        // create child nodes and insert them
        SearchNode neighborNode = new SearchNode(board, move, current_min);
        // System.out.println("Priorities: " + neighborNode.getPriority());
        MinSearchNodes.insert(neighborNode);
      }

      while(twin_neighbors.size() > 0) {
        Board twin_board = twin_neighbors.dequeue();
        // HERE IS A BUG ????
        int move_twin = twin_current_min.getMoves();
        move_twin++;

        if(previous_twin != null && previous_twin.getCurrentBoard().equals(twin_board)) {
          continue;
        }

        // create child nodes and insert them
        SearchNode twinNeighborNode = new SearchNode(twin_board, move_twin, twin_current_min);
        // System.out.println("Priorities: " + twinNeighborNode.getPriority());
        MinTwin_searchNodes.insert(twinNeighborNode);
      }

      moves = current_min.getMoves();
      twin_moves = twin_current_min.getMoves() + 1;
      lastNode = current_min;
    }

    solvable = !twin_solved;
    minMoves = moves;
  }

  /*
  * Create search node for inserting into MinPQ
  * The components of the Node: moves, previous, current, priority
  */
  private class SearchNode implements Comparable<SearchNode> {
    private SearchNode previous;
    private Board currentBoard;
    private int moves;
    private int priority;

    public SearchNode(Board initial, int m, SearchNode prev) {
      previous = prev;
      moves = m;
      currentBoard = initial;
      // the total number of moves we need to make (including those already made) is at least its priority,
      // using either the Hamming or Manhattan priority function.
      priority = m + initial.manhattan();
    }

    public int getPriority() {
      return priority;
    }

    public Board getCurrentBoard() {
      Board temp = currentBoard;
      return temp;
    }

    public int getMoves() {
      return moves;
    }

    public SearchNode getPrevious() {
      SearchNode temp = previous;
      return temp;
    }

    @Override
    public int compareTo(SearchNode o) {
//      if(this.priority < o.getPriority())         return -1;
//      else if(this.priority > o. getPriority())   return 1;
//      else                                        return 0;
      return this.priority - o.priority;
    }
  }

  /*
  * To detect such situations, use the fact that boards are divided into two equivalence classes with respect to reachability:
  * Those that can lead to the goal board
  * Those that can lead to the goal board if we modify the initial board by swapping any pair of tiles (the blank square is not a tile).
  *
   */

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return solvable;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if(!isSolvable()) {
      return -1;
    }
    return minMoves;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    Stack<Board> boards = new Stack<>();
    SearchNode lastNode = this.lastNode;
    if(this.isSolvable()) {
      while(lastNode.getPrevious() != null) {
        boards.push(lastNode.getCurrentBoard());
        lastNode = lastNode.getPrevious();
      }
      // push the last board
      boards.push(lastNode.getCurrentBoard());
      return boards;
    }
    return null;
  }

  // test client (see below)
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Iterable<Board> solution;

    int[] numbers = {0, 1, 3, 4, 2, 5, 7, 8, 6};
    int[] goal = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    int[] unsolvable = {1, 2, 3, 4, 5, 6, 8, 7, 0};
    int[] test = {1, 2, 3, 4, 6, 5, 7, 8, 0};
    int[][] blocks = new int[3][3];
    int[][] block2 = new int[2][2];
    int idx = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        blocks[i][j] = numbers[idx++];
      }
    }

    int[] unsolvable2 = {1, 0, 2, 3};
    int k = 0;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        block2[i][j] = unsolvable2[k++];
      }
    }

    //Board board = new Board(block2);
    Board board = new Board(blocks);
    System.out.println(board);
    System.out.println("TWIN: " + board.twin());

    Solver solver = new Solver(board);
    System.out.println("Is solvable? " + solver.isSolvable());
    solution = solver.solution();
    System.out.println("Minimum number of moves = " + solver.moves());
    for (Board b : solution)
      System.out.println(b);

    // create initial board from file
//    In in = new In(args[0]);
//    int n = in.readInt();
//    int[][] tiles = new int[n][n];
//    for (int i = 0; i < n; i++)
//      for (int j = 0; j < n; j++)
//        tiles[i][j] = in.readInt();
//    Board initial = new Board(tiles);
//
//    // solve the puzzle
//    Solver solver = new Solver(initial);
//
//    // print solution to standard output
//    if (!solver.isSolvable())
//      StdOut.println("No solution possible");
//    else {
//      StdOut.println("Minimum number of moves = " + solver.moves());
//      for (Board board : solver.solution())
//        StdOut.println(board);
//    }
  }
}
