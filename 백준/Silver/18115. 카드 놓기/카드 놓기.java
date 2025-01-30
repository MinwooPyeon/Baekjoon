import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(new StringBuilder(br.readLine()).reverse().toString());
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == 1) {
                deque.addFirst(i);
            } else if (num == 2) {
                int top = deque.removeFirst();
                deque.addFirst(i);
                deque.addFirst(top);
            } else {
                deque.addLast(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (deque.size() != 0) {
            sb.append(deque.removeFirst() + " ");
        }

        System.out.println(sb);
    }
}