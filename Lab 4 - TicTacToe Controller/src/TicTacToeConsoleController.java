package tictactoe;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private static final Set<String> ELEMENT_2s = Set.of("q", "Q");
  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
    //scan.useDelimiter(" *\\n*");
  }

  @Override public void playGame(TicTacToe m) {
    if (null == m) {
      throw new IllegalArgumentException("Model is null");
    }

    boolean quit = false;
    boolean badInput = false;
    while (!m.isGameOver()) {
      if (!badInput) {
        try {
          out.append(m.toString()).append("\n");
        } catch (IOException e) {
          throw new IllegalStateException(
              "Append failed while outputting game state prior to move.", e);
        }

        try {
          out.append("Enter a move for ").append(String.valueOf(m.getTurn())).append(":\n");
        } catch (IOException ioe) {
          throw new IllegalStateException("Append failed while prompting for a move.", ioe);
        }
      }
      badInput = false;
      String element1 = scan.next();
      if ("q".equals(element1) || "Q".equals(element1)) {
        quit = true;
        break;
      }

      String element2 = scan.next();
      if (ELEMENT_2s.contains(element2)) {
        quit = true;
        break;
      }

      if (!("1".equals(element1) || "2".equals(element1) || "3".equals(element1))
          || !("1".equals(element2) || "2".equals(element2) || "3".equals(element2))) {
        try {
          out.append("Input must be a pairs of Integers 1, 2 or 3.\n");
          badInput = true;
        } catch (IOException e) {
          throw new IllegalStateException(" Error appending while informing user of bad input", e);
        }
        continue;
      }

      // try to make a move
      try {
        m.move(Integer.parseInt(element1) - 1, Integer.parseInt(element2) - 1);
      } catch (IllegalArgumentException iae) {
        try {
          out.append(iae.getMessage()).append("\n");
          badInput = true;
        } catch (IOException ioe) {
          throw new IllegalStateException("Error while informing user of occupied position.", ioe);
        }
      }

    }

    if (quit) {
      try {
        out.append("Game quit! Ending game state:\n");
        out.append(String.valueOf(m)).append("\n");
      } catch (IOException ioe) {
        throw new IllegalStateException("Error appending while quitting game.", ioe);
      }
      return;
    }

    try {
      out.append(String.valueOf(m)).append("\n");
      out.append("Game is over!");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed while outputting final game state", ioe);
    }

    if (m.getWinner() == Player.X) {
      try {
        out.append(" X wins.");
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed while outputting game winner", ioe);
      }
    } else if (m.getWinner() == Player.O) {
      try {
        out.append(" O wins.");
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed while outputting game winner", ioe);
      }
    } else {
      try {
        out.append(" Tie game.");
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed while outputting game winner", ioe);
      }

    }

  }
}
