import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        String s = stk.nextToken();
        Set<String> set = new HashSet<>();

        for (int i = 0; i <s.length(); i++) {
            for (int j = i+1; j <s.length()+1; j++) {
                set.add(s.substring(i,j));
            }
        }

        System.out.println(set.size());
    }
}