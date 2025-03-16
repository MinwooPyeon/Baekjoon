import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.FileHandler;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        List<Integer> answer = new ArrayList<>();

        int T = Integer.parseInt(br.readLine());

        int[] list = new int[T];

        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());
            int[] log = new int[N];
            Deque<Integer> deque = new ArrayDeque<>();
            String temp = br.readLine();
            st = new StringTokenizer(temp);
            int j=0;
            while(st.hasMoreTokens()){
                log[j]=Integer.parseInt(st.nextToken());
                j++;
            }
            Arrays.sort(log);
            for(int k=N-1;k>=0;k--){
                if(k%2==0)
                    deque.addFirst(log[k]);
                else
                    deque.addLast(log[k]);
            }

            for(int k=0;k<N;k++){
                log[k]=deque.poll();
            }

            int max=-1;

            for(int k=0;k<N;k++){
                max=Math.max(Math.abs(log[k]-log[(k+1)%N]),max);
            }

            answer.add(max);
        }

        for(int i : answer){
            bw.write(Integer.toString(i));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}