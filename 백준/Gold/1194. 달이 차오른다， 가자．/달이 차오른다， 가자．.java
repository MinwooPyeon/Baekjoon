import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static char[][] board;
	static boolean[][][] visited;
	static final int DIRECTION = 4;
	static int N, M;
	static int result = -1;
	static Player startPlayer;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	static class Player {
		int y;
		int x;
		int depth;
		int keyBitMask;

		public Player(int y, int x, int depth, int keyBitMask) {
			super();
			this.y = y;
			this.x = x;
			this.depth = depth;
			this.keyBitMask = keyBitMask;
		}

		@Override
		public String toString() {
			return "Player [y=" + y + ", x=" + x + ", depth=" + depth + ", keyBitMask=" + keyBitMask + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		visited = new boolean[N][M][1 << 6];

		// String은 불변객체여서 메모리를 많이 먹기 때문에 StringBuilder 활용
		StringBuilder sb = new StringBuilder();
		char tempChar;
		for (int y = 0; y < N; y++) {
			sb.append(br.readLine());
			for (int x = 0; x < M; x++) {
				tempChar = sb.charAt(x);
				board[y][x] = tempChar;

				// 출발지
				if (tempChar == '0') {
					startPlayer = new Player(y, x, 0, 0);
					continue;
				}
			}
			sb.setLength(0);
		}

		bfs();
		System.out.println(result);

	}

	private static void bfs() {
		Queue<Player> bfsQ = new LinkedList<Player>();
		bfsQ.offer(startPlayer);
		visited[startPlayer.y][startPlayer.x][startPlayer.keyBitMask] = true;
		Player tempPlayer;
		int tempY, tempX, depth, keyBitMask;

		while (!bfsQ.isEmpty()) {
			tempPlayer = bfsQ.poll();

			for (int k = 0; k < DIRECTION; k++) {
				tempY = tempPlayer.y + dy[k];
				tempX = tempPlayer.x + dx[k];
				keyBitMask = tempPlayer.keyBitMask;
				depth = tempPlayer.depth;

				if (!isValid(tempY, tempX, keyBitMask)) {
					continue;
				}

				if (isDestination(tempY, tempX)) {
					result = depth + 1;
					return;
				}

				// 만약 문이라면
				if (isDoor(tempY, tempX)) {
					// 키가 없다면 지나가지 못함
					// 원리 비트 And 연산 (기존비트 & 문)
					// 즉 비트의 자리수가 아무것도 같지 않으면 열쇠는 없다는 뜻이고 continue
					if ((keyBitMask & (1 << (board[tempY][tempX] - 'A'))) == 0) {
						continue;
					}

				}

				if (isKey(tempY, tempX)) {
					// 원리 비트 or연산 ( 기존비트 | 새로운 키 획득)
					// 만약 기존비트 000000
					// a키 획득 100000
					// 기존비트 | 새로운 키 획득 = 100000
					keyBitMask = keyBitMask | (1 << (board[tempY][tempX] - 'a'));
				}

				visited[tempY][tempX][keyBitMask] = true;
				bfsQ.add(new Player(tempY, tempX, depth + 1, keyBitMask));
			}
		}

	}

	private static boolean isValid(int tempY, int tempX, int keyBitMask) {
		// 좌표가 유효해야 함 && 지나갈 위치가 #이 아니여야 함 && 방문되지 않았어야 함 && 문으로 막혀있다면 키가 존재해야함
		return tempY >= 0 && tempY < N && tempX >= 0 && tempX < M && board[tempY][tempX] != '#'
				&& !visited[tempY][tempX][keyBitMask];
	}

	private static boolean isDestination(int tempY, int tempX) {
		return board[tempY][tempX] == '1';
	}

	private static boolean isDoor(int tempY, int tempX) {
		return board[tempY][tempX] >= 'A' && board[tempY][tempX] <= 'F';
	}

	private static boolean isKey(int tempY, int tempX) {
		return board[tempY][tempX] >= 'a' && board[tempY][tempX] <= 'f';
	}
}
