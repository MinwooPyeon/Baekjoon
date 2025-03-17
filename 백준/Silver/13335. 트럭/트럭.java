import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    int n = Integer.parseInt(st.nextToken());
    int w = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());

    Queue<Integer> truck = new LinkedList<>();
    Queue<Integer> bridge = new LinkedList<>();
    int count = 0;
    int bridgeWeight = 0;
    st = new StringTokenizer(br.readLine(), " ");

    // 트럭들의 무게를 큐에 넣기
    for (int i = 0; i < n; i++) {
      truck.add(Integer.parseInt(st.nextToken()));
    }

    // 다리의 길이만큼 초기화
    for (int i = 0; i < w; i++) {
      bridge.add(0);
    }

    while (!bridge.isEmpty()) {
      count++;
      bridgeWeight -= bridge.poll();
      if (!truck.isEmpty()) {
        if (truck.peek() + bridgeWeight <= L) {
          bridgeWeight += truck.peek();
          bridge.add(truck.poll());
        } else {
          bridge.add(0);
        }
      }
    }

    sb.append(count);
    System.out.println(sb);
  }
}
