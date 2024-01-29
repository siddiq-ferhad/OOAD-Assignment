// (Aqilah) Represent a Time chess piece
public class Time extends Piece {
    public Time(PieceColor color, String iconPath) {
        super(PieceType.TIME, color, iconPath);
    }

    @Override
    // Check if the movement is valid based on piece's logic
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Calculate the absolute differences in row and column positions
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        // Move diagonally (equal row and column differences)
        return rowDiff == colDiff;
    }
}
