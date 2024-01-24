import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
        addListeners();
    }

    private void addListeners() {
        view.addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == view.getStartButton()) {
                    startGame();
                // } else if (e.getSource() == view.getExitButton()) {
                //     exitGame();
                }
            }
        });
    }

    private void startGame() {
        // Dispose of the main menu view
        view.dispose();
        
        // Create instances of the model and view for the game
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        new Controller(board, boardView);
    }

    // private void exitGame() {
    //     System.exit(0);
    // }
}
