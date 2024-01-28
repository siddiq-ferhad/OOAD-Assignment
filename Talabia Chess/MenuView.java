import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView {
    private JFrame frame;
    private JButton startButton;
    private JButton loadButton;
    //private JButton exitButton;

    public MenuView() {
        initialize();
    }

    // sets up the main frame and initializes GUI
    private void initialize() {
        frame = new JFrame("Talabia Chess");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Set the background image
        ImageIcon backgroundImg = new ImageIcon("chessboard.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImg);
        backgroundLabel.setLayout(new BorderLayout());

        // Add the panel to the background
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        backgroundLabel.add(panel, BorderLayout.CENTER);

        panel.setOpaque(false);
        frame.getContentPane().add(backgroundLabel, BorderLayout.CENTER);


        JLabel titleLabel = new JLabel("Talabia Chess");
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 70));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentY(Component.TOP_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE);

        startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("Arial", Font.BOLD, 15));
        startButton.setPreferredSize(new Dimension(500,50));

        loadButton = new JButton("Load Game");
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.setFont(new Font("Arial", Font.BOLD, 15));
        loadButton.setPreferredSize(new Dimension(500, 50));

        // exitButton = new JButton("Exit");
        // exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(120));
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(15));
        panel.add(loadButton);
        panel.add(Box.createVerticalStrut(70));
        // panel.add(exitButton);
        // panel.add(Box.createVerticalGlue());

        frame.getContentPane().add(backgroundLabel,BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void addButtonListener(ActionListener listener) {
        startButton.addActionListener(listener);
        loadButton.addActionListener(listener);
    }

    // Getter for startButton
    public JButton getStartButton() {
        return startButton;
    }

    // Getter for loadButton
    public JButton getLoadButton() {
        return loadButton;
    }

    // dispose of the menu's frame
    public void dispose() {
        frame.dispose();
    }
}