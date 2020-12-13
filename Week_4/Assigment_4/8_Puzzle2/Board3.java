import edu.princeton.cs.algs4.LinkedQueue;

import java.util.ArrayList;

public class Board3 {
  private int[] board;
  private int width;
  private int blank_square = 1;


  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board3(int[][] tiles) {
    int row = tiles.length;
    int col = tiles[0].length;
    width = tiles.length;
    board = new int[row * col];

    if(tiles == null) throw new IllegalArgumentException();
    if(row != col) throw new IllegalArgumentException();

    board = TwoDtoOneD(tiles, row, col);
  }

  // string representation of this board
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("Size: " + width + " x " + width + "\n");
    for(int i = 0; i < width; i++) {
      for(int j = 0; j < width; j++) {
        s.append(String.format("%2d ", board[xyToPos(i, j)]));
      }
      s.append("\n");
    }
    return s.toString();
  }

  // board dimension n
  public int dimension() {
    return width;
  }

  // number of tiles out of place
  public int hamming() {
    int sum = 0;

    for(int i = 0; i < board.length; i++) {
      if(board[i] != i + blank_square && board[i] != 0) {
        sum += 1;
      }
    }
    return sum;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    int sum = 0;
    int x1;
    int y1;
    int x2;
    int y2;

    for(int i = 0; i < board.length; i++) {
      if(board[i] != i + blank_square && board[i] != 0) {
        // where I want to go
        x1 = xyFromPos(i)[0];   // col
        y1 = xyFromPos(i)[1];   // row
        // where is my current position
        x2 = xyFromPos(board[i] - blank_square)[0];   //col
        y2 = xyFromPos(board[i] - blank_square)[1];   //row
        sum = sum + (Math.abs(x1 - x2) + Math.abs(y1 - y2));
//        System.out.println("x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2);
//        System.out.println("Distance: " + (Math.abs(x1 - x2) + Math.abs(y1 - y2)));
      }
    }
//    System.out.println("sum: " + sum);
    return sum;
  }

  // is this board the goal board?
  public boolean isGoal() {
    for(int i = 0; i < board.length-1; i++) {
      if(board[i] != i + blank_square) return false;
    }
    return true;
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if(y == this) return true;
    if(y == null) return false;
    if(y.getClass() != this.getClass()) return false;
    Board3 that = (Board3) y;
    if(this.dimension() != that.dimension()) return false;

    for(int i = 0; i < board.length; i++) {
      if(this.board[i] != that.board[i]){
        return false;
      }
    }
    return true;
  }

  // all neighboring boards
  public Iterable<Board3> neighbors() {
    int emptyTile;
    int row, col;
    int up, left, down, right;
    LinkedQueue<Board3> neighbors = new LinkedQueue<>();
    ArrayList<Integer> tiles = new ArrayList<>();

    for(emptyTile = 0; emptyTile < board.length; emptyTile++) {
      if(board[emptyTile] == 0) {
        col = xyFromPos(emptyTile)[0];
        row = xyFromPos(emptyTile)[1];

        // Up
        if(checkboundaries(row-1, col)) {
          up = xyToPos(row-1, col);
          tiles.add(up);
        }
        // Left
        if(checkboundaries(row, col-1)) {
          left = xyToPos(row, col-1);
          tiles.add(left);
        }
        // Down
        if(checkboundaries(row+1, col)) {
          down = xyToPos(row+1, col);
          tiles.add(down);
        }
        // Right
        if(checkboundaries(row, col+1)) {
          right = xyToPos(row, col+1);
          tiles.add(right);
        }
        break;
      }
    }

    for(int i = 0; i < tiles.size(); i++) {
      int[] temp = new int[board.length];
      int[][] temp2D;

      // copy board to temp
      for(int j = 0; j < board.length; j++) {
        temp[j] = board[j];
      }

      swap(temp, emptyTile, tiles.get(i));
      temp2D = oneDtoTwoD(temp, this.width);
      Board3 block = new Board3(temp2D);
      neighbors.enqueue(block);
    }
    return neighbors;
  }


  // a board that is obtained by exchanging any pair of tiles
  public Board3 twin() {
    int row, col;
    int up, left, down, right;
    int[] copy_board = new int[board.length];
    int[][] copy_twin;

    // copy board to copy_board
    for(int j = 0; j < board.length; j++) {
      copy_board[j] = board[j];
    }

    for(int i = 0; i < board.length; i++) {
      if(board[i] != 0) {
        col = xyFromPos(i)[0];
        row = xyFromPos(i)[1];

        // Up
        if(checkboundaries(row-1, col)) {
          up = xyToPos(row-1, col);
          if(copy_board[up] != 0) {
            swap(copy_board, up, i);
            break;
          }
        }

        // Left
        if(checkboundaries(row, col-1)) {
          left = xyToPos(row, col-1);
          if(copy_board[left] != 0) {
            swap(copy_board, left, i);
            break;
          }
        }

        // Down
        if(checkboundaries(row+1, col)) {
          down = xyToPos(row+1, col);
          if(copy_board[down] != 0) {
            swap(copy_board, down, i);
            break;
          }
        }

        // Right
        if(checkboundaries(row, col+1)) {
          right = xyToPos(row, col+1);
          if(copy_board[right] != 0) {
            swap(copy_board, right, i);
            break;
          }
        }
      }
    }
    copy_twin = oneDtoTwoD(copy_board, this.width);
    Board3 twin = new Board3(copy_twin);
    return twin;
  }

  /***************************************************************************
   *  Helper Board functions.
   ***************************************************************************/

  public int[] TwoDtoOneD(int[][] a, int row, int col) {
    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        board[xyToPos(i, j)] = a[i][j];
      }
    }
    return board;
  }

  /*
   * row major order
   */
  public int xyToPos(int i, int j) { return i * width + j;}

  public static int[][] oneDtoTwoD(int[] a, int size) {
    int[][] temp = new int[size][size];
    int idx = 0;

    for(int i = 0; i < temp.length; i++) {
      for(int j = 0; j < temp.length; j++) {
        temp[i][j] = a[idx++];
        //System.out.print(temp[i][j] + " ");
      }
      //System.out.print("\n");
    }
    return temp;
  }

  public int[] xyFromPos(int i) {
    int[] xy = new int[2];
    xy[0] = i % width;    // col
    xy[1] = i / width;    // row
    return xy;
  }

  public boolean checkboundaries(int row, int col) {
    if(row < 0 || row >= width) return false;
    if(col < 0 || col >= width) return false;
    return true;
  }

  public void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  /***************************************************************************
   *  Helper Board functions.
   ***************************************************************************/

  // unit testing (not graded)
  public static void main(String[] args) {
    int[][] tilesTest;
    int[] test = {5, 3, 2, 6, 7, 8, 4, 1, 0};
    int[] goal = {1, 2, 3, 4, 5 ,6, 7, 8 , 0};

    tilesTest = oneDtoTwoD(test, 3);
    //tilesTest = oneDtoTwoD(goal, 3);

//    for (int i = 0; i < tilesTest.length; i++) {
//      for (int j = 0; j < tilesTest.length; j++) {
//        System.out.print(tilesTest[i][j] + " ");
//        //System.out.print(GoalBoard[i][j] + " ");
//      }
//      System.out.println();
//    }

    Board3 board = new Board3(tilesTest);
    System.out.println(board);
    System.out.println("Is the clone_board goal board? " + board.isGoal());
    System.out.println("Manhattan: " + board.manhattan());
    System.out.println("Hamming: " + board.hamming());

    Board3 twin;
    twin = board.twin();
    System.out.println("Twin");
    System.out.println(twin);

    int n = 1;
    Iterable<Board3> neighbors = board.neighbors();
    for(Board3 b : neighbors) {
      System.out.println("Neighbor: " + n++);
      System.out.println(b);
    }

    Board3 clone_board = new Board3(tilesTest);
    System.out.println("Is the clone_board goal board? " + clone_board.isGoal());
    System.out.println("Is the board equal to clone_board " + board.equals(clone_board));
    System.out.println("Is the board equal to null " + board.equals(null));
    System.out.println("Is the board equal to board " + board.equals(board));
    System.out.println("Is the board equal to twin " + board.equals(twin));


  }
}
