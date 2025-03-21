import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
  static int[][] houses;
  static boolean[][] visited;
  static int[] dx = {0, 0, -1, 1};
  static int[] dy = {-1, 1, 0, 0};
  static List<Integer> result;
  static int cnt, N;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    result = new LinkedList<>();
    N = Integer.parseInt(br.readLine());
    houses = new int[N][N];
    visited = new boolean[N][N];
    cnt = 1;

    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < N; j++) {
        houses[i][j] = str.charAt(j) - '0';
      }
    }

    for (int x = 0; x < N; x++) {
      for (int y = 0; y < N; y++) {
        if (houses[x][y] == 1 && !visited[x][y]) {
          dfs(x, y);
          result.add(cnt);
          cnt = 1;
        }
      }
    }

    Collections.sort(result);

    sb.append(result.size()).append("\n");
    for (int r : result)
      sb.append(r).append("\n");

    System.out.print(sb.toString());
  }

  public static void dfs(int x, int y) {
    visited[x][y] = true;

    for (int i = 0; i < 4; i++) {
      int nx = dx[i] + x;
      int ny = dy[i] + y;

      if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && houses[nx][ny] == 1) {
        cnt++;
        dfs(nx, ny);
      }
    }
  }
}
