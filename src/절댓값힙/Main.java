package 절댓값힙;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static PriorityQueue<AbsValue> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt( br.readLine() );

        for(int i=0; i<N ;i++){
            int input = Integer.parseInt(br.readLine());

            if(input==0){
                if(!pq.isEmpty())  System.out.println( pq.poll().value );
                else System.out.println(0);
            }else{
                pq.offer(new AbsValue(input));
            }

        }
    }

    static class AbsValue implements Comparable<AbsValue>{
        int value;

        AbsValue(int value){
            this.value = value;
        }

        @Override
        public int compareTo(AbsValue o) {
            if(Math.abs(this.value) < Math.abs(o.value)){ //절댓값 작은 거 우선
                return -1;
            }else if(Math.abs(this.value)==Math.abs(o.value)){ //절대값 같으면, 값이 작은 거 우선
                if(this.value < o.value) return -1;
                else if(this.value == o.value) return 0;
                else return 1;
            }else{
                return 1;
            }
        }
    }
}