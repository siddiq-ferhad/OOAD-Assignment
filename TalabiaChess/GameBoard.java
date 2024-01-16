import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {

    private JButton[][] chessButtons;

    public GameBoard() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Chess Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set a fixed size for the JFrame
        setSize(800, 600);
        setResizable(false);
        
        setLayout(new GridLayout(6, 7));

        chessButtons = new JButton[6][7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                chessButtons[i][j] = new JButton();
                chessButtons[i][j].setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.BLACK);
                add(chessButtons[i][j]);
            }
        }

        initializePieces();

        setVisible(true);
    }

    private void initializePieces() {
        // Icon --> png image files
        ImageIcon bluePoint = resizeIcon(new ImageIcon("blue_point.png"), 100, 100);
        ImageIcon blueHourglass = resizeIcon(new ImageIcon("blue_hourglass.png"), 100, 100);
        ImageIcon blueTime = resizeIcon(new ImageIcon("blue_time.png"), 100, 100);
        ImageIcon bluePlus = resizeIcon(new ImageIcon("blue_plus.png"), 100, 100);
        ImageIcon blueSun = resizeIcon(new ImageIcon("blue_sun.png"), 100, 100);

        ImageIcon yellowPoint = resizeIcon(new ImageIcon("yellow_point.png"), 100, 100);
        ImageIcon yellowHourglass = resizeIcon(new ImageIcon("yellow_hourglass.png"), 100, 100);
        ImageIcon yellowTime = resizeIcon(new ImageIcon("yellow_time.png"), 100, 100);
        ImageIcon yellowPlus = resizeIcon(new ImageIcon("yellow_plus.png"), 100, 100);
        ImageIcon yellowSun = resizeIcon(new ImageIcon("yellow_sun.png"), 100, 100);

        // Set the icons for the chess pieces
        // blue pieces
        chessButtons[0][0].setIcon(bluePlus); 
        chessButtons[0][1].setIcon(blueHourglass);
        chessButtons[0][2].setIcon(blueTime);
        chessButtons[0][3].setIcon(blueSun);
        chessButtons[0][4].setIcon(blueTime);
        chessButtons[0][5].setIcon(blueHourglass);
        chessButtons[0][6].setIcon(bluePlus);
        chessButtons[1][0].setIcon(bluePoint);
        chessButtons[1][1].setIcon(bluePoint);
        chessButtons[1][2].setIcon(bluePoint);
        chessButtons[1][3].setIcon(bluePoint);
        chessButtons[1][4].setIcon(bluePoint);
        chessButtons[1][5].setIcon(bluePoint);
        chessButtons[1][6].setIcon(bluePoint);

        // yellow pieces
        chessButtons[5][0].setIcon(yellowPlus);
        chessButtons[5][1].setIcon(yellowHourglass);
        chessButtons[5][2].setIcon(yellowTime);
        chessButtons[5][3].setIcon(yellowSun);
        chessButtons[5][4].setIcon(yellowTime);
        chessButtons[5][5].setIcon(yellowHourglass);
        chessButtons[5][6].setIcon(yellowPlus);
        chessButtons[4][0].setIcon(yellowPoint);
        chessButtons[4][1].setIcon(yellowPoint);
        chessButtons[4][2].setIcon(yellowPoint);
        chessButtons[4][3].setIcon(yellowPoint);
        chessButtons[4][4].setIcon(yellowPoint);
        chessButtons[4][5].setIcon(yellowPoint);
        chessButtons[4][6].setIcon(yellowPoint);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    public static void main(String[] args) {
        new GameBoard();
    }
}
