package tictactoe;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class implements the TicTacToeView interface and its methods.
 */
public class TicTacToeGuiView extends JFrame implements TicTacToeView, ActionListener {

  private TicTacToeController controller;
  private ReadonlyTttModel model;
  private JMenuBar menuBar;
  private JMenu fileMenu;
  private JMenuItem newGame;
  private JMenuItem close;
  private TicTacToePanel gamePanel;
  private JPanel buttonPanel;
  private JButton exit;
  private JTextField input;
  private JButton move;

  /**
   * The constructor of the TicTacToeGuiView class.
   * @param model ReadonlyTttModel model
   */
  public TicTacToeGuiView(ReadonlyTttModel model) {
    super("Tic Tac Toe");
    this.model = model;
    this.setSize(500, 580);
    //this.setLayout(new FlowLayout());
    //this.setBackground(Color.WHITE);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.menuBar = new JMenuBar();
    this.menuBar.setName("MenuBar");
    this.setJMenuBar(this.menuBar);

    this.fileMenu = new JMenu("File");
    this.menuBar.setName("File");
    this.menuBar.add(fileMenu);

    this.newGame = new JMenuItem("newGame");
    this.newGame.setName("newGame");
    this.newGame.addActionListener(this);
    this.fileMenu.add(this.newGame);

    this.close = new JMenuItem("Close");
    this.close.setName("Close");
    this.close.addActionListener(this);
    this.fileMenu.add(this.close);

    this.buttonPanel = new JPanel();
    this.buttonPanel.setSize(500, 30);

    this.exit = new JButton("Exit");
    this.buttonPanel.add(this.exit);
    this.exit.addActionListener(this);
    this.exit.setName("Close");

    this.input = new JTextField(5);
    this.buttonPanel.add(this.input);

    this.move = new JButton("Move");
    //this.buttonPanel.add(this.move);
    this.move.setName("Move");
    this.move.addActionListener(this);
    this.buttonPanel.add(this.move);

    //this.add(this.buttonPanel);
    this.buttonPanel.setVisible(true);

    this.gamePanel = new TicTacToePanel();
    this.gamePanel.setBounds(0, 50, 500, 500);
    this.add(this.gamePanel);

    this.setVisible(true);
  }

  @Override public void addClickListener(TicTacToeController listener) {
    this.controller = listener;
    this.gamePanel.addMouseListener(new TicTacToeMouse(listener));
  }

  @Override public void refresh() {
    this.gamePanel.setBoard(this.model.getBoard());
    this.repaint();
  }

  @Override public void makeVisible() {

  }

  @Override public void setModel(ReadonlyTttModel newModel) {
    this.model = newModel;
    this.gamePanel.setBoard(this.model.getBoard());
    this.gamePanel.repaint();
  }

  @Override public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof Component) {
      Component c = (Component) e.getSource();
      if (c.getName().equals("Close")) {
        System.exit(0);
      }
      if (c.getName().equals("Move")) {
        String[] stringCoords = this.input.getText().split(" ");
        int row = Integer.parseInt(stringCoords[0]);
        int col = Integer.parseInt(stringCoords[1]);
        this.controller.handleCellClick(row, col);
        this.input.setText("");
      }
      if (c.getName().equals("newGame")) {
        this.controller.restartGame();
      }
    }
  }
}
