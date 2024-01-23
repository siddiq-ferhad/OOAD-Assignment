import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BoardView extends JFrame {
    private Board board;
    private JButton[][] buttons;

    public BoardView(Board board) {
        this.board = board;
        initializeGUI();
    }

    private void initializeGUI() {
        setLayout(new GridLayout(6, 7));
        buttons = new JButton[6][7];

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
                    buttons[row][col].setIcon(piece.getIcon());
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
    }

    public void addButtonListener(int row, int col, ActionListener listener) {
        buttons[row][col].addActionListener(listener);
    }

    public void updateGUI() {
        // Update the icons on the buttons based on the current state of the board
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null) {
                    buttons[row][col].setIcon(piece.getIcon());
                } else {
                    buttons[row][col].setIcon(null); // Clear the icon if there is no piece
                }
            }
        }
        // Repaint the GUI to reflect the changes
        revalidate();
        repaint();
    }
}