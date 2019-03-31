package 연구소;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[][] map;

    static List<Position> ground = new ArrayList<>();

    static int maxValue=0;
    static int zeroCount=0;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N+1][M+1];

        //인풋
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j]==0) ground.add(new Position(i,j));
            }
        }

        int n = ground.size();
        combination(n, 3, new int[3], 0, 0);

    }

    //3개의 숫자를 선택
    static void combination(int n, int r, int[] arr, int index, int target){

        if(r==0){
            bfs( makeWall(arr) );
        }
        else if(target==n) return;
        else{
            arr[index] = target;
            combination(n, r-1, arr, index+1, target+1); //선택
            combination(n, r, arr, index, target+1); //안선택
        }
    }

    //3개의 벽을 건설하고 deepCopy된 map을 반환
    static int[][] makeWall(int[] wallPoint){

        int[][] nMap = new int[N+1][M+1];

        //맵 복사
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                nMap[i][j] = map[i][j];
            }
        }

        //3개의 벽 건설
        for(int i=0; i<3; i++){
            Position p = ground.get(wallPoint[i]);
            nMap[p.n][p.m] = 1;
        }

        return nMap;
    }

    //입력받은 지도에서 바이러스를 배양하고 빈칸을 측정
    static void bfs(int[][] map){

    }

    //갈 수 있는 땅인가
    static boolean isCango(Position p){
        return true;
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