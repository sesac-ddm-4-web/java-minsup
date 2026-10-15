package part06;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ex01 {
    static void main() {
        int n = 8;
        int[][] edges = {{0,1}, {0,2}, {2,3}, {1,5},{5,4}, {6,7}};
        System.out.println(validPath(n,edges,5,7));
    }
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(source);
        visited[source] = true;

        while(!stack.isEmpty()) {
            int cur = stack.pop();
            if(cur == destination) {
                return true;
            }
            for(int i : graph.get(cur)) {
                if(!visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                }
            }
        }
        return false;
    }
    // 서로 다른 집합 개념도 있는 것 같아 DSU로도 풀어보았습니다.
    public static boolean validPath2(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for(int[] e : edges) {
            int a = find(parent,e[0]);
            int b = find(parent,e[1]);
            if(a != b) {
                parent[b] = a;
            }
        }
        return find(parent, source) == find(parent, destination);
    }
    static int find(int[] parent, int x){
        if(x == parent[x]) return x;
        // 부모가 따로 있음
        return parent[x] = find(parent, parent[x]);
    }

}
