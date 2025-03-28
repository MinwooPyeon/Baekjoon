import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean[] prime = new boolean[1000000] ;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        // buffer받기전 실행 *중요
        get_prime();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // 0이 나올때까지 반복
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            // 만약 추측이 틀리때 확인 조건
            boolean ans = false;
            
            // 주어진 짝수 n을 두 홀수 소수의 합으로 나타내는지 확인
            for (int i = 2; i <= n / 2; i++) {
                // i와(n-i)가 모두 소수인지 확인
                if (!prime[i] && !prime[n - i]) {
                    System.out.println(n + " = " + i + " + " + (n - i));
                    ans = true;
                    break;
                }
            }
            if (ans == false) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }
    // 에라토스테네스의 체 알고리즘
    public static void get_prime() {
        
        // true = 소수아님 , false = 소수 
        prime[0] = prime[1] = true;
        
        // 2부터 최대값의 제곱근 만큼반복 예10이면 3.2정도
        for(int i = 2; i <= Math.sqrt(prime.length); i++) {

            // 만약 구하려는 수가 이미 소수면 continue
            if(prime[i]) continue;

            // 10을 예로들면 2제외 2의배수 4, 6, 8, 10 
            // 3제외 3의배수 3, 6, 9
            // 결과적으로 2, 3, 5, 7 만 남는다.

            // 따로 계산이 없어도 배수들을 차례대로 없애면 소수만 남는다. 
            for(int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}