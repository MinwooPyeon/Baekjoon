import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.IntBinaryOperator;

public class Main {

    static int N,M;


    public static void main(String[] args) throws IOException {
        BufferedReader br  =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int graph[][] = new int[N+1][N+1];
        int dist[][] = new int[N+1][N+1];


        for (int i = 0; i < N + 1 ; i++) {
            Arrays.fill(graph[i],Integer.MAX_VALUE/2);
        }

        for (int i = 1; i < N+1 ; i++) {
            for (int j = 1; j < N+1 ; j++) {
                dist[i][j] = j;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start][end] = graph[end][start] = Math.min(graph[start][end],cost);
        }


        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j <N+1 ; j++) {
                for (int k = 1; k <N+1 ; k++) {
                    if(graph[j][k] > graph[j][i]+graph[i][k]) {
                        graph[j][k] = Math.min(graph[j][i] + graph[i][k], graph[j][k]);
                        dist[j][k] = dist[j][i];
                    }
                }
            }
        }

        for (int i = 1; i < N+1 ; i++) {
            for (int j = 1; j < N+1; j++) {
                if(i == j){
                    System.out.print("- ");
                }else{
                    System.out.print(dist[i][j]+" ");
                }
            }
            System.out.println();
        }

    }
}