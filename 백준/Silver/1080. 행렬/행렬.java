import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n, m;
    static int[][] arr1, arr2;
    static int cnt = 0;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr1 = new int[n][m];
        arr2 = new int[n][m];

		//arr1 입력받기
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr1[i][j] = str.charAt(j) - '0';
            }
        }

	//arr2 입력받기
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr2[i][j] = str.charAt(j) - '0';
            }
        }

	//arr1와 arr2 값을 비교 -> 다르면 뒤집어서 맞춰주기!
        for(int i = 0; i<n-2; i++) {
            for(int j = 0; j<m-2; j++){
                if(arr1[i][j] != arr2[i][j]){
                    cnt ++;
                    flip(i, j);
                }
            }
        }
	//arr1와 arr2가 같은지 검사
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(arr1[i][j] != arr2[i][j]) {
                    System.out.println("-1");
                    return;
                }
            }
        }
       	//결과 출력
        System.out.println(cnt);
    }
    
    public static void flip(int i, int j){
    //뒤집기!
        for(int a = i; a < i+3; a++){
            for(int b = j; b < j+3; b++){
                arr1[a][b] = (arr1[a][b] == 1) ? 0 : 1;
            }
        }

    }

}