// Gavin Rose - rose1751
// Gage Canavan - canav022
public class Knight {
    private int row;
    private int col;
    private boolean isBlack;
    public Knight(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            int colDiff = Math.abs(this.col - endCol);  // determines change in columns
            int rowDiff = Math.abs(this.row - endRow);  // determines change in rows
            if (board.getPiece(endRow, endCol) == null) { // Determines if space is empty and if the move is possible.
                if ((rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1)) {
                    return true;
                }
            } else if ((board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack)) { // Determines if opposite color holds position and if it can be taken.
                if ((rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
