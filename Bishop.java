// Gavin Rose - rose1751
// Gage Canavan - canav022
public class Bishop {
    private int row;
    private int col;
    private boolean isBlack;
    public Bishop(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
                return true;
            }
            if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
                return true;
            }
        }
        return false;
    }
}
