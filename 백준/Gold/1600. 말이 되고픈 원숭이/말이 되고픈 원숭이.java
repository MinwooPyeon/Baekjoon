import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // 변수 선언: k는 말을 몇 번 사용할 수 있는지, m은 가로 크기, n은 세로 크기
    static int k, m, n;
    static int[][] map; // 맵의 정보를 담을 배열
    static boolean[][][] visited; // 방문 여부를 기록할 3D 배열 (x, y, horseCnt)
    
    // 일반적인 4방향 이동
    static int[] dx = {-1, 0, 1, 0}; 
    static int[] dy = {0, 1, 0, -1};
    
    // 말처럼 이동할 수 있는 8가지 방향
    static int[] horseX = {-2, -2, -1, 1, 2, 2, -1, 1};
    static int[] horseY = {-1, 1, 2, 2, -1, 1, -2, -2};

    static class Point {
        int x;
        int y;
        int horseCnt;
        int moveCnt;

        Point(int x, int y, int horseCnt, int moveCnt) {
            this.x = x;
            this.y = y;
            this.horseCnt = horseCnt;
            this.moveCnt = moveCnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m]; 
        visited = new boolean[n][m][31];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        
        // 시작 지점 (0, 0)부터 시작
        queue.offer(new Point(0, 0, 0, 0));
        visited[0][0][0] = true; // 시작지점 방문 처리

        while (!queue.isEmpty()) {
            Point point = queue.poll(); 

            if (point.x == n - 1 && point.y == m - 1) {
                System.out.println(point.moveCnt); 
                return; 
            }

            // 4방향
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i]; 
                int ny = point.y + dy[i]; 
                
                // 유효한 이동인지 확인
                if (Move(nx, ny, point.horseCnt)) {
                    visited[nx][ny][point.horseCnt] = true; // 방문 처리
                    queue.offer(new Point(nx, ny, point.horseCnt, point.moveCnt + 1)); 
                }
            }

            // 말을 사용한 적이 있고, 아직 말을 더 사용할 수 있으면
            if (point.horseCnt < k) {
                for (int i = 0; i < 8; i++) {
                    int hx = point.x + horseX[i];
                    int hy = point.y + horseY[i];

                    // 유효한 이동인지 확인
                    if (Move(hx, hy, point.horseCnt + 1)) {
                        visited[hx][hy][point.horseCnt + 1] = true; // 방문 처리
                        queue.offer(new Point(hx, hy, point.horseCnt + 1, point.moveCnt + 1)); // 큐에 새로운 점 추가
                    }
                }
            }
        }
        System.out.println(-1);
    }

    // 이동이 가능한지 확인
    private static boolean Move(int x, int y, int horseCnt) {
        return x >= 0 && y >= 0 && x < n && y < m 
            && map[x][y] != 1 // 벽이 아닌지
            && !visited[x][y][horseCnt]; // 이미 방문했는지
    }
}
