package 톱니바퀴;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Integer>[] wh;

    public static void main(String[] args) throws Exception{

        wh = new LinkedList[5];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=1; i<=4; i++){
            String line = br.readLine();
            wh[i] = new LinkedList<>();

            for(int j=0; j<8; j++){
                wh[i].add( line.charAt(j)-'0' );
            }
        }

        N = Integer.parseInt(br.readLine());


        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int wise = Integer.parseInt(st.nextToken());
            play(n, wise, 0);
        }

//        print();
        System.out.println(getScore());
    }


    static void print(){

        for(int i=1; i<=4; i++){
            for(int w: wh[i]){
                System.out.printf("%d", w);
            }
            System.out.println();
        }

    }




    static void play(int n, int wise, int from){

        //오른쪽으로 전달
        if(from==0 || from==-1){

            if(n!=4){
                int dir = getDirection(n, 1, wise);
                play(n+1, dir, -1);
            }
        }

        //왼쪽으로 전달
        if(from==0 || from==1){

            if(n!=1){
                int dir = getDirection(n, -1, wise);
                play(n-1, dir, 1);
            }
        }

        rotate(n, wise);
    }



    //다음번째 바퀴의 회전방향 반환
    static int getDirection(int n, int direction, int wise){

        if(direction==1){
            if(wh[n].get(2) != wh[n+1].get(6)) wise*=-1;
            else wise=0;
        }

        if(direction==-1){
            if(wh[n-1].get(2) != wh[n].get(6)) wise*=-1;
            else wise=0;
        }
        return wise;
    }


    /**
     *
     * @param n 몇번 바퀴
     * @param wise 1:시계방향, -1:반시계방향
     */
    static void rotate(int n, int wise){

        //시계방향 회전
        if(wise==1){
            int temp = wh[n].get(7);
            wh[n].remove(7);
            wh[n].add(0, temp);
        }

        //반시계방향 회전
        if(wise==-1){
            int temp = wh[n].get(0);
            wh[n].remove(0);
            wh[n].add(temp);
        }
    }



    static int getScore(){
        int sum = 0;
        if(wh[1].get(0)==1) sum+=1;
        if(wh[2].get(0)==1) sum+=2;
        if(wh[3].get(0)==1) sum+=4;
        if(wh[4].get(0)==1) sum+=8;
        return sum;
    }


}
