import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static int n;
	private static int[][] map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	private static class Node implements Comparable<Node> {
		int x;
		int y;
		int cost;

		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int idx = 1;
		String line;

		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.equals("0"))
				break;
			n = Integer.parseInt(line);
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				String row = br.readLine().trim();
				StringTokenizer st = new StringTokenizer(row);
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cost = bfs();
			sb.append("Problem ").append(idx).append(": ").append(cost).append("\n");
			idx++;
		}
		System.out.print(sb.toString());
	}

	private static int bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[][] move = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(move[i], Integer.MAX_VALUE);
		}

		pq.offer(new Node(0, 0, map[0][0]));
		move[0][0] = map[0][0];

		while (!pq.isEmpty()) {
			Node currentNode = pq.poll();
			int px = currentNode.x, py = currentNode.y;
			int cost = currentNode.cost;

			if (px == n - 1 && py == n - 1) {
				return cost;
			}

			for (int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n)
					continue;

				if (cost + map[ny][nx] < move[ny][nx]) {
					move[ny][nx] = cost + map[ny][nx];
					pq.add(new Node(nx, ny, move[ny][nx]));
				}
			}
		}
		return -1; // 도착점에 도달하지 못한 경우
	}
}