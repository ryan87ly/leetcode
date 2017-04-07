public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        int i = 0, j = columnCount - 1;
        while(i < rowCount && j >= 0) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] < target) i ++ ;
            else j --;
        }
        return false;
    }
}