import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1; // 1을 빼는 연산
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }
        
        sb.append(dp[n]).append("\n");
        
        // 역추적 (경로 출력)
        while (n > 0) {
            sb.append(n).append(" ");
            if (n == 1) break;
            if (n % 3 == 0 && dp[n / 3] == dp[n] - 1) {
                n /= 3;
            } else if (n % 2 == 0 && dp[n / 2] == dp[n] - 1) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        
        System.out.println(sb.toString().trim());
    }
}