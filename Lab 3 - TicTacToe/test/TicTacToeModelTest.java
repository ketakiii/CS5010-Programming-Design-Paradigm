import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;


/**
 * This class tests the methods in the class TicTacToe.
 */
public class TicTacToeModelTest {

  @Test public void testConstructor() {
    TicTacToe m = new TicTacToeModel();
    String e1 = "   |   |  \n" + "-----------\n" + "   |   |  \n" + "-----------\n" + "   |   |  ";
    assertEquals(e1, m.toString());
  }

  /**
   * This method tests which Player gets the turn.
   */
  @Test public void testgetTurn() {
    TicTacToe t1 = new TicTacToeModel();
    Player expected1 = Player.X;
    Player expected2 = Player.O;
    assertEquals(expected1, t1.getTurn());
    t1.move(0, 0);
    assertEquals(expected2, t1.getTurn());
    t1.move(1, 0);
    assertEquals(expected1, t1.getTurn());
  }

  /**
   * This method tests if the game is over - also testing the edge cases.
   */
  @Test public void testisGameOver() {
    TicTacToe t2 = new TicTacToeModel();
    boolean expected1 = false;
    assertEquals(expected1, t2.isGameOver());
    t2.move(0, 0);
    t2.move(0, 1);
    t2.move(1, 1);
    boolean expected2 = false;
    assertEquals(expected2, t2.isGameOver());
    t2.move(0, 2);
    t2.move(2, 2);
    boolean expected3 = true;
    assertEquals(expected3, t2.isGameOver());
    assertThrows(IllegalStateException.class, () -> t2.move(1, 2));

    TicTacToe t3 = new TicTacToeModel();
    t3.move(0, 0);
    t3.move(0, 1);

    Player[][] temp1 = t3.getBoard();
    t3.move(0, 2);
    Player[][] temp2 = t3.getBoard();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (i == 0 && j == 2) {
          assertNotEquals(temp1[i][j], temp2[i][j]);
        } else {
          assertEquals(temp1[i][j], temp2[i][j]);
        }
      }
    }

    assertThrows(IllegalArgumentException.class, () -> t3.move(6, 6));
    assertThrows(IllegalArgumentException.class, () -> t3.move(-1, -1));
    t3.move(1, 1);
    t3.move(1, 0);
    t3.move(1, 2);
    t3.move(2, 1);
    t3.move(2, 0);
    t3.move(2, 2);
    boolean expected4 = true;
    assertEquals(expected4, t3.isGameOver());
    assertEquals(null, t3.getWinner());
    assertThrows(IllegalStateException.class, () -> t2.move(1, 2));
  }

  /**
   * This method tests which Player is the winner - also checking the test cases.
   */
  @Test public void testgetWinner() {
    TicTacToe m = new TicTacToeModel();
    assertEquals(null, m.getWinner());

    TicTacToe t3 = new TicTacToeModel();
    t3.move(0, 0);
    t3.move(0, 1);
    assertEquals(null, t3.getWinner());
    t3.move(1, 1);
    t3.move(0, 2);
    t3.move(2, 2);
    Player expected1 = Player.X;
    assertEquals(expected1, t3.getWinner());
    assertEquals(true, t3.isGameOver());

    TicTacToe t5 = new TicTacToeModel();
    t5.move(0, 0);
    t5.move(0, 2);
    assertThrows(IllegalArgumentException.class, () -> t5.move(0, 2));
    t5.move(0, 1);
    t5.move(1, 1);
    t5.move(2, 2);
    t5.move(2, 0);
    Player expected2 = Player.O;
    assertEquals(expected2, t5.getWinner());

    TicTacToe t6 = new TicTacToeModel();
    t6.move(0, 0);
    t6.move(0, 2);
    t6.move(1, 0);
    System.out.println(t6.isGameOver());
    assertThrows(IllegalArgumentException.class, () -> t6.move(1, 0));
    t6.move(1, 2);
    t6.move(2, 0);
    Player expected3 = Player.X;
    assertEquals(expected3, t6.getWinner());
    assertEquals(true, t6.isGameOver());

    TicTacToe t7 = new TicTacToeModel();
    t7.move(0, 0);
    t7.move(2, 0);
    t7.move(1, 1);
    t7.move(2, 2);
    t7.move(0, 2);
    t7.move(2, 1);
    Player expected4 = Player.O;
    assertEquals(expected4, t7.getWinner());
    assertEquals(true, t7.isGameOver());
  }

  /**
   * This method tests the current board state.
   */
  @Test public void testgetBoard() {
    TicTacToe t = new TicTacToeModel();
    t.move(0, 0);
    t.move(1, 1);
    assertEquals(Player.X, t.getMarkAt(0, 0));
    Player[][] test = t.getBoard();

    assertEquals(Player.X, test[0][0]);
    assertEquals(Player.O, test[1][1]);
    assertEquals(null, test[2][2]);

    test[2][2] = Player.X;
    assertEquals(null, t.getMarkAt(2, 2));

  }

  /**
   * This method tests is the mark at a position at the board is correct,
   * testing on illegal rows and columns.
   */
  @Test public void testgetMarkAt() {
    TicTacToe t1 = new TicTacToeModel();
    Player expected1 = Player.X;
    assertEquals(null, t1.getMarkAt(0, 0));
    t1.move(0, 0);
    assertEquals(expected1, t1.getMarkAt(0, 0));
    assertThrows(IllegalArgumentException.class, () -> t1.getMarkAt(6, 6));
    assertThrows(IllegalArgumentException.class, () -> t1.getMarkAt(-1, -2));
  }

  /**
   * This method tests the toString method.
   */
  @Test public void testtoString() {
    TicTacToe m = new TicTacToeModel();
    System.out.println(m.toString());
  }
}