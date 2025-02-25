import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
  private static int N, M;
  private static boolean found = false;
  private static List<Integer>[] graph;

  private static class Node {
    int vertex;
    List<Node> children = new ArrayList<>();

    public Node(int vertex) {
      this.vertex = vertex;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    String firstLine = in.readLine();
    int spaceIdx = firstLine.indexOf(" ");
    N = Integer.parseInt(firstLine.substring(0, spaceIdx));
    M = Integer.parseInt(firstLine.substring(spaceIdx + 1).trim());

    graph = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      String line = in.readLine();
      spaceIdx = line.indexOf(" ");
      int a = Integer.parseInt(line.substring(0, spaceIdx));
      int b = Integer.parseInt(line.substring(spaceIdx + 1).trim());
      graph[a].add(b);
      graph[b].add(a);
    }

    for (int i = 0; i < N; i++) {
      boolean[] visited = new boolean[N];
      visited[i] = true;
      Node root = new Node(i);
      dfs(root, visited, 1);
      if (found)
        break;
      visited[i] = false;
    }

    System.out.println(found ? 1 : 0);
  }

  private static void dfs(Node current, boolean[] visited, int depth) {
    if (depth == 5) {
      found = true;
      return;
    }
    int curVertex = current.vertex;
    for (int neighbor : graph[curVertex]) {
      if (!visited[neighbor]) {
        visited[neighbor] = true;
        Node child = new Node(neighbor);
        current.children.add(child);
        dfs(child, visited, depth + 1);
        if (found)
          return;
        visited[neighbor] = false;
      }
    }
  }
}
