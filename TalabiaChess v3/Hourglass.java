public class Hourglass extends Piece {
    public Hourglass(PieceColor color, String iconPath) {
        super(PieceType.HOURGLASS, color, iconPath);
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}