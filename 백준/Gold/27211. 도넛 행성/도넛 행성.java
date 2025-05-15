import java.io.*;
import java.util.*;

public class Main {
    static class Pos{
        int r, c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static int N, M;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N][M];
        int result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == 0 && !visited[i][j]){
                    bfs(i, j);
                    result++;
                }
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();		
        bw.close();
        br.close();
    }
    static void bfs(int r, int c){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(r, c));
        visited[r][c] = true;
        while(!q.isEmpty()){
            Pos cur = q.poll();
            for(int i=0;i<4;i++){
                int nr = cur.r + dr[i] < 0 ? N-1 : (cur.r + dr[i]) % N;
                int nc = cur.c + dc[i] < 0 ? M-1 : (cur.c + dc[i]) % M;
                if(!visited[nr][nc] && map[nr][nc] == 0){
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }
        }
    }
}