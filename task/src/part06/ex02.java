public class ex02 {
    static void main() {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '1'},
                {'0', '0', '0', '1', '0'}
        };
        System.out.println(solutionByDfs(grid));
    }
    // dfs로도 한 번 풀어봤습니다.
    public static int solutionByDfs(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    static void dfs(char[][] grid, int row, int col) {
        int nrow;
        int ncol;
        for(int[] n : new int[][] {{1,0},{-1,0},{0,1},{0,-1}}) {
            nrow = row + n[0];
            ncol = col + n[1];
            if(nrow >= 0 && nrow < grid.length && ncol >= 0 && ncol < grid[0].length) {
                if(grid[nrow][ncol] == '1') {
                    grid[nrow][ncol] = '0';
                    dfs(grid, nrow, ncol);
                }
            }
        }
    }
    public static int solutionByBfs(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';
                    bfs(grid,i,j);
                }
            }
        }
        return count;
    }
    // 조건에 맞으면 큐에 넣어 다시 해당 큐를 검사
    static void bfs(char[][] grid, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row,col});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] n : new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                int nrow = cur[0] + n[0];
                int ncol = cur[1] + n[1];
                if (nrow >= 0 && ncol >= 0
                        && nrow < grid.length && ncol < grid[0].length
                        && grid[nrow][ncol] == '1') {
                    grid[nrow][ncol] = '0'; // 큐에 넣을 때 바로 방문 처리
                    queue.offer(new int[] {nrow, ncol});
                }
            }
        }
    }
}
    }
