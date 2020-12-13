import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.LinkedQueue;

public class Board {
  private int[] board;
  private static int width;
  // We want start from 1 to N (we dont want to count the blank square)
  private int blank_square = 1;


  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    // size of rows
    int row = tiles.length;
    // size of columns
    int col = tiles[0].length;
    board = new int[row * col];
    width = tiles.length;

    if (tiles == null) throw new IllegalArgumentException();
    if (row != col) throw new IllegalArgumentException();

    // Translate the 2D Board into a 1D array
    board = twoDTo1D(tiles, row, col);
  }


  /**
   * string representation of this board
   * returns a string composed of n + 1 lines.
   * The first line contains the board size n;
   * the remaining n lines contains the n-by-n grid of tiles in row-major order,
   * using 0 to designate the blank square.
   *
   * @return String
   */
  public String toString() {
    StringBuilder string = new StringBuilder();
    string.append("Size: " + width + " x " + width + "\n");
    for(int i = 0; i < width; i++) {
      for(int j = 0; j < width; j++) {
        string.append(String.format("%2d ", board[xyToPos(i, j)]));
      }
      string.append("\n");
    }
    return string.toString();
  }

  // board dimension n
  public int dimension() {
    return width;
  }

  /**
   * Number of tiles out of place, plus the number of moves made so far to get to the search node.
   * Intuitively, a search node with a small number of blocks in the wrong position is close to the goal,
   * and we prefer a search node that have been reached using a small number of moves.
   *
   * @return
   */
  public int hamming() {
    int sum = 0;

    for (int i = 0; i < board.length; i++) {
      if (board[i] != i + blank_square && board[i] != 0) {
        sum++;
      }
    }
    return sum;
  }

  /**
   * The sum of the Manhattan distances (sum of the vertical and horizontal distance)
   * from the blocks to their goal positions, plus the number of moves made so far to get to the search node.
   */
  public int manhattan() {
    int sum = 0;
    int x1;
    int x2;
    int y1;
    int y2;
    for (int i = 0; i < board.length; i++) {
      if (board[i] != i + blank_square && board[i] != 0) {
        // Where I want to go
        x1 = xyFromPos(i)[0];
        y1 = xyFromPos(i)[1];
//        System.out.println("Board content: " + board[i]);
        // My current position
        x2 = xyFromPos(board[i] - blank_square)[0];      // col
        y2 = xyFromPos(board[i] - blank_square)[1];      // row
//        System.out.println("x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2);
//        System.out.println("Distance: " + (Math.abs(x1-x2) + Math.abs(y1-y2)));
        sum = sum + ( Math.abs(x1-x2) + Math.abs(y1-y2) );
      }
    }
    return sum;
  }

  // is this board the goal board?
  public boolean isGoal() {
    for(int i = 0; i < board.length - blank_square; i++) {
      if(board[i] != i + blank_square) {
        return false;
      }
    }
    return true;
  }

  /**
   * Two boards are equal if they are have the same size and their corresponding tiles are in the same positions.
   * The equals() method is inherited from java.lang.Object, so it must obey all of Java’s requirements.
   * @return
   */
  // does this board equal y?
  public boolean equals(Object y) {
    if(y == this) return true;
    if(y == null) return false;
    if(y.getClass() != this.getClass()) return false;
    Board that = (Board) y;
    if(this.dimension() != that.dimension()) return false;

    for(int i = 0; i < board.length; i++) {
      if(this.board[i] != that.board[i]) {
        return false;
      }
    }
    return true;
  }


  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    int row;
    int col;
    int up, down, left, right;

    int[][] copy_twin;
    int[] copy_board = new int[board.length];

    // copy board to copy_board
    for(int i = 0; i < board.length; i++) {
      copy_board[i] = board[i];
    }

    // check for non-zero in board
    // exchange the non-zero tile whit other tile up-down-left-right
    for(int i = 0; i < copy_board.length; i++) {
      if(copy_board[i] != 0) {
        col = xyFromPos(i)[0];
        row = xyFromPos(i)[1];

        //System.out.println("Board position: " + board[i]);

        // Up
        if(checkBoundaries(row-1, col)) {
          up = xyToPos(row-1, col);
          //System.out.println("Up: " + copy_board[up]);
          if(copy_board[up] != 0) {
            swap(copy_board, up, i);
            break;
          }
        }

        // left
        if(checkBoundaries(row, col-1)) {
          left = xyToPos(row, col-1);
          //System.out.println("Left: " + copy_board[left]);
          if(copy_board[left] != 0) {
            swap(copy_board, left, i);
            break;
          }
        }

        // down
        if(checkBoundaries(row+1, col)) {
          down = xyToPos(row+1, col);
          //System.out.println("Down: " + copy_board[down]);
          if(copy_board[down] != 0) {
            swap(copy_board, down, i);
            break;
          }
        }

        // right
        if(checkBoundaries(row, col+1)) {
          right = xyToPos(row, col+1);
          System.out.println("Right: " + copy_board[right]);
          if(copy_board[right] != 0) {
            swap(copy_board, right, i);
            break;
          }
        }
      }
    }
    copy_twin = oneDTo2D(copy_board, width);

    return new Board(copy_twin);
  }

  /*
  * All the possible boards to exchange
   */
  // all neighboring boards
  public Iterable<Board> neighbors() {
    int emptyTile;
    int row, col;
    int up, left, right, down;
    LinkedQueue<Board> neighbors = new LinkedQueue<>();
    ArrayList<Integer> blocks = new ArrayList<>();

    // find empty tile position
    // and exchange with all the position it can exchange in boundaries
    // add all position in blocks list
    for (emptyTile = 0; emptyTile < board.length; emptyTile++) {
      if (board[emptyTile] == 0) {
        col = xyFromPos(emptyTile)[0];
        row = xyFromPos(emptyTile)[1];

        // Up
        if (checkBoundaries(row - 1, col)) {
          up = xyToPos(row - 1, col);
          blocks.add(up);
        }

        // Left
        if (checkBoundaries(row, col - 1)) {
          left = xyToPos(row, col - 1);
          blocks.add(left);
        }

        // Down
        if (checkBoundaries(row + 1, col)) {
          down = xyToPos(row + 1, col);
          blocks.add(down);
        }

        // Right
        if (checkBoundaries(row, col + 1)) {
          right = xyToPos(row, col + 1);
          blocks.add(right);
        }
        break;
      }
    }

    // System.out.println("Empty: " + emptyTile);
    for(int i = 0; i < blocks.size(); i++) {
      // System.out.println("Block: " + blocks.get(i));
      int[] temp1D = new int[board.length];
      int[][] temp2D;
      // copy board into tempBoard
      for(int j = 0; j < board.length; j++) {
        temp1D[j] = board[j];
      }
      // swap tiles with empty tile
      swap(temp1D, emptyTile, blocks.get(i));
      // translate 1D to 2D
      temp2D = oneDTo2D(temp1D, this.width);
      // create Board and enqueue it
      Board tempBoard = new Board(temp2D);
      neighbors.enqueue(tempBoard);
    }
    return neighbors;
  }


  /***************************************************************************
   *  Helper Board functions.
   ***************************************************************************/
  /*
  * Translate array 1D to array 2D
  */
