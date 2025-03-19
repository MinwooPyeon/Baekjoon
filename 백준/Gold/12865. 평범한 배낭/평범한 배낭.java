import java.util.Scanner;

public class Main {
  static Integer[][] dp;
  static int[] weights;
  static int[] profits;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int W = sc.nextInt();

    weights = new int[N + 1];
    profits = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      weights[i] = sc.nextInt();
      profits[i] = sc.nextInt();
    }

    // DP 테이블 초기화
    dp = new Integer[N + 1][W + 1];

    System.out.println(knapsack(N, W));
  }

  static int knapsack(int N, int W) {

    // 베이스 값 채우기
    if (N == 0 || W == 0) {
      return 0;
    }

    // 이미 계산한 값이 있다면 반환
    if (dp[N][W] != null) {
      return dp[N][W];
    }

    // 현재 아이템의 무게와 가치
    int itemWeight = weights[N];
    int itemProfit = profits[N];

    // 아이템을 담을 수 없는 경우
    if (itemWeight > W) {
      dp[N][W] = knapsack(N - 1, W);
    }
    // 아이템을 담을 수 있는 경우
    else {
      dp[N][W] = Math.max(knapsack(N - 1, W), knapsack(N - 1, W - itemWeight) + itemProfit);
    }

    return dp[N][W];
  }
}
