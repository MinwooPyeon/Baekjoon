import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  private static int n, m;
  private static int maxSafeArea = Integer.MIN_VALUE;
  private static int[][] rooms;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    rooms = new int[n][m];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        rooms[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Wall(0);

    System.out.println(maxSafeArea);
  }

  private static void Wall(int wall) {
    if (wall == 3) {
      SafeArea();
      return;
    }

    // 벽 세우기
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (rooms[i][j] == 0) {
          rooms[i][j] = 1;
          Wall(wall + 1);
          rooms[i][j] = 0;
        }
      }
    }
  }

  private static void SafeArea() {
    int[][] roomsCopy = new int[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        roomsCopy[i][j] = rooms[i][j];
      }
    }

    // 바이러스
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (roomsCopy[i][j] == 2) {
          virus(roomsCopy, i, j); // 바이러스
        }
      }
    }

    // 안전 영역
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (roomsCopy[i][j] == 0) {
          cnt++;
        }
      }
    }

    maxSafeArea = Math.max(maxSafeArea, cnt); // 최대 안전 영역
  }

  private static void virus(int[][] roomsCopy, int r, int c) {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    Queue<int[]> positions = new LinkedList<>();

    positions.add(new int[] {r, c});

    while (!positions.isEmpty()) {
      int[] cur = positions.poll();

      for (int i = 0; i < 4; i++) {
        int nr = cur[0] + dr[i];
        int nc = cur[1] + dc[i];

        if (check(nr, nc) && roomsCopy[nr][nc] == 0) {
          roomsCopy[nr][nc] = 2;
          positions.add(new int[] {nr, nc});
        }
      }
    }
  }

  private static boolean check(int r, int c) {
    return r >= 0 && r < n && c >= 0 && c < m;
  }
}
