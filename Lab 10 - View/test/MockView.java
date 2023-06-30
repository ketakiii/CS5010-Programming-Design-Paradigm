package tictactoe;

/**
 * The MockView defines and implements the mock view.
 */
public class MockView implements TicTacToeView {

  private TicTacToe model;
  private TicTacToeController controller;

  /**
   * This is the constructor of the MockView class.
   */
  public MockView(TicTacToe model) {
    this.model = model;
  }

  @Override public void addClickListener(TicTacToeController listener) {
    this.controller = listener;
    this.controller.handleCellClick(2, 2);
  }

  @Override public void refresh() {

  }

  @Override public void makeVisible() {

  }

  @Override public void setModel(ReadonlyTttModel model) {

  }
}
