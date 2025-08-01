import java.io.*;
import javax.swing.JOptionPane;

// Represent the game logic and state
public class Game {
    private Board board;
    private Piece.PieceColor currentPlayer = Piece.PieceColor.YELLOW;
    private int firstClickRow = -1;
    private int firstClickCol = -1;
    private int moveCounter = 0;

    // (Siddiq) Constructor takes an instance of the board
    public Game(Board board) {
        this.board = board;
        System.out.println("\nIt's now " + currentPlayer + "'s turn.");
    }

    // (Siddiq) Constructor takes instances of the board and board view
    public Game(Board board, BoardView boardView) {
        this.board = board;
        loadGame();
        boardView.updateGUI();
        System.out.println("\nIt's now " + currentPlayer + "'s turn.");
    }

    // (Siddiq/Luqman) Method to handle button clicks on the board (Can differentiate 1st click & 2nd click)
    public void handleButtonClick(int row, int col) {
        if (firstClickRow == -1 && firstClickCol == -1) {
            handleFirstClick(row, col);
        } else {
            handleSecondClick(row, col);
        }
        saveGame();
    }

    // (Siddiq) Handle the first click on the board
    private void handleFirstClick(int row, int col) {
        Piece clickedPiece = board.getPiece(row, col);

        // Check if the clicked piece is not null and belongs to the current player's turn
        if (clickedPiece != null && clickedPiece.getColor() == currentPlayer) {
            firstClickRow = row;
            firstClickCol = col;
        } else {
            System.out.println("Invalid click. Please select a valid piece.");
        }
    }

    // (Siddiq) Handle the second click on the board
    private void handleSecondClick(int row, int col) {
        // Check if the second click is a cancellation of the first click
        if (row == firstClickRow && col == firstClickCol) {
            System.out.println("Cancelled selection.");
        } else {
            Piece selectedPiece = board.getPiece(firstClickRow, firstClickCol);

            // Check if the move is valid based on piece rules and the board state
            if (isValidMove(selectedPiece, firstClickRow, firstClickCol, row, col)) {
                Piece targetPiece = board.getPiece(row, col);

                // Check if the target position is empty or has an opponent's piece
                if (targetPiece == null || targetPiece.getColor() != currentPlayer) {
                    board.movePiece(firstClickRow, firstClickCol, row, col);
                    switchPlayer(); // Switch the player turn after a successful move
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

    // (Siddiq) Switch the player turn between YELLOW and BLUE, flip the game board, and check for winning condition
    private void switchPlayer() {
        currentPlayer = (currentPlayer == Piece.PieceColor.YELLOW) ? Piece.PieceColor.BLUE : Piece.PieceColor.YELLOW;
        board.flipBoard(); // Flip the board after switching the player turn
        System.out.println("It's now " + currentPlayer + "'s turn.");

        moveCounter++;

        // Check for total moves (To switch Plus and Time pieces)
        checkMoves();

        // Check if the winning condition has been fulfilled
        checkForWinningCondition();
    }

    // (Siddiq/Aqilah) Check if the move is valid based on the piece's rules and the board state
    private boolean isValidMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        Piece clickedPiece = board.getPiece(firstClickRow, firstClickCol);

        // Special rule for Hourglass (Can skip pieces)
        if (clickedPiece.getType() == Piece.PieceType.HOURGLASS) {
            return piece != null && piece.isValidMove(fromRow, fromCol, toRow, toCol);
        } else {
            return piece != null && piece.isValidMove(fromRow, fromCol, toRow, toCol) &&
                    board.isPathClear(fromRow, fromCol, toRow, toCol);
        }
    }

    // (Izzati) Start a new game
    public void newGame() {
        clearGameStateFile();
        board.resetBoard(); // Reset the board to initial state
        moveCounter = 0;
        currentPlayer = Piece.PieceColor.YELLOW;

        firstClickRow = -1;
        firstClickCol = -1;

        System.out.println("\nYou started a new game!");
        System.out.println("\nIt's now " + currentPlayer + "'s turn.");
    }

    // (Luqman/Izzati) Save the game state to a file
    public void saveGame() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_state.txt", false))) {
            // Save the current player information and turn counter
            writer.write(currentPlayer.name());
            writer.newLine();
            writer.write(String.valueOf(moveCounter));
            writer.newLine();

            // Save the piece information
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

    // (Luqman/Izzati) Load the game state from a file
    public void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader("game_state.txt"))) {
            // Load the current player information and turn counter
            String currentPlayerInfo = reader.readLine();
            String moveCounterInfo = reader.readLine();
            if (currentPlayerInfo != null) {
                currentPlayer = Piece.PieceColor.valueOf(currentPlayerInfo);
                moveCounter = Integer.parseInt(moveCounterInfo);
            }

            // Load the piece information
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
            System.out.println("\nSaved file found. Loading the previous game...");

        } catch (IOException e) {
            System.out.println("No saved file found. Starting a new game...\n");
        }
    }

