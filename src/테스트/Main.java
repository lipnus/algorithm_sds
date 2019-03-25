package 테스트;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args){

        PriorityQueue<Test> pq = new PriorityQueue<>();

        pq.add(new Test(1));
        pq.add(new Test(3));
        pq.add(new Test(5));

        System.out.println(pq.poll().a);

    }



    static class Test implements Comparable<Test> {
        int a;

        Test(int a){
            this.a = a;
        }

        @Override
        public int compareTo(Test o) {
            if(this.a < o.a) return -1;
            else return 1;
        }
    }


}
