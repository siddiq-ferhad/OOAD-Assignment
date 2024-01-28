import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

public class BoardView extends JFrame implements ComponentListener {
    private Board board;
    private JButton[][] buttons;
    private JMenuBar menuBar;
    JMenu menuList;
    JMenuItem newGameItem;
    JMenuItem exitItem;
    private int iconSize = 64;

    public BoardView(Board board) {
        this.board = board;
        initializeGUI();
        addComponentListener(this);
    }

    private void initializeGUI() {
        // set ups GUI
        setLayout(new GridLayout(6, 7));
        buttons = new JButton[6][7];
        menuBar = new JMenuBar(); //create menubar

        // Create menu in menu bar
        menuList = new JMenu("Menu");
        menuBar.add(menuList);

        // Create menu item for file
        newGameItem = new JMenuItem("New Game");
        exitItem = new JMenuItem("Exit");

        // Add menu item to file
        menuList.add(newGameItem);
        menuList.add(exitItem);


        // Create buttons for each position on the chessboard
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setBackground(Color.WHITE);
                buttons[row][col].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 3));
                add(buttons[row][col]);

                // Set the button's icon based on the piece at this position
                Piece piece = board.getPiece(row, col);
                if (piece != null) {
                    buttons[row][col].setIcon(resizeIcon(piece.getIcon(), 50, 50));
                }
            }
        }
        
        setTitle("Talabia Chess");
        pack();
        setSize(800, 800);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setJMenuBar(menuBar);

    }

    public void addButtonListener(int row, int col, ActionListener listener) {
        buttons[row][col].addActionListener(listener); // add ActionListener for all rows/cols
    }
    public void addNewGameListener(ActionListener NewGameListener){
        newGameItem.addActionListener(NewGameListener); // add ActionListener for new game 
    }
    public void addExitGameListener(ActionListener ExitGameListener){
        exitItem.addActionListener(ExitGameListener); // add ActionListener for exit game
    }

    public void updateGUI() {
        // Update the icons on the buttons based on the current state of the board (after movements)
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null) {
                    buttons[row][col].setIcon(resizeIcon(piece.getIcon(), iconSize, iconSize));
                } else {
                    buttons[row][col].setIcon(null); // Clear the icon if there is no piece
                }
            }
        }
        // Repaint the GUI to reflect the changes
        revalidate();
        repaint();
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        // resize the given icon to the specified width and height
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    @Override
    public void componentResized(ComponentEvent e){
        //update the icon size based on the frame size
        iconSize = Math.min(getWidth() / 8, getHeight() / 6);
        //Update all the button images with the new icon size
        updateGUI();
    }

    // not used in this implementation
    @Override
    public void componentMoved(ComponentEvent e){
    }

    public void componentShown(ComponentEvent e){
    }

    public void componentHidden(ComponentEvent e){
    }
}