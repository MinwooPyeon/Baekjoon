import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] play = new int[6][3];    // 경기 결과 배열 
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            answer = 0;
            // 경기 정보 저장 
            for (int j = 0; j < 6; j++) {        // 팀 6개
                for (int k = 0; k < 3; k++) {    // 각 팀별로 (승, 무, 패) 3개
                    play[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            boolean game = false;
            for (int j = 0; j < 6; j++) {
                if (play[j][0] + play[j][1] + play[j][2] != 5) { // 승+무+패 = 5
                    game = true;
                    break;
                }
            }
            if (!game)    // 모든 국가의 경기 수가 5일 때 백트래킹
                search(0, 1);
            sb.append(answer).append(" ");
        }
        System.out.print(sb);
    }

    // 백트래킹
    private static void search(int now, int next) {
        if (answer == 1)
            return;

        if (now == 5) {
            answer = 1;
            return;
        }
        // 현재 국가가 상대 국가에 승리
        if (play[now][0] > 0 && play[next][2] > 0) {
            play[now][0]--;
            play[next][2]--;
            if (next == 5) {    
                search(now + 1, now + 2);
            } else {
                search(now, next + 1);
            }
            play[now][0]++;
            play[next][2]++;
        }
        // 현재 국가와 상대 국가가 무승부
        if (play[now][1] > 0 && play[next][1] > 0) {
            play[now][1]--;
            play[next][1]--;
            if (next == 5) {
                search(now + 1, now + 2);
            } else {
                search(now, next + 1);
            }
            play[now][1]++;
            play[next][1]++;
        }
        // 현재 국가가 상대 국가에 패배
        if (play[now][2] > 0 && play[next][0] > 0) {
            play[now][2]--;
            play[next][0]--;
            if (next == 5) {
                search(now + 1, now + 2);
            } else {
                search(now, next + 1);
            }
            play[now][2]++;
            play[next][0]++;
        }
    }
}
