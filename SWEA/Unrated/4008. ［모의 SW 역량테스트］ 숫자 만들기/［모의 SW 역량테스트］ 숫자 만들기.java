import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    static int N, min, max;
    static int[] num, op;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("4008.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(in.readLine());
            op = new int[4];
            num = new int[N];
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            String[] opStr = in.readLine().split(" ");
            for (int i = 0; i < 4; i++) {
                op[i] = Integer.parseInt(opStr[i]);
            }
            
            String[] numStr = in.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(numStr[i]);
            }
            
            dfs(1, num[0]);
            System.out.println("#" + (t + 1) + " " + (max - min));
        }
    }

    static void dfs(int pos, int result) {
        if (pos == N) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }
        if (op[0] > 0) {
            op[0]--;
            dfs(pos + 1, result + num[pos]);
            op[0]++;
        }
        if (op[1] > 0) {
            op[1]--;
            dfs(pos + 1, result - num[pos]);
            op[1]++;
        }
        if (op[2] > 0) {
            op[2]--;
            dfs(pos + 1, result * num[pos]);
            op[2]++;
        }
        if (op[3] > 0) {
            op[3]--;
            dfs(pos + 1, result / num[pos]);
            op[3]++;
        }
    }
}