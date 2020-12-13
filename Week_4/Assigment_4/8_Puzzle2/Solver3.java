import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.LinkedList;

public class Solver3 {
  private SearchNode lastNode;
  private int minMoves;
  private boolean solvable;

  // find a solution to the initial board (using the A* algorithm)
  public Solver3(Board3 initial) {
    int moves = 0, twin_moves = 0;
    boolean isSolvable = false, isTwinSolvable = false;

    if(initial == null) throw new IllegalArgumentException();

    LinkedQueue<Board3> neighbors = new LinkedQueue<>();
    LinkedQueue<Board3> twin_neighbors = new LinkedQueue<>();

    MinPQ<SearchNode> minPQ = new MinPQ<>();
    MinPQ<SearchNode> twin_minPQ = new MinPQ<>();

    SearchNode initialSearchNode = new SearchNode(initial, moves, null);
    SearchNode twinInitialSearchNode = new SearchNode(initial.twin(), twin_moves, null);

    minPQ.insert(initialSearchNode);
    twin_minPQ.insert(twinInitialSearchNode);

    SearchNode current;
    SearchNode twin_current;

    while(!isSolvable && !isTwinSolvable) {
      current = minPQ.delMin();
      SearchNode previous = current.getPrevious();
      Board3 current_board = current.getBoard();
      isSolvable = current_board.isGoal();

      twin_current = twin_minPQ.delMin();
      SearchNode twin_previous = twin_current.getPrevious();
      Board3 twin_currentBoard = twin_current.getBoard();
      isTwinSolvable = twin_currentBoard.isGoal();

      for(Board3 b: current_board.neighbors()) {
        neighbors.enqueue(b);
      }

      for(Board3 b: twin_currentBoard.neighbors()) {
        twin_neighbors.enqueue(b);
      }

      while(neighbors.size() > 0) {
        Board3 board = neighbors.dequeue();
        int current_moves = current.getMoves();
        current_moves++;

        if(previous != null && previous.getBoard().equals(board)) {
          continue;
        }

        // create child nodes and insert them
        SearchNode neighborNode = new SearchNode(board, current_moves, current);
        minPQ.insert(neighborNode);
      }

      while(twin_neighbors.size() > 0) {
        Board3 twin_board = neighbors.dequeue();
        int twin_Currentmoves = twin_current.getMoves();
        twin_Currentmoves++;

        if(twin_previous != null && twin_previous.getBoard().equals(twin_board)) {
          continue;
        }

        SearchNode twin_neighborNode = new SearchNode(twin_board, twin_Currentmoves, twin_current);
        twin_minPQ.insert(twin_neighborNode);
      }

      moves = current.getMoves();
      twin_moves = twin_current.getMoves();
      lastNode = current;

    }
    solvable = !isTwinSolvable;
    minMoves = moves;

  }

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return solvable;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if(!isSolvable()){
      return -1;
    }
    return minMoves;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board3> solution() {
    Stack<Board3> boards = new Stack<>();
    SearchNode lastNode = this.lastNode;

    if(this.isSolvable()) {
      while(lastNode.getPrevious() != null) {
        boards.push(lastNode.getBoard());
        lastNode = lastNode.getPrevious();
      }
      boards.push(lastNode.getBoard());
      return boards;
    }
    return null;
  }

  public class SearchNode implements Comparable<SearchNode> {
    private int moves;
    private int priority;
    private SearchNode previous;
    private Board3 board;

    public SearchNode(Board3 initial, int m, SearchNode prev) {
      board = initial;
      moves = m;
      previous = prev;
      priority = m + board.manhattan();
    }

    public int getMoves() {
      return moves;
    }

    public int getPriority() {
      return priority;
    }

    public Board3 getBoard() {
      return board;
    }

    public SearchNode getPrevious() {
      return previous;
    }

    @Override
    public int compareTo(SearchNode o) {
      return this.priority - o.priority;
    }
  }

  // test client (see below)
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Iterable<Board3> solution;

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
    Board3 board = new Board3(blocks);
    System.out.println(board);
    System.out.println("TWIN: " + board.twin());

    Solver3 solver = new Solver3(board);
    System.out.println("Is solvable? " + solver.isSolvable());
    solution = solver.solution();
    System.out.println("Minimum number of moves = " + solver.moves());
    for (Board3 b : solution)
      System.out.println(b);
  }
//
//    // create initial board from file
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
//  }

}
