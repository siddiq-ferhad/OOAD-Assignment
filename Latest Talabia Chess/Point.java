public class Point extends Piece {
    public Point(PieceColor color, String iconPath) {
        super(PieceType.POINT, color, iconPath);
    }

    // @Override
    // public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    // int rowDiff = toRow - fromRow;
    // int colDiff = Math.abs(toCol - fromCol);

    // if (getColor() == PieceColor.BLUE) {
    // return rowDiff > 0 && rowDiff <= 1 && colDiff == 0;
    // } else {
    // return rowDiff < 0 && rowDiff >= -1 && colDiff == 0;
    // }
    // }

    // @Override
    // public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    // int rowDiff = toRow - fromRow;
    // int colDiff = Math.abs(toCol - fromCol);

    // return rowDiff > 0 && rowDiff <= 1 && colDiff == 0;

    // //return rowDiff < 0 && rowDiff >= -1 && colDiff == 0;
    // }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = toRow - fromRow;
        int colDiff = Math.abs(toCol - fromCol);

        // // Check if the move is within the board boundaries
        // if (toRow < 0 || toRow >= BOARD_ROWS || toCol < 0 || toCol >= BOARD_COLS) {
        // return false;
        // }

        // Check if the move is valid based on the color of the piece
        if (getColor() == PieceColor.BLUE) {
            // Blue piece can only move forward and turn around at the end of the board
            return (rowDiff > 0 && rowDiff <= 1 && colDiff == 0 ) ||
                    (fromRow == 6 && rowDiff < 0 && rowDiff >= -2 && colDiff == 0);
        } else {
            // Yellow piece can only move forward and turn around at the end of the board
            return (rowDiff < 0 && rowDiff >= -2 && colDiff == 0) ||
                    // (fromRow == 0 && rowDiff > 0 && rowDiff <= 2  && colDiff == 0) || 
                    (fromRow >= 0 && rowDiff > 0 && rowDiff <= 2  && colDiff == 0);
        }
    }

    // private boolean noPieceBlocking(int fromRow, int toRow, int fromCol) {
    //     int step = (toRow > fromRow) ? 1 : -1;
    //     for (int i = fromRow + step; i != toRow; i += step) {
    //     // Assuming your board is represented by a 2D array 'board'
    //     if (pieceIsPresentAt(i, fromCol)) {
    //         return false;  // There is a piece in the way
    //     }
    // }
    // return true;  // No pieces blocking the way
    // }

    // private boolean pieceIsPresentAt(int i, int fromCol) {
    //     return false;
        
    // }

    // if (fromRow - toRow <= 1) {
    // // Check if the move is within 1 or 2 steps
    // if (fromCol == toCol || Math.abs(fromCol - toCol) == 1) {
    // return rowDiff > 0 && rowDiff <= 1 && colDiff == 0; // Valid move
    // }
    // }
    // // Check if the move is backward
    // if (fromRow - toRow <= -1) {
    // // Check if the move is within 1 or 2 steps
    // if (fromCol == toCol || Math.abs(fromCol - toCol) == 1) {
    // return rowDiff < 0 && rowDiff >= -1 && colDiff == 0;
    // // Valid move
    // }
    // }
    // return false;

}
