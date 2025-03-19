import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int n;
  static int max = Integer.MIN_VALUE;
  static int min = Integer.MAX_VALUE;
  static int[] operator = new int[4];
  static int[] numbers;

  static boolean isEmpty() {
    int sum = 0;
    for (int i = 0; i < 4; i++) {
      sum += operator[i];
    }
    return sum == 0;
  }

  static void cal(int val, int idx) {
    if (isEmpty()) {
      min = Math.min(min, val);
      max = Math.max(max, val);
      return;
    }

    for (int i = 0; i < 4; i++) {
      if (operator[i] > 0) {
        operator[i]--;

        switch (i) {
          case 0:
            cal(val + numbers[idx], idx + 1);
            break;
          case 1:
            cal(val - numbers[idx], idx + 1);
            break;
          case 2:
            cal(val * numbers[idx], idx + 1);
            break;
          case 3:
            cal(val / numbers[idx], idx + 1);
            break;
          default:
            break;
        }

        operator[i]++;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    n = Integer.parseInt(in.readLine());
    numbers = new int[n];

    st = new StringTokenizer(in.readLine(), " ");

    for (int i = 0; i < n; i++) {
      numbers[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(in.readLine(), " ");

    for (int i = 0; i < 4; i++) {
      operator[i] = Integer.parseInt(st.nextToken());
    }

    in.close();

    cal(numbers[0], 1);

    sb.append(max).append('\n').append(min);

    System.out.print(sb);

  }
}
