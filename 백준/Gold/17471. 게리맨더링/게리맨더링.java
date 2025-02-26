import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
 
public class Main {
	private static int N;
	private static int matrix[][];
	private static int people[];
	private static int result = Integer.MAX_VALUE;
	private static boolean isSelected[];
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N + 1]; 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			people[i] = Integer.parseInt(st.nextToken());
 
		matrix = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); 
			for (int j = 0; j < M; j++)
				matrix[i][Integer.parseInt(st.nextToken())] = 1;
		}
		isSelected = new boolean[N + 1];
		subset(1);
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	// 부분집합
	private static void subset(int cnt) {
		if (cnt == N + 1) {
			if (checkTeam(true) && checkTeam(false))
				result = Math.min(result, comp());
			return;
		}
		isSelected[cnt] = true;
		subset(cnt + 1); 
 
		isSelected[cnt] = false;
		subset(cnt + 1); 
	}
	
	// 인구수 확인
	private static boolean checkTeam(boolean flag) {
		boolean isVisited[] = new boolean[N + 1];
		LinkedList<Integer> list = new LinkedList<Integer>();
 
		for (int i = 1; i <= N; i++) {
			if (isSelected[i] == flag) {
				list.addLast(i);
				isVisited[i] = true;
				break;
			}
		}
 
		while (!list.isEmpty()) {
			int n = list.pollFirst();
 
			for (int i = 1; i <= N; i++) {
				if (isVisited[i] || flag != isSelected[i])
					continue;
 
				if (matrix[n][i] == 1) {
					list.addLast(i);
					isVisited[i] = true;
				}
			}
		}
 
		for (int i = 1; i <= N; i++) {
			if (isSelected[i] == flag && !isVisited[i]) 
				return false;
		}
 
		return true;
	}
	
	// 점수차
	private static int comp() {
		int teamA = 0, teamB = 0;
 
		for (int i = 1; i <= N; i++) {
			if (isSelected[i])	
				teamA += people[i];
			else				
				teamB += people[i];
		}
 
		return Math.abs(teamA - teamB);
	}
 
}
