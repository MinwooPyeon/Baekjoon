import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 왼쪽 부터 ㄱㄱ
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int[] dp = new int[n];
        int length = 0;

        // 증가 수열
        for (int i = 0; i < n; i++) {
            int pos = Arrays.binarySearch(dp, 0, length, arr[i][1]);
            if (pos < 0) {
                pos = -pos - 1;
            }
            dp[pos] = arr[i][1];
            if (pos == length) {
                length++;
            }
        }

        System.out.println(n - length);
    }
}