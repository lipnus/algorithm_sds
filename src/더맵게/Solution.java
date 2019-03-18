package 더맵게;

import java.util.PriorityQueue;

public class Solution {

    static PriorityQueue<Integer> pq;

    public static void main(String[] args){


        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K=7;

        pq = new PriorityQueue<>();
        for(int s: scoville){
            pq.add(s);
        }

        int count = 0;
        while (true){

            //하나남음
            if(pq.size()==1){
                if(K<=pq.poll()) break; //통과
                else{
                    count = -1; //나가리
                    break;
                }
            }

            //두개이상 남음
            int last = pq.poll();
            int last2 = pq.poll();

            if(K <= last){
                break; //통과
            }

            pq.add(last+last2*2);
            count++;
        }

        System.out.println( count );
    }
}
