import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static boolean[][] map;
    static boolean[][] visited;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Spot {
        int x;
        int y;
        int count;

        public Spot(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        map = new boolean[n][m];
        visited = new boolean[n][m];

        // 미로 정보
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                if (row.charAt(j) == '1') {
                    map[i][j] = true;
                }
            }
        }

        sb.append(bfs(0, 0)).append("\n");
        System.out.print(sb);
    }

    private static int bfs(int startX, int startY) {
        // 시작 지점 방문 처리
        visited[startX][startY] = true;
        
        Queue<Spot> queue = new LinkedList<>();
        queue.offer(new Spot(startX, startY, 1));

        while (!queue.isEmpty()) {
            Spot current = queue.poll();

            // 목표 지점에 도달하면 이동 거리 반환
            if (current.x == n - 1 && current.y == m - 1) {
                return current.count;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 범위 안에 있고, 아직 방문하지 않았으며 이동 가능한 칸인 경우
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (!visited[nx][ny] && map[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new Spot(nx, ny, current.count + 1));
                    }
                }
            }
        }
        return 0;
    }
}