public class Board {
    private Piece[][] pieces;

    public Board() {
        // Initialize the 6x7 chessboard
        pieces = new Piece[6][7];
        initializeBoard();
    }

    private void initializeBoard() {
        // Set up the initial positions of chess pieces on the board
        // Example: Placing white pieces on the bottom row
        pieces[0][0] = new Plus(Piece.PieceColor.BLUE, "blue_plus.jpeg");
        pieces[0][1] = new Hourglass(Piece.PieceColor.BLUE, "blue_hourglass.jpeg");
        pieces[0][2] = new Time(Piece.PieceColor.BLUE, "blue_time.jpeg");
        pieces[0][3] = new Sun(Piece.PieceColor.BLUE, "blue_sun.jpeg");
        pieces[0][4] = new Time(Piece.PieceColor.BLUE, "blue_time.jpeg");
        pieces[0][5] = new Hourglass(Piece.PieceColor.BLUE, "blue_hourglass.jpeg");
        pieces[0][6] = new Plus(Piece.PieceColor.BLUE, "blue_plus.jpeg");
        pieces[1][0] = new Point(Piece.PieceColor.BLUE, "blue_point.jpeg");
        pieces[1][1] = new Point(Piece.PieceColor.BLUE, "blue_point.jpeg");
        pieces[1][2] = new Point(Piece.PieceColor.BLUE, "blue_point.jpeg");
        pieces[1][3] = new Point(Piece.PieceColor.BLUE, "blue_point.jpeg");
        pieces[1][4] = new Point(Piece.PieceColor.BLUE, "blue_point.jpeg");
        pieces[1][5] = new Point(Piece.PieceColor.BLUE, "blue_point.jpeg");
        pieces[1][6] = new Point(Piece.PieceColor.BLUE, "blue_point.jpeg");

        
        pieces[5][0] = new Plus(Piece.PieceColor.YELLOW, "yellow_plus.jpeg");
        pieces[5][1] = new Hourglass(Piece.PieceColor.YELLOW, "yellow_hourglass.jpeg");
        pieces[5][2] = new Time(Piece.PieceColor.YELLOW, "yellow_time.jpeg");
        pieces[5][3] = new Sun(Piece.PieceColor.YELLOW, "yellow_sun.jpeg");
        pieces[5][4] = new Time(Piece.PieceColor.YELLOW, "yellow_time.jpeg");
        pieces[5][5] = new Hourglass(Piece.PieceColor.YELLOW, "yellow_hourglass.jpeg");
        pieces[5][6] = new Plus(Piece.PieceColor.YELLOW, "yellow_plus.jpeg");
        pieces[4][0] = new Point(Piece.PieceColor.YELLOW, "yellow_point.jpeg");
        pieces[4][1] =  new Point(Piece.PieceColor.YELLOW, "yellow_point.jpeg");
        pieces[4][2] =  new Point(Piece.PieceColor.YELLOW, "yellow_point.jpeg");
        pieces[4][3] =  new Point(Piece.PieceColor.YELLOW, "yellow_point.jpeg");
        pieces[4][4] =  new Point(Piece.PieceColor.YELLOW, "yellow_point.jpeg");
        pieces[4][5] =  new Point(Piece.PieceColor.YELLOW, "yellow_point.jpeg");
        pieces[4][6] =  new Point(Piece.PieceColor.YELLOW, "yellow_point.jpeg");
    }

    public Piece getPiece(int row, int col) {
        // Get the piece at a specific position on the board
        return pieces[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        pieces[row][col] = piece;
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        // Move a chess piece from one position to another
        Piece pieceToMove = pieces[fromRow][fromCol];
        pieces[toRow][toCol] = pieceToMove;
        pieces[fromRow][fromCol] = null; // Clear the original position
    }

    public boolean isPathClear(int fromRow, int fromCol, int toRow, int toCol) {
        int rowStep = Integer.compare(toRow, fromRow);
        int colStep = Integer.compare(toCol, fromCol);

        for (int i = fromRow + rowStep, j = fromCol + colStep; i != toRow || j != toCol; i += rowStep, j += colStep) {
            if (pieces[i][j] != null) {
                return false;
            }
        }
        return true;
    }
    
    public void resetBoard(){
        //clear current gameplay
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                pieces[row][col] = null;
            }
        }
        initializeBoard();
    }

    public void flipBoard(){
        Piece[][] flipBoard = new Piece[6][7];
        for (int row = 0; row < 6; row++){
            for (int col = 0; col < 7; col++){
                flipBoard[5-row][6-col] = getPiece(row, col);
            }
        }
        setBoard(flipBoard);
    }
    public void setBoard(Piece[][] board){
        this.pieces = board;
    }
}