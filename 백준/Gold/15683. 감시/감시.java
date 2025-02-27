import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Edge {
	int num, direct;

	Edge(int num, int direct) {
		this.num = num;
		this.direct = direct;
	}

	int getNum() {
		return num;
	}

	int getDirect() {
		return direct;
	}

	void setDirect(int direct) {
		this.direct = direct;
	}
}

public class Main {

	private static Edge[][] arr;
	private static Edge[][] tmp;
	private static int N, M;
	private static int min = 100;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new Edge[N][M];
		tmp = new Edge[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = new Edge(Integer.parseInt(st.nextToken()), 1);
			}
		}
		boolean isTrue = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Edge myEdge = arr[i][j];
				int num = myEdge.getNum();
				int dir = myEdge.getDirect();
				if (num != 0 && num != 6 && num != -1) {
					// CCTV인 경우
					int nextI = 0;
					int nextJ = 0;
					if (j == M - 1) {
						nextI = i + 1;
						nextJ = 0;
					} else {
						nextI = i;
						nextJ = j + 1;
					}
					arr[i][j].setDirect(1);
					DFS(nextI, nextJ);
					arr[i][j].setDirect(2);
					DFS(nextI, nextJ);
					arr[i][j].setDirect(3);
					DFS(nextI, nextJ);
					arr[i][j].setDirect(4);
					DFS(nextI, nextJ);
					isTrue = true;
				}
			}
			if (isTrue) {
				break;
			}
		}
		int counter = 0;
		if (min == 100) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j].getNum() == 0) {
						counter++;
					}
				}
			}
			min = counter;
		}
		bw.write(Integer.toString(min));
		bw.flush();
		bw.close();

	}

	private static void DFS(int row, int column) {
		if (row >= N || column >= M) {
			return;
		}

		if (row == N - 1 && column == M - 1) {
			Edge myEdge = arr[row][column];
			cctvView();
			min = Math.min(getCannotViewCount(), min);
			myEdge.setDirect(2);
			cctvView();
			min = Math.min(getCannotViewCount(), min);
			myEdge.setDirect(3);
			cctvView();
			min = Math.min(getCannotViewCount(), min);
			myEdge.setDirect(4);
			cctvView();
			min = Math.min(getCannotViewCount(), min);
			return;
		}

		Edge myEdge = arr[row][column];
		int num = myEdge.getNum();
		int dir = myEdge.getDirect();
		int i = row;
		int j = column;
		if (num != 0 && num != 6 && num != -1) {
			// CCTV인 경우
			int nextI = 0;
			int nextJ = 0;
			if (j == M - 1) {
				nextI = i + 1;
				nextJ = 0;
			} else {
				nextI = i;
				nextJ = j + 1;
			}
			arr[i][j].setDirect(1);
			DFS(nextI, nextJ);
			arr[i][j].setDirect(2);
			DFS(nextI, nextJ);
			arr[i][j].setDirect(3);
			DFS(nextI, nextJ);
			arr[i][j].setDirect(4);
			DFS(nextI, nextJ);
		} else {
			int nextI = 0;
			int nextJ = 0;
			if (j == M - 1) {
				nextI = i + 1;
				nextJ = 0;
			} else {
				nextI = i;
				nextJ = j + 1;
			}
			DFS(nextI, nextJ);
		}
	}

	private static int getCannotViewCount() { // 사각 지대 개수 구하기
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Edge myEdge = tmp[i][j];
				int num = myEdge.getNum();
				if (num == 0) {
					count++;
				}
			}
		}
		return count;
	}

	private static void cctvView() { // 현재 바라보고 있는 CCTV로 볼 수 있는 곳 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Edge myEdge = tmp[i][j];
				if (myEdge.getNum() > 0 && myEdge.getNum() < 6) {
					int dir = myEdge.getDirect();
					int num = myEdge.getNum();
					if (dir == 1) { // 방향이 어디인지에 따라 달라짐
						if (num == 1) { // CCTV의 종류
							setView(i, j, 1);
						} else if (num == 2) {
							setView(i, j, 1);
							setView(i, j, 3);
						} else if (num == 3) {
							setView(i, j, 1);
							setView(i, j, 2);

						} else if (num == 4) {
							setView(i, j, 1);
							setView(i, j, 2);
							setView(i, j, 3);
						} else {
							setView(i, j, 1);
							setView(i, j, 2);
							setView(i, j, 3);
							setView(i, j, 4);
						}

					} else if (dir == 2) {
						if (num == 1) {
							setView(i, j, 2);
						} else if (num == 2) {
							setView(i, j, 2);
							setView(i, j, 4);
						} else if (num == 3) {
							setView(i, j, 2);
							setView(i, j, 3);
						} else if (num == 4) {
							setView(i, j, 2);
							setView(i, j, 3);
							setView(i, j, 4);
						} else {
							setView(i, j, 1);
							setView(i, j, 2);
							setView(i, j, 3);
							setView(i, j, 4);
						}

					} else if (dir == 3) {
						if (num == 1) {
							setView(i, j, 3);
						} else if (num == 2) {
							setView(i, j, 1);
							setView(i, j, 3);

						} else if (num == 3) {
							setView(i, j, 3);
							setView(i, j, 4);

						} else if (num == 4) {
							setView(i, j, 3);
							setView(i, j, 4);
							setView(i, j, 1);

						} else {
							setView(i, j, 1);
							setView(i, j, 2);
							setView(i, j, 3);
							setView(i, j, 4);
						}

					} else {
						if (num == 1) {
							setView(i, j, 4);
						} else if (num == 2) {
							setView(i, j, 2);
							setView(i, j, 4);

						} else if (num == 3) {
							setView(i, j, 4);
							setView(i, j, 1);
						} else if (num == 4) {
							setView(i, j, 4);
							setView(i, j, 1);
							setView(i, j, 2);

						} else {
							setView(i, j, 1);
							setView(i, j, 2);
							setView(i, j, 3);
							setView(i, j, 4);
						}

					}
				}
			}
		}
	}

	private static void setView(int i, int j, int direct) {
		if (direct == 1) {
			for (int k = j + 1; k < M; k++) {
				Edge newEdge = tmp[i][k];
				int newDir = newEdge.getDirect();
				int newNum = newEdge.getNum();
				if (newNum == 6) {
					break;
				} else {
					if (newNum == -1 || newNum >= 1 && newNum <= 5) {
						continue;
					} else
						tmp[i][k] = new Edge(-1, 0);
				}
			}
		} else if (direct == 2) {
			for (int k = i - 1; k >= 0; k--) {
				Edge newEdge = tmp[k][j];
				int newDir = newEdge.getDirect();
				int newNum = newEdge.getNum();
				if (newNum == 6) {
					break;
				} else {

					if (newNum == -1 || newNum >= 1 && newNum <= 5) {
						continue;
					}
					tmp[k][j] = new Edge(-1, 0);
				}
			}
		} else if (direct == 3) {
			for (int k = j - 1; k >= 0; k--) {
				Edge newEdge = tmp[i][k];
				int newDir = newEdge.getDirect();
				int newNum = newEdge.getNum();
				if (newNum == 6) {
					break;
				} else {

					if (newNum == -1 || newNum >= 1 && newNum <= 5) {
						continue;
					}
					tmp[i][k] = new Edge(-1, 0);
				}
			}
		} else if (direct == 4) {
			for (int k = i + 1; k < N; k++) {
				Edge newEdge = tmp[k][j];
				int newDir = newEdge.getDirect();
				int newNum = newEdge.getNum();
				if (newNum == 6) {
					break;
				} else {
					if (newNum == -1 || newNum >= 1 && newNum <= 5) {
						continue;
					}
					tmp[k][j] = new Edge(-1, 0);
				}
			}
		}
	}
}