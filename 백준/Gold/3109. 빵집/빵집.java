import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, answer;
	static char[][] board;
	static final int[] rh = { -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		inputData();
		findPath();
		System.out.println(answer);
	}

	static void inputData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];

		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = row.charAt(j);
			}
		}
	}

	// 왼쪽 끝에서 오른쪽 끝 경로 개수
	static void findPath() {
		for (int row = 0; row < R; row++) {
			if (dfs(row, 0)) {
				answer++; // 경로 중복 방지
			}
		}
	}

	// (y, x)에서 시작해 오른쪽 끝 열까지 연결
	static boolean dfs(int y, int x) {
		// 마지막 열이면 완성
		if (x == C - 1)
			return true;

		for (int i = 0; i < 3; i++) {
			int ny = y + rh[i]; // 다음 행
			int nx = x + 1; // 다음 열

			if(pass(ny, nx) && board[ny][nx] == '.') {
			// 지나간 경로 확인 (방문처리)
				board[ny][nx] = '-';

			// 이어지면
				if(dfs(ny, nx)) {
                    return true;
                }
			// 이어지지 않으면
				// board[ny][nx] = '.';
			}
		}
		return false;
	}
	static boolean pass(int y, int x) {
		return (y >= 0 && y < R && x >= 0 && x < C);
	}
}
