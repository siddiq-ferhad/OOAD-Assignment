public class Point extends Piece {
    public Point(PieceColor color, String iconPath) {
        super(PieceType.POINT, color, iconPath);
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = toRow - fromRow;
        int colDiff = Math.abs(toCol - fromCol);

        // Check if the move is valid based on the color of the piece
        if (getColor() == PieceColor.BLUE) {
            return (rowDiff > 0 && rowDiff <= 2 && colDiff == 0) ||
                    (rowDiff < 0 && rowDiff >= -2 && colDiff == 0);

        } else {
            return (rowDiff < 0 && rowDiff >= -2 && colDiff == 0) ||
                    (rowDiff > 0 && rowDiff <= 2 && colDiff == 0);

        }
    }
}