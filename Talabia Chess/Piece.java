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

    // check if a move from one position to another is valid (subclasses needs to implement)
    public abstract boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol);

    // getters
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

    // to represent a fixed set of named values
    public enum PieceType {
        HOURGLASS, PLUS, POINT, TIME, SUN
    }

    public enum PieceColor {
        BLUE, YELLOW
    }
}