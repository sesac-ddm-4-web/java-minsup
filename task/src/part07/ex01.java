import java.util.Arrays;

public class ex01 {
    static void main() {
        int[][] input = {{1,2},{1,3},{2,3}};
        int[] answer = findRedundantConnection(input);
        System.out.println(Arrays.toString(answer));
    }
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int a = find(parent, edge[0]);
            int b = find(parent, edge[1]);
            if (a == b) {
                return edge;   // 이미 연결된 두 노드 → 사이클을 만드는 간선
            }
            parent[b] = a;
        }
        return new int[0];
    }

    static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }
}
