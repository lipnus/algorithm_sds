package 직사각형의한점구하기;

import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args){

        int[][] v = { {1,1}, {2,2}, {1,2} };
        int[] answer = new int[2];

        PriorityQueue<Integer> pq_x = new PriorityQueue<>();
        PriorityQueue<Integer> pq_y = new PriorityQueue<>();

        //넣기
        for(int i=0; i<3; i++){
            pq_x.add( v[i][0] );
            pq_y.add( v[i][1] );
        }

        int temp_x = pq_x.poll();
        int temp_y = pq_y.poll();

        if(temp_x == pq_x.poll()) answer[0] = pq_x.poll();
        else answer[0] = temp_x;

        if(temp_y == pq_y.poll()) answer[1] = pq_y.poll();
        else answer[1] = temp_y;

        System.out.println(answer[0] + ", " + answer[1]);
    }
}
