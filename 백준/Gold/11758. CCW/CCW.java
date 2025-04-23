import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());

		bw.write(ccw(x1, y1, x2, y2, x3, y3) + "\n");
		bw.close();
		br.close();
	}

	public static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		int a = x1 * y2 + x2 * y3 + x3 * y1;
		int b = y1 * x2 + y2 * x3 + y3 * x1;

		// 방향별로 나눔
		if (a - b > 0) {
			return 1;
		} else if (a == b) { 
			return 0;
		} else { 
			return -1;
		}
	}

}