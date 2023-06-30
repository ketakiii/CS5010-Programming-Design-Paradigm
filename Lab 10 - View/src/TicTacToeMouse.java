package tictactoe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The TicTacToeMouse class defines and implements the actions taken by a mouse click.
 */
public class TicTacToeMouse extends MouseAdapter {

  private TicTacToeController listener;

  /**
   * This is the constructor of the TicTacToeMouse class.
   * @param controller TicTacToeController controller
   */
  public TicTacToeMouse(TicTacToeController controller) {
    this.listener = controller;
  }

  @Override public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);
    int row = e.getY() / 166;
    int col = e.getX() / 166;
    this.listener.handleCellClick(row, col);
  }
}
