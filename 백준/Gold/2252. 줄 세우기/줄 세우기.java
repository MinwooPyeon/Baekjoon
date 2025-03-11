import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> ll = new ArrayList<>();
    static StringBuffer sb = new StringBuffer();
    static int N;
    static int[] count;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        count = new int[N+1];
        for(int i = 0; i<=N; i++)
            ll.add(new ArrayList<>());

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            ll.get(A).add(B);
            count[B]++;
        }

        //위상 정렬
        topologicalSort();

        System.out.println(sb);
    }

    static void topologicalSort(){
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i<=N; i++)
            if(count[i] == 0)
                q.offer(i);

        for(int i = 0; i<N; i++) {
            if (!q.isEmpty()) {
                int num = q.poll();
                sb.append(num).append(" ");

                for (int j = 0; j < ll.get(num).size(); j++) {
                    count[ll.get(num).get(j)]--;

                    if (count[ll.get(num).get(j)] == 0)
                        q.offer(ll.get(num).get(j));
                }
            }
        }
    }
}