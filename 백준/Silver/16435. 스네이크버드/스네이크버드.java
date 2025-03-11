import java.io.*;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] input = br.readLine().split(" ");
		int count = Integer.parseInt(input[0]);
		int size = Integer.parseInt(input[1]);
		int[] fruits = new int[count];
		input = br.readLine().split(" ");
		
		for (int i = 0; i < count; i++) {
			fruits[i] = Integer.parseInt(input[i]);
		}

		Arrays.sort(fruits);

		for (int fruit : fruits) {
			if (fruit <= size) {
				size++;
			} else {
				break;
			}
		}

		sb.append(size).append("\n");
		System.out.print(sb);
		br.close();
	}
}
