import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.valueOf(input[0]);
		int K = Integer.valueOf(input[1]);
		
		long [][]dp=new long [N+1][K+1];
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=K;j++) {
				dp[i][j]=1; 
				for(int k=1;k<=N;k++) {
					dp[i][j]+=dp[k][j-1]%1000000000;
				}
			}
		}
		
		System.out.println(dp[N][K]%1000000000);
	}
}