import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Handle actions and events related to the main menu
public class MenuController {
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
        addListeners();
    }

    // Izzati/Siddiq
    private void addListeners() {
        // Add action listeners to the buttons in MenuView (startButton and loadButton)
        view.addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getStartButton()) {
                    startGame();
                } else if (e.getSource() == view.getLoadButton()) {
                    loadGame();
                }
            }
        });
    }

    // Siddiq
    private void startGame() {
        // Dispose of the main menu view
        view.dispose();
        
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        new Controller(board, boardView, "");
    }

    // Siddiq
    private void loadGame() {
        // Dispose of the main menu view
        view.dispose();
        
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        new Controller(board, boardView);
    }
}
