import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
        // Load the game state from the text file
        loadGameStateFromFile();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
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
        // Save the game state to a text file
        saveGameStateToFile();

        // Repaint the GUI to reflect the changes
        revalidate();
        repaint();
    }

    private void saveGameStateToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_state.txt", false))) {
            // Write the current state of the game board to the file
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 7; col++) {
                    Piece piece = board.getPiece(row, col);
                    if (piece != null) {
                        // Write information about the piece to the file
                        writer.write(piece.getType().name() + "\t" + piece.getColor().name() + "\t"
                                + piece.getIconPath());
                    } else {
                        // If there is no piece, write an empty line or placeholder
                        writer.write("EMPTY");
                    }
                    // Add a comma to separate pieces, but not after the last column
                    if (col < 6) {
                        writer.write(",");
                    }
                }
                // Move to the next line after each row
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private void loadGameStateFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("game_state.txt"))) {
            String line;
            int row = 0;

            // Read each line from the file
            while ((line = reader.readLine()) != null && row < 6) {
                String[] tokens = line.split(",");

                // Iterate through each column
                for (int col = 0; col < tokens.length; col++) {
                    String[] pieceInfo = tokens[col].split("\t");
                    if (pieceInfo.length == 3) {
                        // Extract type, color and icon path from the split string
                        Piece.PieceType type = Piece.PieceType.valueOf(pieceInfo[0]);
                        Piece.PieceColor color = Piece.PieceColor.valueOf(pieceInfo[1]);
                        String iconPath = pieceInfo[2];

                        // Create a new piece based on the loaded information
                        Piece piece;
                        switch (type) {
                            case HOURGLASS:
                                piece = new Hourglass(color, iconPath);
                                break;
                            case PLUS:
                                piece = new Plus(color, iconPath);
                                break;
                            case POINT:
                                piece = new Point(color, iconPath);
                                break;
                            case TIME:
                                piece = new Time(color, iconPath);
                                break;
                            case SUN:
                                piece = new Sun(color, iconPath);
                                break;
                            default:
                                throw new IllegalArgumentException("Unknown PieceType: " + type);
                        }
                        board.setPiece(row, col, piece);
                        buttons[row][col].setIcon(piece.getIcon());
                    } else {
                        board.setPiece(row, col, null);
                        buttons[row][col].setIcon(null);
                    }
                }
                // Move to the next row
                row++;
            }
            System.out.println("Saved file found. Loading the previous game...\n");

        } catch (IOException e) {
            System.out.println("No saved file found. Starting a new game...\n");
        }
        // Repaint the GUI to reflect the changes
        revalidate();
        repaint();
    }
}