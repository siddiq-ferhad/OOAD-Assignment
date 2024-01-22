import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView {
    private JFrame frame;
    private JButton startButton;
    private JButton exitButton;

    public MenuView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Talabia Chess");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Talabia Chess");
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 70));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);
        panel.add(Box.createVerticalGlue());

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }

    public void addButtonListener(ActionListener listener) {
        startButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }

    // Getter for startButton
    public JButton getStartButton() {
        return startButton;
    }

    // Getter for exitButton
    public JButton getExitButton() {
        return exitButton;
    }

    public void dispose() {
        frame.dispose();
    }
}