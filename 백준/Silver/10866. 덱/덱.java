import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> q = new LinkedList<>(); 
        int N =Integer.parseInt(br.readLine());
        while(N -->0){
           StringTokenizer st = new StringTokenizer(br.readLine()," ");
           
            switch(st.nextToken()){
                case"push_front" :
                    q.offerFirst(Integer.parseInt(st.nextToken()));
                    break;
                    
               case"push_back" : 
                    q.offerLast(Integer.parseInt(st.nextToken()));
                    break;
                    
                case "pop_front" :
                    if(q.isEmpty())
                        bw.write(-1+"\n");
                    else
                        bw.write(q.pollFirst()+"\n");
                    break;
                    
                case "pop_back" :
                    if(q.isEmpty())
                        bw.write(-1+"\n");
                    else
                        bw.write(q.pollLast()+"\n");
                    break;
                    
                case "size" :
                    bw.write(q.size()+"\n");
                    break;
                    
                case "empty" : 
                    if(q.isEmpty())
                        bw.write(1+"\n");
                    else
                        bw.write(0+"\n");
                    break;

                 case "front" : 
                    if(q.isEmpty())
                        bw.write(-1+"\n");
                    else
                        bw.write(q.peek()+"\n");
                    break;
                        
                 case "back" : 
                    if(q.isEmpty())
                        bw.write(-1+"\n");
                    else
                        bw.write(q.peekLast()+"\n");
                    break;
            }
        }
        bw.flush();
    }
}