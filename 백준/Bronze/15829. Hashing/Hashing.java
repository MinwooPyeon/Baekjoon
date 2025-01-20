import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int L = sc.nextInt();
        String s = sc.next();
        
        long hash = 0;
        long power = 1;
        
        int r = 31;
        int M = 1234567891;
        
        for (int i = 0; i < L; i++) {
            int charValue = s.charAt(i) - 'a' + 1;
            hash += charValue * power;
            hash %= M; 
            power = (power * r) % M; 
        }
        
        System.out.println(hash);
    }
}
