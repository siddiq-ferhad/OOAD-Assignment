// (Aqilah) Represent a Sun chess piece
public class Sun extends Piece {
    public Sun(PieceColor color, String iconPath) {
        super(PieceType.SUN, color, iconPath);
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Calculate the absolute differences in row and column positions
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        // Move to any adjacent square (up, down, left, right, or diagonally)
        return rowDiff <= 1 && colDiff <= 1;
    }
}
