public class Board {
    private char[][] board;

    public Board() {
        board = new char[6][7];
        // Initialize the board with empty spaces
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public char getPieceAt(int row, int col) {
        return board[row][col];
    }

    public void setPieceAt(int row, int col, char piece) {
        board[row][col] = piece;
    }
}