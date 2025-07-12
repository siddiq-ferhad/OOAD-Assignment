import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Handle user input and update the game state accordingly
public class Controller {
    private BoardView boardView;
    private Game game; 

    // (Aqra/Siddiq) Constructor takes an instance of the board and board view
    public Controller(Board board, BoardView boardView) {
        this.boardView = boardView;
        this.game = new Game(board, boardView); // Initialize the Game class
        initializeButtonListeners();
        menuBarListener();
    }

    // (Aqra/Siddiq) Constructor takes an instance of the board, board view, and a string parameter
    public Controller(Board board, BoardView boardView, String x) {
        this.boardView = boardView;
        this.game = new Game(board); // Initialize the Game class
        initializeButtonListeners();
        menuBarListener();
    }

    // (Aqra) Initialize button listeners for the board
    private void initializeButtonListeners() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                boardView.addButtonListener(row, col, new ButtonClickListener(row, col));
            }
        }
    }

    // (Izzati) Menu bar listener
    private void menuBarListener(){
        boardView.addNewGameListener(new NewGameListener());
        boardView.addExitGameListener(new ExitGameListener());
    }

    // (Aqra) ActionListener implementation for the board buttons
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
            // Assign the button click handling to the Game class
            game.handleButtonClick(row, col);

            // Update the GUI
            updateView();
        }
    }

    // Siddiq, Izzati
    private class NewGameListener implements ActionListener{
        // Handle the "New Game" menu item click event
        public void actionPerformed(ActionEvent e){
            game.newGame();

            // Update GUI
            boardView.updateGUI();
        }
    }

    // Siddiq, Izzati
    private class ExitGameListener implements ActionListener{
        // Handle the "Exit" menu item click event and exit the program
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    // (Siddiq) Update the GUI based on the current state of the board
    private void updateView() {
        boardView.updateGUI();
    }
}
