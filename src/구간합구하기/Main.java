package 구간합구하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N; //숫자개수
    static int M; //변경횟수
    static int K; //구간합을 구하는 횟수

    static long[] tree;
    static int k;
    static int treeSize; //크기는 2^k
    static int startIdx; //리프노드의 시작인덱스


    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //map개수 구하기
        k=0;
        while(N > Math.pow(2, k++)){}
        treeSize = (int)Math.pow(2,k);
        tree = new long[ treeSize ];
        startIdx = treeSize/2;

        //숫자 입력받음
        for(int i=0; i<N; i++){
            int a = Integer.parseInt(br.readLine());
            tree[startIdx+i] = a;
        }

        //트리 채우기
        fillTree(1);

        //변경 및 부분합 구하기
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            //업데이트
            if(a==1){
                update(b,c); //b번째를 c로 바꾼다.
//                print();
            }

            //합 구하기

            if(a==2) System.out.println( sum(1,b,c, 1, (int)Math.pow(2,k-1)) );
        }
    }

    static void print(){
        for(long t: tree){
            System.out.printf("%d ", t);
        }
    }


    /**
     *
     * @param idx 현제위치
     * @param left 구하려고 하는 구간의 시작
     * @param right 구하려고 하는 구간의 끝
     * @param start 현재위치노드 자손 리프노드의 시작
     * @param end 현재위치노드 자손 리프노드의 끝
     * @return
     */
    static long sum(int idx, int left, int right, int start, int end){

        //현재구간이 구하려는 구간 바깥
        if(end < left || right < start){
            return 0;
        }

        //현재 구간이 구하려는 구간의 안쪽
        if(left <= start && end <=right){
            return tree[idx];
        }

        //현재구간과 구하려는 구간이 겹침
        return sum(idx*2, left, right, start, (start+end)/2) +
                sum(idx*2+1, left, right, (start+end)/2+1, end);
    }

    /**
     *
     * @param idx 번째 수를
     * @param num 으로 교체
     */
    static void update(int idx, int num){

        long delta = num - tree[startIdx + idx - 1];
        int updateIdx=startIdx + idx - 1;

//        System.out.printf("update(%d, %d) delta:%d, updateIndex:%d", idx, num, delta, updateIdx);


        while(0<updateIdx){
            tree[updateIdx] += delta;
            updateIdx /= 2;
        }
    }


    static long fillTree(int index){
        if(index < treeSize/2) return tree[index] = fillTree(index*2) + fillTree(index*2+1);
        else return tree[index];
    }
}






