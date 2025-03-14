import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int N;
    static int[][] map;  
    static int[][] dist; 
    static int[] dx = {-1, 0, 1, 0}; 
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("1249.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());  
            map = new int[N][N];
            dist = new int[N][N];

            // 지도 입력 받기
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            
            // 출발지점 
            dist[0][0] = 0;
            bfs();

            System.out.printf("#%d %d\n", tc, dist[N - 1][N - 1]);
        }
    }

    // BFS
    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});  // 시작점
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 지도 범위 안에 있는지 확인
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    int newDist = dist[x][y] + map[nx][ny];

                    // 더 짧은 거리로 갱신되는 경우에만 큐에 추가
                    if (newDist < dist[nx][ny]) {
                        dist[nx][ny] = newDist;
                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
        }
    }
}
