import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[][] dp = new int[30][30];

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int tc = Integer.parseInt(br.readLine());
    for (int i = 0; i < tc; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int n = Integer.parseInt(st.nextToken()); // 서쪽
      int m = Integer.parseInt(st.nextToken()); // 동쪽

      System.out.println(combination(m, n));
    }
  }

  static int combination(int n, int r) {
    if (r == 0 || n == r) {
      return 1;
    }

    if (dp[n][r] > 0) {
      return dp[n][r];
    }

    return dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
  }
}
