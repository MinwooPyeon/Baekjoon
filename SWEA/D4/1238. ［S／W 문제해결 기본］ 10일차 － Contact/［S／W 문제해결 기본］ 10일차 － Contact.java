import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
  private static int N;
  private static int[][] graph;
  private static int[] visited;

  public static void main(String[] args) throws Exception {
   // System.setIn(new FileInputStream("1238.txt"));
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();

    for (int tc = 1; tc <= 10; tc++) {
      st = new StringTokenizer(in.readLine());
      N = Integer.parseInt(st.nextToken());
      int V = Integer.parseInt(st.nextToken());

      graph = new int[101][101];
      visited = new int[101];

      st = new StringTokenizer(in.readLine());
      for (int i = 0; i < N / 2; i++) {
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        graph[from][to] = 1;
      }
      sb.append('#').append(tc).append(" ").append(bfs(V)).append("\n");
    }
    System.out.println(sb);
  }

  private static int bfs(int start) {
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(start);
    visited[start] = 1;

    while (!queue.isEmpty()) {
      int cur = queue.poll();

      for (int i = 0; i <= 100; i++) {
        if (graph[cur][i] == 1 && visited[i] == 0) {
          queue.offer(i);
          visited[i] = visited[cur] + 1;
        }
      }
    }

    int maxDepth = 0;
    for (int i = 0; i <= 100; i++) {
      if (visited[i] > maxDepth) {
        maxDepth = visited[i];
      }
    }

    for (int i = 100; i >= 0; i--) {
      if (visited[i] == maxDepth) {
        return i;
      }
    }
    return -1;
  }
}