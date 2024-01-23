import java.io.*;

public class Game {
    private Board board;
    private Piece.PieceColor currentPlayer = Piece.PieceColor.YELLOW;
    private int firstClickRow = -1;
    private int firstClickCol = -1;

    // Constructor takes an instance of the board
    public Game(Board board, BoardView boardView) {
        this.board = board;
        loadGameStateFromFile();
        boardView.updateGUI();
    }

    // Method to handle button clicks on the board
    public void handleButtonClick(int row, int col) {
        if (firstClickRow == -1 && firstClickCol == -1) {
            handleFirstClick(row, col);
        } else {
            handleSecondClick(row, col);
        }
        saveGameStateToFile();
    }

    // Handle the first click on the board
    private void handleFirstClick(int row, int col) {
        Piece clickedPiece = board.getPiece(row, col);

        // Check if the clicked piece is not null and belongs to the current player's turn
        if (clickedPiece != null && clickedPiece.getColor() == currentPlayer) {
            firstClickRow = row;
            firstClickCol = col;
        } else {
            System.out.println("Invalid first click. Please select a valid piece.");
        }
    }

    // Handle the second click on the board
    private void handleSecondClick(int row, int col) {
        // Check if the second click is a cancellation of the first click
        if (row == firstClickRow && col == firstClickCol) {
            System.out.println("Canceled selection.");
        } else {
            Piece selectedPiece = board.getPiece(firstClickRow, firstClickCol);

            // Check if the move is valid based on piece rules and the board state
            if (isValidMove(selectedPiece, firstClickRow, firstClickCol, row, col)) {
                Piece targetPiece = board.getPiece(row, col);

                // Check if the target position is empty or has an opponent's piece
                if (targetPiece == null || targetPiece.getColor() != currentPlayer) {
                    board.movePiece(firstClickRow, firstClickCol, row, col);
                    switchPlayer();  // Switch the player turn after a successful move
                } else {
                    System.out.println("Invalid move! Cannot capture your own piece.");
                }
            } else {
                System.out.println("Invalid move! Please select a valid target.");
            }
        }

        // Reset the first click position after handling the move
        firstClickRow = -1;
        firstClickCol = -1;
    }

    // Switch the player turn between YELLOW and BLUE
    private void switchPlayer() {
        currentPlayer = (currentPlayer == Piece.PieceColor.YELLOW) ? Piece.PieceColor.BLUE : Piece.PieceColor.YELLOW;
        System.out.println("It's now " + currentPlayer + "'s turn.");
    }

    // Check if the move is valid based on the piece's rules and the board state
    private boolean isValidMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        return piece != null && piece.isValidMove(fromRow, fromCol, toRow, toCol) &&
                board.isPathClear(fromRow, fromCol, toRow, toCol);
    }

    // Save the game state to a file
    public void saveGameStateToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_state.txt", false))) {
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 7; col++) {
                    Piece piece = board.getPiece(row, col);
                    if (piece != null) {
                        writer.write(piece.getType().name() + "\t" + piece.getColor().name() + "\t"
                                + piece.getIconPath());
                    } else {
                        writer.write("EMPTY");
                    }
                    if (col < 6) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    // Load the game state from a file
    public void loadGameStateFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("game_state.txt"))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null && row < 6) {
                String[] tokens = line.split(",");

                for (int col = 0; col < tokens.length; col++) {
                    String[] pieceInfo = tokens[col].split("\t");
                    if (pieceInfo.length == 3) {
                        Piece.PieceType type = Piece.PieceType.valueOf(pieceInfo[0]);
                        Piece.PieceColor color = Piece.PieceColor.valueOf(pieceInfo[1]);
                        String iconPath = pieceInfo[2];

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
                    } else {
                        board.setPiece(row, col, null);
                    }
                }
                row++;
            }
            System.out.println("Saved file found. Loading the previous game...\n");

        } catch (IOException e) {
            System.out.println("No saved file found. Starting a new game...\n");
        }
    }
}