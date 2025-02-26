import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	private static int day, oneMon, threeMon, year;
	private static int[] plan = new int[13];
	private static int answer;

    public static void main(String[] args) throws Exception {
    //	System.setIn(new FileInputStream("1952.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            int[] rates = parseInts(in.readLine());
            day = rates[0];
            oneMon = rates[1];
            threeMon = rates[2];
            year = rates[3];
            
            int[] plans = parseInts(in.readLine());
            for (int i = 0; i < 12; i++) {
                plan[i + 1] = plans[i];
            }
            
            answer = year;  
            dfs(1, 0);
            
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void dfs(int month, int cost) {
        if (month > 12) {
            answer = Math.min(answer, cost);
            return;
        }
        if (plan[month] > 0) {
            dfs(month + 1, cost + day * plan[month]);   
            dfs(month + 1, cost + oneMon);              
            dfs(month + 3, cost + threeMon);            
        } else {
            dfs(month + 1, cost);
        }
    }

    private static int[] parseInts(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') count++;
        }
        int[] arr = new int[count + 1];
        int index = 0, arrIndex = 0;
        while (index < s.length()) {
            int nextSpace = s.indexOf(' ', index);
            if (nextSpace == -1) {
                arr[arrIndex++] = Integer.parseInt(s.substring(index));
                break;
            } else {
                arr[arrIndex++] = Integer.parseInt(s.substring(index, nextSpace));
                index = nextSpace + 1;
            }
        }
        return arr;
    }
}