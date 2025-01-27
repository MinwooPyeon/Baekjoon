import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine()); 
		
		Map<String, Integer> map = new LinkedHashMap<>();
		List<Integer> list;
		StringTokenizer st;
		String name;
		
		while (n-- > 0) {
			
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < 7; j++) {
					name = st.nextToken();
					if (name.equals("-")) {
						continue;
					}
					
					if (!map.containsKey(name)) {
						map.put(name, 0);
					}
					
					map.put(name, map.get(name) + time(i));
				}
			}
		}
		
		if (map.size() == 0) {
			sb.append("Yes");
		} else {
			list = new ArrayList<>(map.values());
			Collections.sort(list);
			
			if (list.get(list.size() - 1) - list.get(0) > 12) {
				sb.append("No");
			} else {
				sb.append("Yes");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int time(int t) {
		if (t == 1 || t == 3) {
			return 4;
		} else if (t == 2) {
			return 6;
		} else {
			return 10;
		}
	}
}