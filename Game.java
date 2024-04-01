// Gavin Rose - rose1751
// Gage Canavan - canav022

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Board board = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        Scanner sc = new Scanner(System.in);
        boolean isBlack = false;
        while (!board.isGameOver()) {

            if (isBlack == false) {
                System.out.println("\n\nIt is now white's turn.");
            } else if (isBlack == true) {
                System.out.println("\n\nIt is now black's turn.");
            }
            System.out.println("Current Board: \n\n" + board);
            System.out.println("Enter your move (format [startRow] [startCol] [endRow] [endCol]): ");
            int startRow = sc.nextInt();
            int startCol = sc.nextInt();
            int endRow = sc.nextInt();
            int endCol = sc.nextInt();
            // if the start position if empty --> invalid move
            // OR if the start position is NOT empty but is the wrong color --> invalid move
            if (board.getPiece(startRow, startCol) == null || (board.getPiece(startRow, startCol) != null && board.getPiece(startRow,startCol).getIsBlack() != isBlack)){
                System.out.println("\nInvalid move.");
            } else {
                if (board.movePiece(startRow, startCol, endRow, endCol)) { // if the move is legal
                    Piece movedPiece = board.getPiece(endRow, endCol);
                    if (board.getPiece(endRow, endCol) != null) { // only checks spaces with pieces
                        if (movedPiece.character == '\u2659' || movedPiece.character == '\u265f') { // if the piece that was moved was a pawn --> promote pawn
                            movedPiece.promotePawn(endRow, isBlack);
                        }
                    }
                    //switch which color's turn it is if the move is valid
                    if (!isBlack) {
                        isBlack = true;
                    } else {
                        isBlack = false;
                    }

                }  // exits the loop if the game is over
            }
        }
        for (int i = 0; i < 8; i++) { // checks which king is still on the board and prints that that color won
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i,j) != null) { // only checks spaces with a piece
                    if (board.getPiece(i, j).character =='\u2654') {
                        System.out.println("\nWhite wins!");
                    } else if (board.getPiece(i, j).character == '\u265a') {
                        System.out.println("\nBlack wins!");
                    }
                }
            }
        }
    }
}