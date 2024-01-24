import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Board board;
    private BoardView boardView;
    private int firstClickRow = -1;
    private int firstClickCol = -1;
    // private Piece piece;

    public Controller(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;

        // Add action listeners to the buttons in the view
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                boardView.addButtonListener(row, col, new ButtonClickListener(row, col));
            }
        }
    }

    // ActionListener implementation for the board buttons
    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        // public void actionPerformed(ActionEvent e) {
        // if (firstClickRow == -1 && firstClickCol == -1) {
        // // First click, just store the position
        // firstClickRow = row;
        // firstClickCol = col;
        // } else {
        // // Second click, perform the move
        // board.movePiece(firstClickRow, firstClickCol, row, col);

        // // Reset the first click position
        // firstClickRow = -1;
        // firstClickCol = -1;

        // // Update the GUI to reflect the changes
        // boardView.updateGUI();
        // }
        // }

        public void actionPerformed(ActionEvent e) {

            if (firstClickRow == -1 && firstClickCol == -1) {
                // First click, just store the position
                firstClickRow = row;
                firstClickCol = col;
            } else {
                // Second click, check if the move is valid
                Piece clickedPiece = board.getPiece(firstClickRow, firstClickCol);

                if (clickedPiece.getType() == Piece.PieceType.HOURGLASS) {

                    if (clickedPiece != null && clickedPiece.isValidMove(firstClickRow, firstClickCol, row, col)
                            ) {
                        // Valid move, perform the move
                        board.movePiece(firstClickRow, firstClickCol, row, col);

                        // Reset the first click position
                        firstClickRow = -1;
                        firstClickCol = -1;

                        // Update the GUI to reflect the changes
                        boardView.updateGUI();
                    } else {
                        // Invalid move, handle accordingly (display a message, etc.)
                        System.out.println("Invalid move!");
                        // Optionally, you may want to reset the first click position here as well
                        // firstClickRow = -1;
                        // firstClickCol = -1;
                    }
                } else {
                    if (clickedPiece.getType() != Piece.PieceType.HOURGLASS) {

                        if (clickedPiece != null && clickedPiece.isValidMove(firstClickRow, firstClickCol, row, col)
                                && board.isPathClear(firstClickRow, firstClickCol, row, col)) {
                            // Valid move, perform the move
                            board.movePiece(firstClickRow, firstClickCol, row, col);
    
                            // Reset the first click position
                            firstClickRow = -1;
                            firstClickCol = -1;
    
                            // Update the GUI to reflect the changes
                            boardView.updateGUI();
                        } else {
                            // Invalid move, handle accordingly (display a message, etc.)
                            System.out.println("Invalid move!");
                            // Optionally, you may want to reset the first click position here as well
                            // firstClickRow = -1;
                            // firstClickCol = -1;
                        }
                    }
                }
            }
        }

        // public void actionPerformed(ActionEvent e) {
        // Piece clickedPiece = board.getPiece(firstClickRow, firstClickCol);

        // if (clickedPiece.getType() == Piece.PieceType.HOURGLASS) {

        // if (firstClickRow == -1 && firstClickCol == -1) {
        // // First click, just store the position
        // firstClickRow = row;
        // firstClickCol = col;
        // } else {
        // // Second click, check if the move is valid
        // //Piece clickedPiece = board.getPiece(firstClickRow, firstClickCol);

        // if (clickedPiece != null && clickedPiece.isValidMove(firstClickRow,
        // firstClickCol, row, col) ) {
        // // Valid move, perform the move
        // board.movePiece(firstClickRow, firstClickCol, row, col);

        // // Reset the first click position
        // firstClickRow = -1;
        // firstClickCol = -1;

        // // Update the GUI to reflect the changes
        // boardView.updateGUI();
        // } else {
        // // Invalid move, handle accordingly (display a message, etc.)
        // System.out.println("Invalid move!");
        // // Optionally, you may want to reset the first click position here as well
        // // firstClickRow = -1;
        // // firstClickCol = -1;
        // }
        // }
        // } else {

        // if (firstClickRow == -1 && firstClickCol == -1) {
        // // First click, just store the position
        // firstClickRow = row;
        // firstClickCol = col;
        // } else {
        // // Second click, check if the move is valid
        // //Piece clickedPiece = board.getPiece(firstClickRow, firstClickCol);

        // if (clickedPiece != null && clickedPiece.isValidMove(firstClickRow,
        // firstClickCol, row, col) && board.isPathClear(firstClickRow, firstClickCol,
        // row, col)) {
        // // Valid move, perform the move
        // board.movePiece(firstClickRow, firstClickCol, row, col);

        // // Reset the first click position
        // firstClickRow = -1;
        // firstClickCol = -1;

        // // Update the GUI to reflect the changes
        // boardView.updateGUI();
        // } else {
        // // Invalid move, handle accordingly (display a message, etc.)
        // System.out.println("Invalid move!");
        // // Optionally, you may want to reset the first click position here as well
        // // firstClickRow = -1;
        // // firstClickCol = -1;
        // }
        // }
        // }

        // }
    }

    public static void main(String[] args) {
        // Instantiate the model, view, and controller
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        new Controller(board, boardView);
    }

}
