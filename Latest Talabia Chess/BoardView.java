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
    JMenuItem saveItem;
    //JMenuItem loadItem;
    JMenuItem exitItem;
    private int iconSize = 64;

    public BoardView(Board board) {
        this.board = board;
        initializeGUI();
        addComponentListener(this);
    }

    private void initializeGUI() {
        setLayout(new GridLayout(6, 7));
        buttons = new JButton[6][7];
        menuBar = new JMenuBar(); //create menubar

        //create menu in menubar
        menuList = new JMenu("Menu");
        menuBar.add(menuList);

        //create menu item for file
        newGameItem = new JMenuItem("New Game");
        saveItem = new JMenuItem("Save Game");
        //loadItem = new JMenuItem("Load Game");
        exitItem = new JMenuItem("Exit");

        //add menu item to file
        menuList.add(newGameItem);
        menuList.add(saveItem);
        //menuList.add(loadItem);
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
        buttons[row][col].addActionListener(listener);
    }
    public void addNewGameListener(ActionListener NewGameListener){
        newGameItem.addActionListener(NewGameListener);
    }
    public void addSaveGameListener(ActionListener SaveGameListener){
        saveItem.addActionListener(SaveGameListener);
    }
    // public void addLoadGameListener(ActionListener LoadGameListener){
    //     loadItem.addActionListener(LoadGameListener);
    // }
    public void addExitGameListener(ActionListener ExitGameListener){
        exitItem.addActionListener(ExitGameListener);
    }

    public void updateGUI() {
        // Update the icons on the buttons based on the current state of the board
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
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    @Override
    public void componentResized(ComponentEvent e){
        //update the icon size based on the frame size
        iconSize = Math.min(getWidth() / 7, getHeight() / 6);
        //Update all the button images with the new icon size
        updateGUI();
    }

    @Override
    public void componentMoved(ComponentEvent e){
    }

    public void componentShown(ComponentEvent e){
    }

    public void componentHidden(ComponentEvent e){
    }
}
