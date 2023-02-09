import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.Arrays;
import org.junit.Test;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;


/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {

  // Providing this shell for you to write your
  // own test cases for the TicTacToe controller
  // You DO NOT NEED to implement tests for the provided model

  // TODO: Implement your own tests cases for the controller

  @Test public void testInvalidModel() {
    StringReader input = new StringReader("input");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    assertThrows(IllegalArgumentException.class, () -> c.playGame(null));
  }

  @Test public void testHangingEnd() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 1 1"), gameLog);
    Thread myThread = new Thread() {
      @Override public void run() {
        c.playGame(m);
      }
    };
    myThread.start();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException ie) {
      System.out.print("Continue");
    }
    //assertTrue(myThread.isAlive());
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Enter a move for X", lines[lines.length - 1]);

  }

  @Test public void testSingleValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected = "   |   |  \n" + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n";

    assertEquals(expected, gameLog.toString());
    String[] lines = gameLog.toString().split("\n");
    assertEquals(18, lines.length);
  }

  @Test public void testBadInputAsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("m 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(13, lines.length);
    assertEquals("Input must be a pairs of Integers 1, 2 or 3.", lines[lines.length - 7]);
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines,
        lines.length - 6, lines.length));
    String expected = "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ";
    assertEquals(expected, lastMsg);
  }

  @Test public void testBadInputAsColumn() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 m q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(13, lines.length);
    assertEquals("Input must be a pairs of Integers 1, 2 or 3.", lines[lines.length - 7]);
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines,
        lines.length - 6, lines.length));
    String expected = "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ";
    assertEquals(expected, lastMsg);
  }

  @Test public void testOutOfBoundsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("5 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(13, lines.length);
    assertEquals("Input must be a pairs of Integers 1, 2 or 3.", lines[lines.length - 7]);
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    String expected = "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ";
    assertEquals(expected, lastMsg);
  }

  @Test public void testOutOfBoundColumn() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 7 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);

    String[] lines = gameLog.toString().split("\n");
    assertEquals(13, lines.length);
    assertEquals("Input must be a pairs of Integers 1, 2 or 3.", lines[lines.length - 7]);
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    String expected = "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ";
    assertEquals(expected, lastMsg);
  }

  @Test public void testQinColumn() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected = "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ";
    String[] lines = gameLog.toString().split("\n");
    assertEquals(12, lines.length);
    assertEquals("Game quit! Ending game state:", lines[lines.length - 6]);
  }

  @Test public void testXwins() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 1 1 2 2 2 1 3");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(36, lines.length);
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }

  @Test public void testOwins() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 1 2 1 3 3 3 3 1");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(42, lines.length);
    assertEquals("Game is over! O wins.", lines[lines.length - 1]);
  }

  @Test public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 1 2 2 1 3 1 2 2 3 3 3 2 2 3 1 3");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
  }

  @Test public void testOccupiedSpace() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 1 1 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    String expected = "Game quit! Ending game state:\n"
        + " X |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ";
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals(expected, lastMsg);
    assertEquals("Position occupied", lines[lines.length - 7]);
  }

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }
}