import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int T, N;
  static Point[] points;
  static int[][] distance;
  static final int INF = 99999;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());
      points = new Point[N + 2];

      for (int i = 0; i < N + 2; i++) {
        st = new StringTokenizer(br.readLine());
        points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      }

      distance = new int[N + 2][N + 2];
      for (int i = 0; i < N + 1; i++) {
        for (int j = i + 1; j < N + 2; j++) {
          distance[i][j] = distance[j][i] = INF;
          int dis = Math.abs(points[i].y - points[j].y) + Math.abs(points[i].x - points[j].x);
          if (dis <= 50 * 20)
            distance[i][j] = distance[j][i] = 1; // i에서 j까지 안전하게 갈 수 있다면 1로 업데이트
        }
      }

      for (int k = 0; k < N + 2; k++) { // 경유지
        for (int i = 0; i < N + 2; i++) { // 출발지
          for (int j = 0; j < N + 2; j++) { // 도착지
            distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
          }
        }
      }

      if (distance[0][N + 1] > 0 && distance[0][N + 1] < INF)
        sb.append("happy\n");
      else
        sb.append("sad\n");
    }

    System.out.print(sb.toString());
    br.close();
  }
}
