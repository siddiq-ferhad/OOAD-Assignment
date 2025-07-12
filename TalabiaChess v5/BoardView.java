import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

// (Aqra, Izzati) Display the chess board and provide a visual representation of the current game state
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

    // (Aqra, Izzati) Intialize the board with GUI
    private void initializeGUI() {
        // Set up GUI
        setLayout(new GridLayout(6, 7));
        buttons = new JButton[6][7];
        
        // Create menu bar
        menuBar = new JMenuBar(); 

        // Create the menu button in menu bar
        menuList = new JMenu("Menu");
        menuBar.add(menuList);

        // Create the drop-down menu item
        newGameItem = new JMenuItem("New Game");
        exitItem = new JMenuItem("Exit");

        // Add the drop-down menu item to menu
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
                    ImageIcon iconToShow = piece.getIcon();
                    if (piece.getType() == Piece.PieceType.POINT && board.isFlipped()) {
                        iconToShow = rotateIcon(iconToShow);  // Fix Point arrow if board flipped
                    }
                    buttons[row][col].setIcon(resizeIcon(iconToShow, 50, 50));
                }
            }
        }
        
        setTitle("Talabia Chess");
        pack();
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setJMenuBar(menuBar);
    }

    // Aqra, Izzati
    public void addButtonListener(int row, int col, ActionListener listener) {
        buttons[row][col].addActionListener(listener); // Add ActionListener for all rows/cols
    }
    public void addNewGameListener(ActionListener NewGameListener){
        newGameItem.addActionListener(NewGameListener); // Add ActionListener for new game 
    }
    public void addExitGameListener(ActionListener ExitGameListener){
        exitItem.addActionListener(ExitGameListener); // Add ActionListener for exit game
    }

    // Aqra, Izzati
    public void updateGUI() {
        // Update the icons on the buttons based on the current state of the board (after movements)
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null) {
                    ImageIcon iconToShow = piece.getIcon();
                    if (piece.getType() == Piece.PieceType.POINT && board.isFlipped()) {
                        iconToShow = rotateIcon(iconToShow);  // Fix Point arrow if board flipped
                    }
                    buttons[row][col].setIcon(resizeIcon(iconToShow, iconSize, iconSize));
                } else {
                    buttons[row][col].setIcon(null); // Clear the icon if there is no piece
                }
            }
        }
        // Repaint the GUI to reflect the changes
        revalidate();
        repaint();
    }

    // Izzati
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        // Resize the given icon to the specified width and height
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    // (Siddiq) Rotate an ImageIcon 180° (used only for the asymmetric Point piece)
    private ImageIcon rotateIcon(ImageIcon icon) {
        Image img = icon.getImage();
        int w = img.getWidth(null);
        int h = img.getHeight(null);

        BufferedImage rotated = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rotated.createGraphics();
        g2.rotate(Math.PI, w / 2.0, h / 2.0);   // Rotate around centre
        g2.drawImage(img, 0, 0, null);
        g2.dispose();

        return new ImageIcon(rotated);
    }

    @Override
    // Izzati
    public void componentResized(ComponentEvent e){
        // Update the icon size based on the frame size
        iconSize = Math.min(getWidth() / 8, getHeight() / 6);
        // Update all the button images with the new icon size
        updateGUI();
    }

    // (Izzati) Not used in this implementation
    @Override
    public void componentMoved(ComponentEvent e){
    }

    public void componentShown(ComponentEvent e){
    }

    public void componentHidden(ComponentEvent e){
    }
}
