// Gavin Rose - rose1751
// Gage Canavan - canav022

import static java.lang.Math.abs;

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        //piece.setPosition(row,col);
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. A constraint of a legal move is:
    // - there exists a Piece at (startRow, startCol) and the destination square is seizable.
    // Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move is legal,
    // and to execute the move if it is.
    // Your Game class should not directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece. 
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece current_piece = getPiece(startRow, startCol);
        if (board[startRow][startCol] == null){
            return false;
        } else {
            if (current_piece.isMoveLegal(this, endRow, endCol)) {
                this.setPiece(startRow, startCol, null); // set where the piece moved from to null
                this.setPiece(endRow, endCol, current_piece); // set the new location to
                current_piece.setPosition(endRow, endCol);
                return true;
            }
            System.out.println("Invalid move.");
            return false;
        }
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int numKings = 0;
        // checks every piece to see if both kings are on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && (board[i][j].character == '\u2654' || board[i][j].character == '\u265a')) {
                    numKings++;
                }
            }
        }
        if (numKings < 2) { // if both kings are not on the board end the game
            return true;
        }
        return false;
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                setPiece(i, j, null);
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    // - where 'start' = (startRow, startCol) and 'end' = (endRow, endCol)
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 8 && startCol < 8 && endRow < 8 && endCol < 8 && startRow >= 0 && startCol >= 0 && endRow >= 0 && endCol >= 0) { // check if inside array bounds
            if (board[startRow][startCol] != null && (board[endRow][endCol] == null || isBlack != board[endRow][endCol].getIsBlack())) { // check if there is a piece at the start location and no piece at the end location
                return (board[startRow][startCol].getIsBlack() == isBlack);
            }
        }
        return false;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1) {
            return true;
        } else {
            return false;
        }
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {  // check if the piece moves vertically
            return false;
        }
        for (int i = 1; i < (Math.abs(startCol - (endCol))); i++) {
            if ((startCol - endCol) > 0) { // if the piece trying to is move left
                if (getPiece(startRow, startCol - i) != null) { // if the space is filled --> false
                    return false;
                }
            } else if ((startCol - endCol) < 0) {  // if the piece trying to is moving right
                if (getPiece(startRow, startCol + i) != null) { // if the space is filled --> false
                    return false;
                }
            }
        }
        return true;  // if none of the previous if statements are true
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol){  // if the piece changes columns
            return false;
        }
        if(startRow == endRow){
            return true;
        } else {
            for (int i = 1; i < (Math.abs(startRow - endRow)); i++) {
                if ((startRow - endRow) > 0) { // if the piece trying to move up
                    if (getPiece(startRow - i, startCol) != null) { // if the space is filled --> false
                        return false;
                    }
                } else if ((startRow - endRow) < 0) { // if the piece trying to is moving down
                    if (getPiece(startRow + i, startCol) != null) { // if the space is filled --> false
                        return false;
                    }
                }
            }
            return true;
        }
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) { // if the piece is in the same space
            return true;
        } else {
            if (Math.abs(startRow - endRow) != Math.abs(startCol - endCol)){ // if the rows and columns don't change the same amount -->
                return false;
            } else if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol)) { // if the rows and columns change the same amount:
                for (int i = 1; i < Math.abs(startRow - endRow); i++) {
                    if (startRow - endRow > 0) { // moving up
                        if (startCol - endCol > 0) { // moving up and left
                            if (board[startRow - i][startCol - i] != null) { // if the spaces between start and end are not null --> false
                                return false;
                            }
                        } else if (startCol - endCol < 0) {  // moving up and right
                            if (board[startRow - i][startCol + i] != null) {  // if the spaces between start and end are not null --> false
                                return false;
                            }
                        }
                    } else if (startRow - endRow < 0) { // moving down
                        if (startCol - endCol > 0) { // moving down and left
                            if (board[startRow + i][startCol - i] != null) { // if the spaces between start and end are not null --> false
                                return false;
                            }
                        } else if (startCol - endCol < 0) {  // moving down and right
                            if (board[startRow + i][startCol + i] != null) {  // if the spaces between start and end are not null --> false
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
