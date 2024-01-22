import javax.swing.*;

public abstract class Piece {
    private PieceType type;
    private PieceColor color;
    private ImageIcon icon;
    private String iconPath;

    public Piece(PieceType type, PieceColor color, String iconPath) {
        this.type = type;
        this.color = color;
        this.icon = new ImageIcon(iconPath);
        this.iconPath = iconPath;
    }

    public abstract boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol);

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getIconPath() {
        return iconPath;
    }

    public enum PieceType {
        HOURGLASS, PLUS, POINT, TIME, SUN
    }

    public enum PieceColor {
        BLUE, YELLOW
    }

    //public abstract boolean isPathClear(int firstClickRow, int firstClickCol, int row, int col);
}