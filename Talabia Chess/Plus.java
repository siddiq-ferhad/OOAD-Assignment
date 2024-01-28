// rules for Plus piece

public class Plus extends Piece {
    public Plus(PieceColor color, String iconPath) {
        super(PieceType.PLUS, color, iconPath);
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        // move either vertically or horizontally, but not diagonally
        return fromRow == toRow || fromCol == toCol;
    }
}