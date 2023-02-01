package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class defines the TicTacToe game as required in the interface.
 */

public class TicTacToeModel implements TicTacToe {
  // add your implementation here
  private final Player[][] board;
  private final int row = 3;
  private final int col = 3;
  private int currentPlayer;
  private int numTurns = 0;

  /**
   * This is a constructor for the TicTacToe class that initializes the board
   * and the current Player.
   */

  public TicTacToeModel() {
    this.board = new Player[row][col];
    this.currentPlayer = 0;
  }

  /**
   * this is a private helper method for checking valid rows and columns.
   * @param ro - rows of the board
   * @param co - columns of the board
   */

  private void checkIllegalRowsColumns(int ro, int co) {
    if (ro < 0 || co < 0 || ro >= this.row || co >= this.col) {
      throw new IllegalArgumentException("Invalid Rows or Columns");
    }
  }

  /**
   * This method moves the player to the desired place, throws an exception if the game is over
   * or if the space is occupied.
   *
   * @param r the row of the intended move
   * @param c the column of the intended move
   */
  @Override public void move(int r, int c) {

    checkIllegalRowsColumns(r, c);

    if (this.isGameOver()) {
      throw new IllegalStateException("Error: The Game is Over!");
    }

    if (this.board[r][c] != null) {
      throw new IllegalArgumentException("Error: The space is occupied!");
    }

    Player player = getTurn();
    this.board[r][c] = player;
    currentPlayer = (currentPlayer + 1) % 2;
    numTurns = numTurns + 1;
  }

  /**
   * This method gives an output of which player's turn it is.
   *
   * @return which tictactoe.Player's turn it is
   */
  @Override public Player getTurn() {
    if (currentPlayer == 0) {
      return Player.X;
    } else {
      return Player.O;
    }
  }

  /**
   * This method gives the boolean value of if the game is over.
   *
   * @return true if the Game is over
   */
  @Override public boolean isGameOver() {
    // If board is full
    if (numTurns == row * col) {
      return true;
    }
    Player winner = this.getGameWinner();
    return winner != null;
  }

  /**
   * This is a private method goes through the board and checks if there is a winner -
   * vertical, horizontally, diagonal.
   *
   * @return the winner
   */
  private Player getGameWinner() {

    // Row Wise
    // Col Wise
    // Diag1
    // Diag2

    for (int i = 0; i < row; i++) {
      Player player  = getMarkAt(i, 0);
      boolean isFound = true;
      for (int j = 1; j < col; j++) {
        if (getMarkAt(i, j) != player) {
          isFound = false;
          break;
        }
      }
      if (isFound) {
        return player;
      }
    }

    for (int i = 0; i < col; i++) {
      Player player  = getMarkAt(0, i);
      boolean isFound = true;
      for (int j = 1; j < row; j++) {
        if (getMarkAt(j, i) != player) {
          isFound = false;
          break;
        }
      }
      if (isFound) {
        return player;
      }
    }

    Player player  = getMarkAt(0, 0);
    boolean isFound = true;
    for (int j = 1; j < row; j++) {
      if (getMarkAt(j, j) != player) {
        isFound = false;
        break;
      }
    }
    if (isFound) {
      return player;
    }

    player  = getMarkAt(0, 2);
    isFound = true;
    for (int j = 1; j < row; j++) {
      if (getMarkAt(j, row - j - 1) != player) {
        isFound = false;
        break;
      }
    }
    if (isFound) {
      return player;
    }
    return null;
  }

  /**
   * This method gives the winner of the game using the helper method.
   *
   * @return the winner
   */
  @Override public Player getWinner() {
    if (!this.isGameOver()) {
      return null;
    }
    return this.getGameWinner();
  }

  /**
   * This method gives the state of the board.
   *
   * @return the current state of the board
   */
  @Override public Player[][] getBoard() {

    Player[][] p = new Player[this.row][this.col];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        p[i][j] = this.board[i][j];
      }
    }
    return p;
  }

  /**
   * This method gives the player at a particular mark.
   *
   * @param r the row
   * @param c the column
   *
   * @return the player at the given position, or null if it's empty
   */
  @Override public Player getMarkAt(int r, int c) {
    checkIllegalRowsColumns(r, c);
    if (this.board[r][c] == null) {
      return null;
    } else {
      return this.board[r][c];
    }
  }

  /**
   * This method gives out a string output of the board state.
   *
   * @return String output
   */
  @Override public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
        row -> " " + Arrays.stream(row).map(p -> p == null ? " " : p.toString())
            .collect(Collectors.joining(" | "))).collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using the helpful
    // built-in String.join method.
    // List<String> rows = new ArrayList<>();
    // for(tictactoe.Player[] row : getBoard()) {
    //   List<String> rowStrings = new ArrayList<>();
    //   for(tictactoe.Player p : row) {
    //     if(p == null) {
    //       rowStrings.add(" ");
    //     } else {
    //       rowStrings.add(p.toString());
    //     }
    //   }
    //   rows.add(" " + String.join(" | ", rowStrings));
    // }
    // return String.join("\n-----------\n", rows);
  }
}
