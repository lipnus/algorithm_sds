package 우선순위큐_힙;

import java.util.Scanner;

public class Main {

    static int[] heap;
    static int MAX_SIZE=100;
    static int size;

    static int T;
    static int N;

    public static void main(String[] args){

        heap = new int[MAX_SIZE+1]; //1부터 채운다

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for(int t=1; t<=T; t++){
            init();
            N= sc.nextInt();

            for(int i=0; i<N; i++){
                push(sc.nextInt());
            }

            System.out.print("heap[]: "); print();
            System.out.println("\n======================================");


            while (0<size){
                System.out.printf("%d ", poll());
            }
        }

    }

    static void init(){
        size = 0;
    }

    /**
     * 제일 마지막에 넣고 올라가면서 update
     * @param input
     */
    static void push(int input){
        if(MAX_SIZE < size+1) return;

        heap[++size] = input; //제일 마지막에 넣는다

        int cur = size;
        while(0<cur && heap[cur]<heap[cur/2]){ //root까지 가거나, 부모보다 커질때까지.
            swap(cur, cur/2);
            cur = cur/2;
        }
    }


    /**
     * root를 빼고 마지막걸로 바꾼다.
     * leaf level까지 내려가면서 update
     * 두 자손 중 더 작은거랑 바꿔야 한다!
     * @return
     */
    static int poll(){
        int value = heap[1];
        heap[1] = heap[size--];
        int cur = 1;

        while(2*cur <= size){ //현재노드의 자손이 있을 때 까지

            int next;
            if(2*cur+1 <= size) next = heap[2*cur]<heap[2*cur+1] ? 2*cur : 2*cur+1; //자손이 2개인 경우
            else next = 2*cur; //자손이 1개인 경우


            if(heap[next] < heap[cur] ){
                swap(cur, next);
                cur = next;
            }else{
                break;
            }
        }

        return value;
    }


    /**
     * 배열 순서대로 출력해줌
     */
    static void print(){
        for(int i=1; i<=size; i++){
            System.out.printf("%d ", heap[i]);
        }
    }

    static void swap(int a, int b){
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }


}
