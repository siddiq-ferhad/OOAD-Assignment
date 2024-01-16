public class Controller {
    private Board model;
    private View view;

    public Controller(Board model, View view) {
        this.model = model;
        this.view = view;

        // Add ActionListener to each button
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                final int row = i;
                final int col = j;
                view.buttons[i][j].addActionListener(e -> handleButtonClick(row, col));
            }
        }
    }

    private void handleButtonClick(int row, int col) {
        // Handle button click, update the model and view
        char currentPiece = model.getPieceAt(row, col);
        char newPiece = (currentPiece == ' ') ? 'X' : ' '; // Toggle between 'X' and ' '
        model.setPieceAt(row, col, newPiece);
        view.updateBoard(model);
    }

    public static void main(String[] args) {
        Board model = new Board();
        View view = new View();
        new Controller(model, view);
    }
}