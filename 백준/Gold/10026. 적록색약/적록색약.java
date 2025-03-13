import java.io.BufferedReader;
import java.io.InputStreamReader;

// 적록색약 아닌 사람 구역 (빨강, 파랑, 초록)
// 적록색약인 사람 구역 (빨강 + 초록, 파랑)

public class Main {

	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		int count = 0;
		int RGcount = 0;

		for (int i = 0; i < n; i++) {
			String[] color = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				if (color[j].equals("R")) {
					map[i][j] = 1;
				} else if (color[j].equals("G")) {
					map[i][j] = 2;
				} else if (color[j].equals("B")) {
					map[i][j] = 3;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 1; k < 4; k++) {
					if (map[i][j] == k && !visited[i][j]) {
						dfs(i, j, k);
						count++;
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 2) {
					map[i][j] = 1;
				}
			}
		}

		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 1; k < 4; k++) {
					if (map[i][j] == k && !visited[i][j]) {
						dfs(i, j, k);
						RGcount++;
					}
				}
			}
		}
		System.out.println(count + " " + RGcount);

	}

	static void dfs(int x, int y, int color) {

		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= n)
				continue;
			if (visited[nx][ny])
				continue;

			if (map[nx][ny] == color) {
				dfs(nx, ny, color);
			}
		}

	}
}