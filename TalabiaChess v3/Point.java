public class Point extends Piece {
    private boolean hasMovedInReverse;

    public Point(PieceColor color, String iconPath) {
        super(PieceType.POINT, color, iconPath);
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = toRow - fromRow;
        int colDiff = Math.abs(toCol - fromCol);

        // Forward movement
        if (!hasMovedInReverse && rowDiff < 0 && rowDiff >= -2 && colDiff == 0) {
            return true;
        }
        // Reverse movement when reaching the other end
        if (fromRow == 0 && rowDiff > 0 && rowDiff <= 2 && colDiff == 0) {
            hasMovedInReverse = true;
            return true;
        }
        // No other movements allowed when in reverse
        return hasMovedInReverse && rowDiff > 0 && rowDiff <= 2 && colDiff == 0;
    }
}