import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    static InputProcessor inputProcessor = new InputProcessor();

    static int N;
    static int[][] MATRIX;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        input();

        preprocess();

        sb.append(topDown(1, N));

        output();
    }


    private static void input() {
        N = inputProcessor.nextInt();

        MATRIX = new int[N + 1][2];
        for(int i = 1; i <= N; i++) {
            MATRIX[i][0] = inputProcessor.nextInt();
            MATRIX[i][1] = inputProcessor.nextInt();
        }
    }

    private static void preprocess() {
        DP = new int[N + 1][N + 1];
        for(int i = 1; i < N; i++) {
            Arrays.fill(DP[i], 1, N + 1, Integer.MAX_VALUE);

            DP[i][i] = 0;
            DP[i][i + 1] = MATRIX[i][0] * MATRIX[i][1] * MATRIX[i + 1][1];
        }
    }


    private static int topDown(int a, int b) {
        if(a == b) return DP[a][b];
        if(DP[a][b] != Integer.MAX_VALUE) return DP[a][b];

        for(int k = a; k < b; k++) {
            int value = topDown(a, k) + topDown(k + 1, b) + (MATRIX[a][0] * MATRIX[k][1] * MATRIX[b][1]);
            DP[a][b] = Math.min(DP[a][b], value);
        }

        return DP[a][b];
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class InputProcessor {
        BufferedReader br;
        StringTokenizer st;

        public InputProcessor() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return st.nextToken();
        }

        public String nextLine() {
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return input;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
    
}