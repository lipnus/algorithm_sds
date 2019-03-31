package 로봇청소기;

import javax.swing.text.Position;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int dir;
    static int[][] map;
    static int cleanCount =0;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Position startPos = new Position(n+1, m+1);
        dir = Integer.parseInt(st.nextToken());


        //지도
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        loop(startPos);

        System.out.println(cleanCount);


    }


    static void loop(Position start){

        Position now = start;
        cleanCount++;
        map[start.n][start.m] =2;

        while (true){

            //왼쪽부터 4방향 탐색
            boolean isMoved = false;
            for(int i=0; i<4; i++){

                rotateDir();
                Position next = getNext(now);

                if(isCanGo(next) && !isCleaned(next)){

                    map[next.n][next.m] = 2;
                    cleanCount++;

                    now = next;
                    isMoved=true;
                    break;
                }


            }

            //움직이지 못했으면 후진
            if(isMoved==false){
                Position next = getBack(now);

                if(isCanGo(next)){
                    now = next;
                }else{ //후진불가능
                    break;
                }
            }
        }
    }

    static void print(){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
            }
            System.out.println();
        }
    }


    //왼쪽으로 90도 회전
    static void rotateDir(){
        if(dir==0) dir=3;
        else if(dir==1) dir=0;
        else if(dir==2) dir=1;
        else if(dir==3) dir=2;
    }

    /**
     * 현재위치와 방향에서 이동해야 할 위치를 반환
     * @param now
     * @return
     */
    static Position getNext(Position now){

        if(dir==0) return new Position(now.n-1, now.m);
        if(dir==1) return new Position(now.n, now.m+1);
        if(dir==2) return new Position(now.n+1, now.m);
        return new Position(now.n, now.m-1);
    }


    /**
     * 현재위치에서 후진했을때의 위치를 리턴
     * @param now
     * @return
     */
    static Position getBack(Position now){

        if(dir==0) return new Position(now.n+1, now.m);
        if(dir==1) return new Position(now.n, now.m-1);
        if(dir==2) return new Position(now.n-1, now.m);
        return new Position(now.n, now.m+1);
    }


    //갈 수 있는 곳인지 리턴
    static boolean isCanGo(Position next){

        //벽인가
        if(map[next.n][next.m]==1) return false;

        return true;
    }


    //청소를 했는지 여부를 리턴
    static boolean isCleaned(Position next){
        if(map[next.n][next.m] == 2) return true;
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
