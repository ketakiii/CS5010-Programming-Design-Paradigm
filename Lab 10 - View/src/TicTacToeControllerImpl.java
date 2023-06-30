package tictactoe;

import javax.swing.JOptionPane;

/**
 * The TicTacToeControllerImpl class that implements the TicTacToeController interface and its
 * methods.
 */
public class TicTacToeControllerImpl implements TicTacToeController {

  private TicTacToe model;
  private TicTacToeView view;

  /**
   * This is the constructor of the TicTacToeControllerImpl class.
   * @param tttView TicTacToeView
   * @param tttModel TicTacToe model
   * @throws IllegalArgumentException when either the view or the model is null
   */
  public TicTacToeControllerImpl(TicTacToeView tttView, TicTacToe tttModel) throws
      IllegalArgumentException {
    if (tttView == null || tttModel == null) {
      throw new IllegalArgumentException("Model or View can not be null");
    }
    this.view = tttView;
    this.model = tttModel;
  }

  @Override public void playGame() {
    this.view.addClickListener(this);
  }

  @Override public void handleCellClick(int row, int col) {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Row and Column can not be less than 0");
    }
    if (row > 2 || col > 2) {
      throw new IllegalArgumentException("Row and Column can not be more than 2");
    }
    try {
      this.model.move(row, col);
    } catch (IllegalStateException e) {
      JOptionPane.showMessageDialog(null, "The game is over, "
          + "so you can not make a move.");
    } catch (IllegalArgumentException iae) {
      JOptionPane.showMessageDialog(null, "The position is already "
          + "occupied.");
    }

    this.view.refresh();
    if (this.model.isGameOver()) {
      if (this.model.getWinner() != null) {
        JOptionPane.showMessageDialog(null, "The game is over. "
            + "The winner is " + this.model.getWinner());
      } else {
        JOptionPane.showMessageDialog(null, "The game is over. "
            + "Tie game");
      }
    }
  }

  @Override public void restartGame() {
    this.model = new TicTacToeModel();
    this.view.setModel(model);
  }
}
