import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int[] trees;
	private static int[] remains;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			trees = new int[N];
			remains = new int[N];
			
			int maxHeight = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(trees[i], maxHeight);
			}
			
			int oddCount = 0; 
			int evenCount = 0; 
			for(int i = 0; i < N; i++) {
				remains[i] = maxHeight - trees[i];
				
				oddCount += remains[i] % 2;
				evenCount += remains[i] / 2;
			}
			
			int result = oddCount + evenCount;
			if(oddCount < evenCount) { 
				int gap = (evenCount - oddCount) * 2;
				int remain = (gap / 3) * 2; 
				
				if(gap % 3 == 2) {
					remain += 2; 
				} else if (gap % 3 == 1) remain += 1; 
				
				result = (oddCount * 2) + remain;
			} else if (oddCount - evenCount > 1){ 
				result += (oddCount - evenCount - 1);
			}
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb);
	}

}