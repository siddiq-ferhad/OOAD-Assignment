import javax.swing.*;

// The abstract base class for chess pieces
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

    // Check if a move from one position to another is valid (Subclasses needs to implement)
    public abstract boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol);

    // Getters
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

    // To represent a fixed set of named values
    public enum PieceType {
        HOURGLASS, PLUS, POINT, TIME, SUN
    }

    public enum PieceColor {
        BLUE, YELLOW
    }
}