package 감시;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;


    static List<Cam> cams;
    static int camSize;

    static int count=0;

    static int[][] map;

    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cams = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                int type = Integer.parseInt(st.nextToken());
                map[i][j] = type;
                if(1<= type && type <=5) cams.add(new Cam(i,j,type));
            }
        }
        camSize = cams.size();

        //카메라가 없는 경우
        if(camSize==0){
            updateMax(map);
            System.out.println(minValue);
            return;
        }


        for(int i=0; i<4; i++){
            play(0, new Cam[camSize], i);
        }
        System.out.println(minValue);

    }


    static void print(int[][] map){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
    }


    //카메라 방향 조합을 만든다
    static void play(int n, Cam[] camArr, int dir){

        Cam c = cams.get(n);
        camArr[n] = new Cam(c.n, c.m, c.type, dir);

        //완성
        if(n==camSize-1){
            takePhoto( copyMap(), camArr);
            return;
        }

        //4가지 방향
        for(int i=0; i<4; i++){
            play(n+1, camArr, i);
        }
    }


    //발사방향을 정함
    static void takePhoto(int[][] map, Cam[] cams){


        //카메라 조합에 따라 촬영
        for(Cam c: cams){

            if(c.type==1){
                shoot(map, c.n, c.m, c.wise);
            }
            if(c.type==2){
                shoot(map, c.n, c.m, c.wise);
                shoot(map, c.n, c.m, (c.wise+2)%4);
            }
            if(c.type==3){
                shoot(map, c.n, c.m, c.wise);
                shoot(map, c.n, c.m, (c.wise+1)%4);
            }
            if(c.type==4){
                shoot(map, c.n, c.m, c.wise);
                shoot(map, c.n, c.m, (c.wise+3)%4);
                shoot(map, c.n, c.m, (c.wise+1)%4);
            }
            if(c.type==5){
                shoot(map, c.n, c.m, 0);
                shoot(map, c.n, c.m, 1);
                shoot(map, c.n, c.m, 2);
                shoot(map, c.n, c.m, 3);
            }
        }

       updateMax(map);
    }


    //시선 쏘기
    static void shoot(int[][] map , int n, int m, int wise){

        int[] dn = {-1,0,1,0};
        int[] dm = {0,1,0,-1};

        while (true){

            //범위바깥
            if( n<1 || N<n || m<1 || M<m) return;

            //벽
            if(map[n][m]==6) return;

            map[n][m] = 7;
            n = n+dn[wise];
            m = m+dm[wise];
        }

    }




    static int[][] copyMap(){
        int[][] cMap = new int[N+1][M+1];

        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                cMap[i][j] = map[i][j];
            }
        }
        return cMap;
    }






    static void updateMax(int[][] map){

        int count=0;

        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(map[i][j]==0) count++;
            }
        }
        minValue = Math.min(minValue, count);
    }




    static class Cam{
        int n;
        int m;
        int type;
        int wise;

        Cam(int n, int m, int type) {
            this.n = n;
            this.m = m;
            this.type = type;
            this.wise = 0;
        }

        Cam(int n, int m, int type, int wise) {
            this.n = n;
            this.m = m;
            this.type = type;
            this.wise = wise;
        }

    }
}
