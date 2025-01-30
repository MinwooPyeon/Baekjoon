import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main { 

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(bf.readLine());
		int arr[] = new int[n];

		Deque<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			queue.add(i);
		}

		int idx = 1;
		while (!queue.isEmpty()) {
			for (int i = 0; i < idx; i++) {
				queue.addLast(queue.pollFirst());
			}
			int num = queue.pollFirst();
			arr[num] = idx++;
		}

		for(int i=0; i<n; i++) {
			bw.write(arr[i]+" ");
		}
		bw.flush();
	}
}