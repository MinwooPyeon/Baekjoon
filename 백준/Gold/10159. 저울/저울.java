import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] graph;
	static boolean[] isVisited;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new ArrayList[N + 1];
		isVisited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a * -1);
		}

		for (int i = 1; i <= N; i++) {
			count = N;
			Arrays.fill(isVisited, false);
			dfs(i);
			isVisited[i] = false;
			count++;
			reverseDfs(i);
			System.out.println(count);
		}
	}

	static void dfs(int n) {
		if (isVisited[n])
			return;

		isVisited[n] = true;
		count--;

		for (int node : graph[n]) {
			if (node > 0 && !isVisited[node]) {
				dfs(node);
			}
		}
	}
	static void reverseDfs(int n) {
		if (isVisited[n])
			return;

		isVisited[n] = true;
		count--;

		for (int node : graph[n]) {
			if (node < 0 && !isVisited[node * -1]) {
				reverseDfs(node * -1);
			}
		}
	}
}
