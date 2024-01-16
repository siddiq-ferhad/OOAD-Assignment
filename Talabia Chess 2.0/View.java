import javax.swing.*;
import java.awt.*;

public class View extends JFrame{
    private JFrame frame;
    JButton[][] buttons;


    public View() {
        frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 7));

        buttons = new JButton[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttons[i][j] = new JButton();
                frame.add(buttons[i][j]);
            }
        }

        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        initializePieces();
    }

    public void updateBoard(Board model) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttons[i][j].setText(String.valueOf(model.getPieceAt(i, j)));
            }
        }
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
        buttons[0][0].setIcon(bluePlus); 
        buttons[0][1].setIcon(blueHourglass);
        buttons[0][2].setIcon(blueTime);
        buttons[0][3].setIcon(blueSun);
        buttons[0][4].setIcon(blueTime);
        buttons[0][5].setIcon(blueHourglass);
        buttons[0][6].setIcon(bluePlus);
        buttons[1][0].setIcon(bluePoint);
        buttons[1][1].setIcon(bluePoint);
        buttons[1][2].setIcon(bluePoint);
        buttons[1][3].setIcon(bluePoint);
        buttons[1][4].setIcon(bluePoint);
        buttons[1][5].setIcon(bluePoint);
        buttons[1][6].setIcon(bluePoint);

        // yellow pieces
        buttons[5][0].setIcon(yellowPlus);
        buttons[5][1].setIcon(yellowHourglass);
        buttons[5][2].setIcon(yellowTime);
        buttons[5][3].setIcon(yellowSun);
        buttons[5][4].setIcon(yellowTime);
        buttons[5][5].setIcon(yellowHourglass);
        buttons[5][6].setIcon(yellowPlus);
        buttons[4][0].setIcon(yellowPoint);
        buttons[4][1].setIcon(yellowPoint);
        buttons[4][2].setIcon(yellowPoint);
        buttons[4][3].setIcon(yellowPoint);
        buttons[4][4].setIcon(yellowPoint);
        buttons[4][5].setIcon(yellowPoint);
        buttons[4][6].setIcon(yellowPoint);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }


    
}