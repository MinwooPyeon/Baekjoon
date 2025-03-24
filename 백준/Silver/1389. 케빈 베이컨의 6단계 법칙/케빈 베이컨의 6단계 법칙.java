import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int KB[] = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            int[] distance = new int[N + 1];
            Arrays.fill(distance, -1);
            distance[i] = 0;
            while (!queue.isEmpty()) {
                int current = queue.poll();
                for (int next : graph.get(current)) {
                    if (distance[next] == -1) {
                        distance[next] = distance[current] + 1;
                        queue.add(next);
                    }
                }
            }
            int sum = 0;
            for (int j = 1; j < N + 1; j++) {
                sum += distance[j];
            }
            KB[i] = sum;
        }

        int min = Integer.MAX_VALUE;
        int answer = 0;
        for (int i = 1; i < N + 1; i++) {
            if (KB[i] < min) {
                min = KB[i];
                answer = i;
            }
        }
        System.out.println(answer);
    }
}