import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	static int result1;
	static int result2;
	static int sum;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());	
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		sum = Integer.MAX_VALUE;
		result1 = 0;
		result2 = 0;
		
		for (int i = 0; i < N - 1; i++) {
			int index = Arrays.binarySearch(arr, arr[i] * -1);
			if (index >= 0) {
				calc(i, index);
			} else {
				index = (index + 1) * -1;
				if (index >= N) {
					calc(i, index - 1);
				} else {
					if (index == i)
						calc(i, index + 1);
					if (index - 1 >= 0)
						calc(i, index - 1);
					calc(i, index);
				}
			}
		}
		System.out.println(result1 + " " + result2);
	}
	
	static void calc(int index1, int index2) {
		if (index1 == index2)
			return;
		int currSum = Math.abs(arr[index1] + arr[index2]);
		if (currSum < sum) {
			sum = currSum;
			result1 = Math.min(arr[index1], arr[index2]);
			result2 = Math.max(arr[index1], arr[index2]);
		}
	}
}
