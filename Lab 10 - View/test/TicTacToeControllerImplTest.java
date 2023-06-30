package tictactoe;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * This class tests the TicTacToeControllerImpl and its methods.
 */
public class TicTacToeControllerImplTest {

  TicTacToe model = new MockModel();
  TicTacToeView view = new MockView(model);
  TicTacToeControllerImpl ticTacToeController = new TicTacToeControllerImpl(view, model);

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    new TicTacToeControllerImpl(new TicTacToeGuiView(null), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullView() {
    new TicTacToeControllerImpl(null, new TicTacToeModel());
  }

  @Test public void testHandleCellClick() {
    ticTacToeController.handleCellClick(2, 2);
    assertThrows(IllegalArgumentException.class, () -> this.model.move(2, 2));
    ticTacToeController.handleCellClick(1, 2);
    assertThrows(IllegalArgumentException.class, () -> this.model.move(1, 2));
  }

  @Test public void testOutOfBoundsHandleCellClick() {
    assertThrows(IllegalArgumentException.class, () ->
        ticTacToeController.handleCellClick(3, 2));
    assertThrows(IllegalArgumentException.class, () ->
        ticTacToeController.handleCellClick(-1, 0));
  }
}