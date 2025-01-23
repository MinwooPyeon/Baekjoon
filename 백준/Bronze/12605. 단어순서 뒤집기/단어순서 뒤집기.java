import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        Stack<String> stack = new Stack<>();


        for(int i=0;i<testCase;i++) {
            String input = br.readLine();
            String[] arr = input.split(" ");
            for (int j = 0; j < arr.length; j++)
                stack.push(arr[j]);


            System.out.print("Case #"+(i+1)+": ");
            while(!stack.isEmpty()){
                System.out.print(stack.pop());
                System.out.print(" ");
                if(stack.isEmpty())
                    System.out.println();
            }
        }




    }
}