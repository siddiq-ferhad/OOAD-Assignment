import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private BoardView boardView;
    private Game game; 

    // Constructor takes an instance of the board and board view
    public Controller(Board board, BoardView boardView) {
        this.boardView = boardView;
        this.game = new Game(board, boardView); // Initialize the Game class
        initializeButtonListeners();
    }

    // Initialize button listeners for the board
    private void initializeButtonListeners() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                boardView.addButtonListener(row, col, new ButtonClickListener(row, col));
            }
        }
    }

    // ActionListener implementation for the board buttons
    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        // Constructor for the ButtonClickListener
        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        // Handle the button click event
        @Override
        public void actionPerformed(ActionEvent e) {
            // Delegate the button click handling to the Game class
            game.handleButtonClick(row, col);

            // Update the GUI
            updateView();
        }
    }

    // Update the view based on the current state of the model (Board)
    private void updateView() {
        boardView.updateGUI();
    }
}