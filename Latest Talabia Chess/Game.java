public class Game {
    private Board board;
    private Piece.PieceColor currentPlayer = Piece.PieceColor.YELLOW;
    private int firstClickRow = -1;
    private int firstClickCol = -1;

    // Constructor takes an instance of the board
    public Game(Board board) {
        this.board = board;
    }

    // Method to handle button clicks on the board
    public void handleButtonClick(int row, int col) {
        if (firstClickRow == -1 && firstClickCol == -1) {
            handleFirstClick(row, col);
        } else {
            handleSecondClick(row, col);
        }
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
}