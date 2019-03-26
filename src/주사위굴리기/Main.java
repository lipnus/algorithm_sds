package 주사위굴리기;

import javax.swing.text.Position;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[][] map;

    static Position pos;
    static int K;

    static int[] dice = new int[7];

    //1~4
    static int[] dn = {0,   0, 0, -1, 1};
    static int[] dm = {0,   1, -1, 0, 0};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N+1][M+1];

        int n = sc.nextInt();
        int m = sc.nextInt();
        pos = new Position(n,m);

        K = sc.nextInt();


        //맵을 채워넣음
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }


        //명령을 수행
        for(int i=1; i<=K; i++){

            int D = sc.nextInt();

            Position nextP = new Position(pos.n+dn[D], pos.m+dm[D]);

            if( !isIn(nextP) ) continue;

            rotate(D);
            changeBottom(nextP);
            System.out.println( getTop() );
            pos = new Position(nextP.n, nextP.m);

        }

    }


    static void rotate(int dir){

        //왼쪽회전
        if(dir==1){
            int temp = dice[3];
            dice[3] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        }

        //오른쪽회전
        if(dir==2){
            int temp = dice[3];
            dice[3] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }

        //위쪽으로 회전
        if(dir==3){
            int temp = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[1];
            dice[1] = temp;
        }

        //아래로 회전
       if(dir==4){
            int temp = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        }
    }


    static int getTop(){
        return dice[3];
    }


    static void changeBottom(Position p){

        if(map[p.n][p.m]==0){
            map[p.n][p.m] = dice[6];
        }else{
            dice[6] = map[p.n][p.m];
            map[p.n][p.m] = 0;
        }

    }

    static boolean isIn(Position p){
        if(0<= p.n && p.n <N && 0<= p.m && p.m <M) return true;
        return false;
    }




    static class Position{
        int n;
        int m;

        Position(int n, int m){
            this.n = n;
            this.m = m;
        }
    }
}
