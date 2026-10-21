import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDangerous {
    static void main() {
        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = {{4, 2}, {1, 3}, {2, 4}};
        System.out.println(solution(points, routes));
    }
    static int solution(int[][] points, int[][] routes) {
            // 1. 로봇별 시간별 경로 만들기
            List<List<int[]>> paths = new ArrayList<>();
            int maxLen = 0;

            for (int[] route : routes) {
                List<int[]> path = new ArrayList<>();

                int[] start = points[route[0] - 1];
                int r = start[0];
                int c = start[1];
                path.add(new int[]{r, c});              // 0초: 시작점은 한 번만 추가

                for (int i = 1; i < route.length; i++) {
                    int[] target = points[route[i] - 1];
                    int tr = target[0];
                    int tc = target[1];

                    while (r != tr) {                   // 행을 먼저 맞춤
                        r += (tr > r) ? 1 : -1;
                        path.add(new int[]{r, c});
                    }
                    while (c != tc) {                   // 그다음 열을 맞춤
                        c += (tc > c) ? 1 : -1;
                        path.add(new int[]{r, c});
                    }
                }
                paths.add(path);
                maxLen = Math.max(maxLen, path.size());
            }

            // 2. 시간별로 같은 좌표에 2대 이상 있는지 확인
            int answer = 0;
            for (int t = 0; t < maxLen; t++) {
                Map<Integer, Integer> count = new HashMap<>();

                for (List<int[]> path : paths) {
                    if (t < path.size()) {              // 아직 도착하지 않은 로봇만
                        int[] pos = path.get(t);
                        int key = pos[0] * 101 + pos[1];   // 좌표를 하나의 정수로 합침
                        count.merge(key, 1, Integer::sum);
                    }
                }

                for (int v : count.values()) {
                    if (v >= 2) answer++;               // 겹친 좌표 하나당 1회
                }
            }
            return answer;
        }
    }
