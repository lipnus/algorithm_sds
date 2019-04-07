package 사다리조작;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N; //사다리 수
    static int M; //놓여져 있는 가로선 수
    static int H; //한줄당 놓을 수 있는 가로선 수

    static List<Row> rows = new ArrayList<>();

    static int[][] ladder;

    static int minLadder=10;

    public static void main(String[] args)throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new int[H+1][N];

        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            ladder[n][m] = 1;
        }


        //놓을 수 있는 곳들의 좌표를 구한다
        for(int i=1; i<=H; i++){
            for(int j=1; j<N; j++){
                if(ladder[i][j]==0){
                    rows.add(new Row(i,j));
                }
            }
        }


        //원본 확인
        if(checkLadder(ladder)){
            System.out.println(0);
            return;
        }

        //1~3개 확인
        int size = rows.size();
        if (size>3) size = 3;

        for(int i=1; i<=size; i++){
            rec(0, i, 0, ladder);
            if(minLadder < i) break; //이미 종결나서 해봐야 소용없음
        }

        if(minLadder!=10) System.out.println(minLadder);
        else System.out.println(-1);


    }


    static void rec(int n, int limit, int target, int[][] ladder){

        if(n==limit){
            if(checkLadder(ladder)==true) minLadder = limit;
            return;
        }

        for(int i=target; i<rows.size(); i++){
            ladder[rows.get(i).n][rows.get(i).m]=1;
            rec(n+1, limit, target+1, ladder);
            ladder[rows.get(i).n][rows.get(i).m]=0;
        }
    }


    //입력받은 사다리의 적합성을 검사
    static boolean checkLadder(int[][] laddder){

        for(int i=1; i<=N; i++){
            if(go(i, laddder)!=i){
                return false;
            }
        }
        return true;

    }


    static void print(int[][] ladder){
        for(int i=1; i<=H; i++){
            for(int j=1; j<=N-1; j++){
                System.out.printf("%d ", ladder[i][j]);
            }
            System.out.println();
        }
    }


    static int[][] copyLadder(){
        int[][] cLadder = new int[H+1][N+1];

        for(int i=1; i<=H; i++){
            for(int j=1; j<N; j++){
                cLadder[i][j] = ladder[i][j];
            }
        }
        return cLadder;
    }

    //사다리 출발(1~N)
    static int go(int start, int[][] ladder){

        int pos = start;

        for(int i=1; i<=H; i++){

            if(pos==1){

                if(ladder[i][1]==1) pos++;
            }
            else if(1<pos&&pos<N){

                if(ladder[i][pos-1]==1) pos--;
                else if(ladder[i][pos]==1) pos++;
            }
            else if(pos==N){

                if(ladder[i][N-1]==1) pos--;
            }
        }
        return pos;
    }

    //추가로 가로선을 놓을 수 있는 곳의 위치
    static class Row{
        int n;
        int m;

        Row(int n, int m){
            this.n = n;
            this.m = m;
        }
    }
}