    // (Siddiq) Check for the winning condition
    private void checkForWinningCondition() {
        if (!isSunPieceOnBoard(Piece.PieceColor.YELLOW)) {
            System.out.println("\nBLUE wins! The Sun piece has been captured.");
            displayWinner("BLUE");

        } else if (!isSunPieceOnBoard(Piece.PieceColor.BLUE)) {
            System.out.println("\nYELLOW wins! The Sun piece has been captured.");
            displayWinner("YELLOW");
        }
    }

    // (Siddiq) Method to display the winner and options to start a new game or quit
    private void displayWinner(String winner) {
        String message = winner + " wins! The Sun piece has been captured.";
        Object[] options = {"Restart", "Quit"};
        int choice = JOptionPane.showOptionDialog(null, message, "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            newGame();
        } else {
            System.exit(0);
        }
    }
    
    // (Siddiq) Check if the Sun piece of the specified color is still on the board (Winning rule)
    private boolean isSunPieceOnBoard(Piece.PieceColor color) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.getType() == Piece.PieceType.SUN && piece.getColor() == color) {
                    return true; // Sun piece found, game continues
                }
            }
        }
        return false; // Sun piece not found, game ends
    }

    // (Izzati) Clear the text file (Saved game)
    private void clearGameStateFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("game_state.txt"))) {
            writer.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // (Siddiq) Switch Plus and Time pieces
    private void switchPieces() {
        System.out.println("\nTime and Plus pieces has been switched!\n");
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                Piece piece = board.getPiece(row, col);
    
                if (piece != null) {
                    if (piece.getType() == Piece.PieceType.TIME) {
                        convertTimePiece(piece, row, col);
                    } else if (piece.getType() == Piece.PieceType.PLUS) {
                        convertPlusPiece(piece, row, col);
                    }
                }
            }
        }
    }
    
    // (Siddiq) Convert Time piece to Plus piece
    private void convertTimePiece(Piece timePiece, int row, int col) {
        Piece.PieceColor color = timePiece.getColor();
        String plusIconPath = (color == Piece.PieceColor.BLUE) ? "blue_plus.jpeg" : "yellow_plus.jpeg";
        board.setPiece(row, col, new Plus(color, plusIconPath));
    }
    
    // (Siddiq) Convert Plus piece to Time piece
    private void convertPlusPiece(Piece plusPiece, int row, int col) {
        Piece.PieceColor color = plusPiece.getColor();
        String timeIconPath = (color == Piece.PieceColor.BLUE) ? "blue_time.jpeg" : "yellow_time.jpeg";
        board.setPiece(row, col, new Time(color, timeIconPath));
    }    

    // (Siddiq) Check if the total moves has fulfilled the specified condition
    private void checkMoves() {
        // Check if four moves have passed
        if (moveCounter % 4 == 0) {
            switchPieces();
        }
    }    
}
