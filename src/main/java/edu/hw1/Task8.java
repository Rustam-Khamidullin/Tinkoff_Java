package edu.hw1;

public class Task8 {
    private static final int BOARD_SIZE = 8;
    private static final int[][] MOVES = new int[][]
        {{1, 2}, {1, -2}, {2, 1}, {2, -1}};

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        if ((board == null) || (board.length != BOARD_SIZE)) {
            return false;
        }
        for (int[] row : board) {
            if ((row == null) || (row.length != BOARD_SIZE)) {
                return false;
            }
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == 1) {
                    for (int[] move : MOVES) {
                        if ((row + move[0] < BOARD_SIZE) && (col + move[1] >= 0) && (col + move[1] < BOARD_SIZE)
                            && (board[row + move[0]][col + move[1]] == 1)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
