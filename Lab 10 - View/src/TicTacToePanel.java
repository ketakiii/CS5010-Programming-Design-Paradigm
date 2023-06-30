package tictactoe;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The TicTacToePanel class implements and defines the Panel of the game board.
 */
public class TicTacToePanel extends JPanel {

  private Player[][] board;

  /**
   * This is the constructor of the TicTacToePanel class.
   */
  public TicTacToePanel() {
    this.setBackground(Color.WHITE);
    this.setVisible(true);
    this.board = new Player[3][3];
  }

  /**
   * This method sets the new board for the game.
   * @param board game board
   */
  public void setBoard(Player[][]board) {
    this.board = board;
    this.repaint();
  }

  @Override public void paintComponent(Graphics g) {
    g.fillRect(166, 0, 2, 500);
    g.fillRect(332, 0, 2, 500);
    g.fillRect(0, 166, 500, 2);
    g.fillRect(0, 332, 500, 2);

    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        Player curr = this.board[row][col];
        if (curr == Player.O) {
          g.fillOval(30 + col * 166, 30 + row * 166, 106, 106);
        } else if (curr == Player.X) {
          g.fillRect(30 + col * 166, 30 + row * 166, 106, 106);
        }
      }
    }
  }

  /*public void addMouseListener(TicTacToeMouse mouse) {

  }

   */
}
