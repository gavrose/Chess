// Gavin Rose - rose1751
// Gage Canavan - canav022

public class King {
    private int row;
    private int col;
    private boolean isBlack;
    public King(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        int rowDiff = Math.abs(this.row - endRow);
        int colDiff = Math.abs(this.col - endCol);
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)){
            return (rowDiff <= 1 && colDiff <= 1);
        }
        else if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return (rowDiff <= 1 && colDiff <= 1);
        }
        return false;
    }
}
