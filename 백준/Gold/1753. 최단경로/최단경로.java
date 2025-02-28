// 인접 행렬 x
// 인접 리스트 o
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static final int INF = Integer.MAX_VALUE;

	private static class Node implements Comparable<Node> {
		int vertex;
		int dist;

		public Node(int vertex, int dist) {
			this.vertex = vertex;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점 개수
		int E = Integer.parseInt(st.nextToken()); // 간선 개수
		int K = Integer.parseInt(br.readLine()); // 시작 정점 번호

		ArrayList<Node>[] graph = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
		}

		int[] distance = new int[V + 1];
		boolean[] visited = new boolean[V + 1];
		Arrays.fill(distance, INF);
		distance[K] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(K, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int curVertex = current.vertex;
			if (visited[curVertex])
				continue;
			visited[curVertex] = true;

			for (Node next : graph[curVertex]) {
				if (!visited[next.vertex] && distance[next.vertex] > distance[curVertex] + next.dist) {
					distance[next.vertex] = distance[curVertex] + next.dist;
					pq.offer(new Node(next.vertex, distance[next.vertex]));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			sb.append(distance[i] == INF ? "INF" : distance[i]).append("\n");
		}
		System.out.println(sb);
	}
}