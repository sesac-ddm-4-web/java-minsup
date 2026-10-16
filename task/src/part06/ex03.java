public class ex03 {
    static void main() {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println(solution(maps));
    }
    static int solution(int[][] maps){
        int answer = 0;
        int[] start = new int[]{0,0};
        bfs(maps,0,0);
        answer = maps[maps.length-1][maps[0].length-1];
        if(answer <= 1) return -1;
        if (maps.length == 1 && maps[0].length == 1) return 1;
        return answer;
    }
    static void bfs(int[][] maps, int r, int c) {
        int row = maps.length;
        int col = maps[0].length;
        // 한 번 지나간 자리를 다시 업데이트하는 걸 방지하기 위함
        boolean[][] visited = new boolean[row][col];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r,c});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur =  queue.poll();
            int rows = cur[0];
            int cols = cur[1];
            for (int[] n : new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                int x = rows + n[0];
                int y = cols + n[1];
                if(x >= 0 && y >= 0 && x < row && y < col) {
                    if(maps[x][y] != 0 && visited[x][y] == false){
                        maps[x][y] = maps[rows][cols] + 1;
                        queue.offer(new int[]{x,y});
                        visited[x][y] = true;
                    }
                }
            }
        }
    }
}
