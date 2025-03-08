import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        // N 최대 1000
        int[] dp = new int[1001];
        int diff;

        for(int i=1; i<1001; ++i){
            dp[i] = 1;
            for(int j=0; j<i; ++j){ // 짝수/홀수 두 경우이므로 0, 1 모두 필요
                diff = i-j;
                if((diff & 1) != 1) { // 홀수 아닐 떄만
                    dp[i] += dp[diff >> 1];
                }
            }
        }

        int T, N;
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<T; ++t){
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }
        System.out.print(sb);

    }
}