import java.io.*;
import java.util.*;

public class Main {
	static int[][] papers;

	private static int one = 0;
	private static int zero = 0;
	private static int minusOne = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		papers = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				papers[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		countPaper(0, 0, N);
		System.out.println(minusOne);
		System.out.println(zero);
		System.out.println(one);

	}

	private static boolean isUniform(int x, int y, int size) {
		int value = papers[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (papers[i][j] != value) {
					return false;
				}
			}
		}
		return true;
	}

	private static void countPaper(int x, int y, int size) {

		int sum = 0;

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				sum += papers[i][j];
			}
		}

		// isUniform으로 이 size*size 안에 있는 숫자가 다 같은 수인지 확인
		// why? +1 -1 이 같이 있을 경우 모두 다 0이 되니까 sum == 0 구간으로 들어갈 수 있다.
		if (isUniform(x, y, size)) {
			if (sum == 0) {
				zero++;
			} else if (sum == size * size) {
				one++;
			} else if (sum == -(size * size)) {
				minusOne++;
			}
			return;
		}

		int paperSize = size / 3;

		// 9개로 쪼개기
		countPaper(x, y, paperSize); // (1 1)
		countPaper(x, y + paperSize, paperSize); // (1 2)
		countPaper(x, y + paperSize * 2, paperSize); // (1 3)
		countPaper(x + paperSize, y, paperSize); // (2 1)
		countPaper(x + paperSize, y + paperSize, paperSize); // (2 2)
		countPaper(x + paperSize, y + paperSize * 2, paperSize); // (2 3)
		countPaper(x + 2 * paperSize, y, paperSize); // (3 1)
		countPaper(x + 2 * paperSize, y + paperSize, paperSize); // (3 2)
		countPaper(x + 2 * paperSize, y + paperSize * 2, paperSize); // (3 3)

	}

}