public class Solution {
    public int countBattleships(char[][] board) {
        if (board.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < board.length; ++ i) {
            for (int j = 0; j < board[0].length; ++ j) {
                if (board[i][j] == 'X' && isPreviousNotShip(board, i, j) && isUpperNotShip(board, i, j)) {
                    count ++;
                }
            }
        }
        return count;
    }

    private boolean isPreviousNotShip(char[][] board, int x, int y) {
        return y == 0 || (board[x][y - 1] != 'X');
    }

    private boolean isUpperNotShip(char[][] board, int x, int y) {
        return x == 0 || (board[x - 1][y] != 'X');
    }
}