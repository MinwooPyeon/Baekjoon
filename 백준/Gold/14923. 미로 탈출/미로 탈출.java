import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main {
    static int N, M, Hx, Hy, Ex, Ey, ans = Integer.MAX_VALUE;
    static int map[][];
    static boolean[][][] visited;
    static PriorityQueue<info> queue = new PriorityQueue<>(new Comparator<info>() {
 
        @Override
        public int compare(info o1, info o2) {
            // TODO Auto-generated method stub
            return o1.dis-o2.dis;
        }
    });
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        st = new StringTokenizer(br.readLine(), " ");
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
 
        map = new int[N][M];
        visited = new boolean[2][N][M];
 
        st = new StringTokenizer(br.readLine(), " ");
        Hx = Integer.parseInt(st.nextToken())-1;
        Hy = Integer.parseInt(st.nextToken())-1;
 
        queue.offer(new info ( Hx, Hy, 0, 0 ));
 
        st = new StringTokenizer(br.readLine(), " ");
 
        Ex = Integer.parseInt(st.nextToken());
        Ey = Integer.parseInt(st.nextToken());
 
        
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        bfs();
        System.out.println(ans==Integer.MAX_VALUE? -1 : ans);
 
    }
 
    public static void bfs() {
        visited[0][Hx][Hy] = true;
        
        while (!queue.isEmpty()) {
            info info = queue.poll();
            
            if (info.x == Ex-1 && info.y == Ey-1) {
                ans = info.dis;
                return;
            }
 
            for (int i = 0; i < 4; i++) {
                int nx = info.x + dx[i];
                int ny = info.y + dy[i];
 
                if (range(nx, ny)) {
 
                    if (map[nx][ny] == 0 && !visited[info.cnt][nx][ny]) { 
                        visited[info.cnt][nx][ny] = true;
                        queue.offer(new info( nx, ny, info.cnt, info.dis+1 ));
                        
                    } else if (map[nx][ny] == 1 && info.cnt==0) {
                        visited[1][nx][ny] = true;
                        queue.offer(new info ( nx, ny, 1, info.dis+1));
                    }
                }
            }
 
        }
    }
 
    public static boolean range(int x, int y) {
        return x >= 0 && y >= 0 && x <N && y <M;
    }
    
    public static class info{
        int x, y, cnt, dis;
 
        public info(int x, int y, int cnt, int dis) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dis = dis;
        }
        
    }
 
}