import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(in.readLine());
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
      if (Math.abs(a) == Math.abs(b))
        return a - b;
      return Math.abs(a) - Math.abs(b);
    });
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < n; i++) {
      int num = Integer.parseInt(in.readLine());
      if (num == 0) {
        sb.append(pq.isEmpty() ? 0 : pq.poll()).append("\n");
      } else {
        pq.add(num);
      }
    }
    System.out.print(sb);
  }
}
