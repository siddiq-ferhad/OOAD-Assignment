public class Sun extends Piece {
    public Sun(PieceColor color, String iconPath) {
        super(PieceType.SUN, color, iconPath);
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        return rowDiff <= 1 && colDiff <= 1;
    }
}
