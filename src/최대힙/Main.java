package 최대힙;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt( br.readLine() );

        for(int i=0; i<N ;i++){
            int input = Integer.parseInt(br.readLine());

            if(input==0){
                if(!pq.isEmpty()) System.out.println( pq.poll() );
                else System.out.println(0);
            }else{
                pq.offer(input);
            }
        }
    }
}
