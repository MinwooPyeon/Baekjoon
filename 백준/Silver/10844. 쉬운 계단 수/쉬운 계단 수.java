import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static Long[][] floor;
    static int N;
    final static long MOD = 1000000000; //나누는 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); //계단의 길이
        floor = new Long[N+1][10]; //자리수 1부터 시작

        br.close();

        for(int i = 0; i < 10; i++){ //첫번째 자리수는 1로 초기화
            floor[1][i] = 1L;
        }

        long count = 0; //계단 개수 (누적)

        for(int i = 1; i < 10; i++){
            count += solution(N, i);
        }

        System.out.println(count%MOD);

    }

    // 파라미터 자릿 수 : position, 자리 값 : value
    public static long solution(int position, int value){
        //자릿수의 끝인 1에 도착하면 더이상 탐색 X
        if(position == 1){
            return floor[position][value];
        }

        if(floor[position][value] == null){
            //0 다음음 1밖에 못온다.
            if(value == 0){
                //자릿수 현재 위치 - 1, 자리 값 1
                floor[position][value] = solution(position-1, 1);
            }

            //9다음은 8밖에 못옴
            else if(value == 9){
                floor[position][value] = solution(position-1, 8);
            }

            //1~8이면 value에서 +1, -1한 경우의 수와 같다.
            else{
                floor[position][value] = solution(position-1, value-1) + solution(position-1, value+1);
            }
        }

        return floor[position][value] % MOD; //값을 구할때마다 MOD로 나눠주기
    }

}