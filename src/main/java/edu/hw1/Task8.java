package edu.hw1;

public class Task8 {
    private Task8() {
    }

    @SuppressWarnings({"ParametrAssignment", "MagicNumber"})
    public static boolean knightBoardCapture(int[][] board) {
        if (board.length != 8) {
            return false;
        }
        for (int[] row : board) {
            if (row.length != 8) {
                return false;
            }
        }

        int[][] moves = new int[][]
            {{1, 2}, {1, -2}, {2, 1}, {2, -1}};

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == 1) {
                    for (int[] move : moves) {
                        if ((row + move[0] < 8) && (col + move[1] >= 0) && (col + move[1] < 8)
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
