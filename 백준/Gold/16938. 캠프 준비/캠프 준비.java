import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int i = 1; i < (1 << N); i++) {
            int len = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int sum = 0;

            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    len++;
                    max = Math.max(arr[j], max);
                    min = Math.min(arr[j], min);
                    sum += arr[j];
                }
            }

           
            if (sum < L || sum > R) { 
                continue;
            }
            if (max - min < X) { 
                continue;
            }

            ans++;

        }

        System.out.println(ans);

    }

}