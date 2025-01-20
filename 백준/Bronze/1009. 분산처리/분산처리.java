import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t; 
        int[] result; 

        t = Integer.parseInt(bf.readLine());
        result = new int[t];

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()); 
            int b = Integer.parseInt(st.nextToken()); 

            int data = 1; 
            for (int j = 0; j < b; j++) {
                data *= a; 
                data %= 10; // 1의 자리 숫자만 유지
            }

            // 계산 결과가 0이면, 실제로는 10번 컴퓨터가 처리함
            if (data == 0) {
                data = 10;
            }

            result[i] = data;
        }
        for (int i = 0; i < t; i++) {
            System.out.println(result[i]);
        }
    }
}