//  public static int[][] copy1DTo2D(int[] a, int size) {
//    int[][] copy = new int[size][size];
//    for(int i = 0; i < a.length; i++) {
//      int col = xyFromPos(i)[0];
//      int row = xyFromPos(i)[1];
//      copy[row][col] = a[i];
//    }
//    return copy;
//  }
  /*
   * Helper method: from 1D to 2D array
   */
  public static int[][] oneDTo2D(int[] a, int size) {
    int[][] temp = new int[size][size];
    int idx = 0;

    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp.length; j++) {
        temp[i][j] = a[idx++];
      }
    }
    return temp;
  }

  /*
  * check boundaries of the current tile
  */
  public boolean checkBoundaries(int i, int j) {
    if(i < 0 || i > width - blank_square) return false;
    if(j < 0 || j > width - blank_square) return false;
    return true;
  }

  /*
   * Helper method: from 2D to 1D array find the position
   * Formula row-major order:
   * Row- major order: row * width + col
   * Col- major order: col * width + row
   * https://en.wikipedia.org/wiki/Row-_and_column-major_order
   * https://www.cyotek.com/blog/converting-2d-arrays-to-1d-and-accessing-as-either-2d-or-1d
   */
  public int xyToPos(int i, int j) {
    return i * width + j;
  }

  /*
  * Translate the 2D Board into a 1D array
   */
  public int[] twoDTo1D(int[][] tiles, int row, int col) {
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        board[xyToPos(i, j)] = tiles[i][j];
//        System.out.print(xyTo1D(i, j) + " ");
      }
      //System.out.println();
    }
    return board;
  }

  /*
   * Helper method: return x, y value from 1D array to 2D array
   */
  public static int[] xyFromPos(int i) {
    int[] xy = new int[2];
    xy[0] = i % width;    // col
    xy[1] = i / width;    // row
    return xy;
  }

  /*
   * exchange index values in an 1D array
   */
  public void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  /***************************************************************************
   * Helper Board functions.
   ***************************************************************************/

  /*
   * Unit testing
   */
//  public static void main(String[] args) {
//    int[][] test = new int[3][3];
//    int[][] GoalBoard = new int[3][3];
//    int[] puzzle = {5, 3, 2, 6, 7, 8, 4, 1, 0};
//    int[] puzzleGoal = {1, 2, 3, 4, 5, 6, 7, 8, 0};
//    int[][] temp;
//    int[][] temp1;
//
//    test = oneDTo2D(puzzle, 3);
//    GoalBoard = oneDTo2D(puzzleGoal, 3);
//
////    for (int i = 0; i < test.length; i++) {
////      for (int j = 0; j < test.length; j++) {
////        System.out.print(test[i][j] + " ");
////        //System.out.print(GoalBoard[i][j] + " ");
////      }
////      System.out.println();
////    }
//
//    Board board = new Board(test);
//    System.out.println(board);
//
////    temp = oneDTo2D(puzzleGoal, board.width);
////    temp1 = copy1DTo2D(puzzleGoal, board.width);
////
////    for (int i = 0; i < test.length; i++) {
////      for (int j = 0; j < test.length; j++) {
////        System.out.print(temp[i][j] + " ");
////        //System.out.print(temp1[i][j] + " ");
////      }
////      System.out.println();
////    }
//
//    System.out.println("Is it goal board? " + board.isGoal());
//    System.out.println("Manhattan: " + board.manhattan());
//    System.out.println("Hamming: " + board.hamming());
//    System.out.println();
//
//    int n = 1;
//    Iterable<Board> neighbors = board.neighbors();
//    for(Board b : neighbors) {
//      System.out.println("Neighbor: " + n++);
//      System.out.println(b);
//    }
//
//    Board twin;
//    twin = board.twin();
//    System.out.println("Twin");
//    System.out.println(twin);
//
//    Board board1 = new Board(test);
//    System.out.println("Is the board1 goal board? " + board1.isGoal());
//    System.out.println("Is board equal to twin? " + board.equals(twin));
//    System.out.println("Is board equal to board? " + board.equals(board));
//    System.out.println("Is board equal to null? " + board.equals(null));
//    System.out.println("Is the board equal to board1? " + board.equals(board1));
//  }
}
