package 스타트와링크;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int total=0;
    static int minDiff=Integer.MAX_VALUE;


    public  static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        //입력받기
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        combination(N,N/2,1, new int[N/2], 0);
        System.out.println(minDiff);
    }




    //명단에 있는 사람들끼리 팀을 구성했을때의 점수
    static int sum(int[] people){

        int sum = 0;

        for(int i=0; i<people.length; i++){
            for(int j=0; j<people.length; j++){
                sum += map[ people[i] ][ people[j] ];
            }
        }
        return sum;
    }


    static int[] anotherTeam(int[] team){

        int[] anotherTeam = new int[team.length];
        int index = 0;

        for(int i=1; i<=team.length*2; i++){

            boolean isSame = false;
            for(int j=0; j<team.length; j++){
                if(i == team[j]){
                    isSame = true;
                    break;
                }
            }
            if(isSame==false) anotherTeam[index++] = i;
        }

        return anotherTeam;
    }


    static void combination(int n, int r, int target, int[] arr, int index){

        if(r==0){
            int teamA = sum(arr);
            int teamB = sum( anotherTeam(arr) );
            minDiff = Math.min(minDiff, Math.abs(teamA-teamB));
            return;
        }

        if(target==n+1) return;

        arr[index] = target;
        combination(n, r-1, target+1, arr, index+1); //선택
        combination(n, r, target+1, arr, index); //안선택
    }
}
