// Gavin Rose - rose1751
// Gage Canavan - canav022
public class Queen {
    private int row;
    private int col;
    private boolean isBlack;
    public Queen(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            if (this.row == endRow) { // Horizontal move
                return board.verifyHorizontal(this.row, this.col, endRow, endCol);
            } else if (this.col == endCol) { // Vertical move
                return board.verifyVertical(this.row, this.col, endRow, endCol);
            }
            if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
                return true;
            }
            if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
                return (this.col == endCol || this.row == endRow || board.verifyDiagonal(this.row, this.col, endRow, endCol));
            }

        }
        return false;
    }
}
