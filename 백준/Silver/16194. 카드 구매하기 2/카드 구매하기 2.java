import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static StringTokenizer st;
  static Integer[] memo;
  static int[] costArr;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    memo = new Integer[N + 1];
    costArr = new int[N + 1];
    st = new StringTokenizer(br.readLine());

    for (int i = 1; i < N + 1; i++) {
      costArr[i] = Integer.parseInt(st.nextToken());
    }

    memo[0] = 0;
    memo[1] = costArr[1];

    System.out.println(dp(N));
  }

  public static int dp(int N) {
    if (memo[N] != null) {
      return memo[N];
    }
    memo[N] = costArr[N];
    for (int i = 1; i < N + 1; i++) {
      memo[N] = Math.min(memo[N], dp(N - i) + dp(i));
    }

    return memo[N];
  }
}
