// Gavin Rose - rose1751
// Gage Canavan - canav022

import java.util.Scanner;

public class Piece {
    // Create Instance Variables
    char character;
    int row;
    int col;
    boolean isBlack;

    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659': // white pawn
            case '\u265f': // black pawn
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656': // white rook
            case '\u265c': // black rook
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u2658': // white knight
            case '\u265e': // black knight
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u2657': // white bishop
            case '\u265d': // black bishop
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u2655': // white queen
            case '\u265b': // black queen
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u2654': // white king
            case '\u265a': // black king
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }



    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() {
        if (isBlack == true){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Handle promotion of a pawn.
     * @param row Current row of the pawn
     * @param isBlack Color of the pawn
     */
    public void promotePawn(int row, boolean isBlack) {
        if ((row == 0 && !isBlack) || (row == 7 && isBlack)) { // if a piece is in the final row on the opposite side of the board
            System.out.println("You can promote a pawn!");
            System.out.println("Promote to: Queen (Q), Rook (R), Knight (K), or Bishop (B)");
            Scanner myScanner = new Scanner(System.in);
            String choice = myScanner.nextLine();
            System.out.println(choice);
            Piece promotedPiece = null;
            if (choice.equalsIgnoreCase("Q")){ //queen
                if (isBlack) {
                    this.character = '\u265b';
                } else {
                    this.character = '\u2655';
                }
            } else if (choice.equalsIgnoreCase("R")) { //rook
                if (isBlack) {
                    this.character = '\u265c';
                } else {
                    this.character = '\u2656';
                }
            } else if (choice.equalsIgnoreCase("B")) { //bishop
                if (isBlack) {
                    this.character = '\u265d';
                } else {
                    this.character = '\u2657';
                }
            } else if (choice.equalsIgnoreCase("K")) { //knight
                if (isBlack) {
                    this.character = '\u265e';
                } else {
                    this.character = '\u2658';
                }
            } else {
                System.out.println("Invalid choice. Defaulting to Queen.");
                if (isBlack) {
                    this.character = '\u265b';
                } else {
                    this.character = '\u2655';
                }
            }
        }
    }




    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return "" + this.character;
    }


}
