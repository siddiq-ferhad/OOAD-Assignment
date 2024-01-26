public class Time extends Piece {
    public Time(PieceColor color, String iconPath) {
        super(PieceType.TIME, color, iconPath);
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        return rowDiff == colDiff;
    }
}