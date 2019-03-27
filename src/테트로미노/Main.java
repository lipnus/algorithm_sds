package 테트로미노;

import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[][] map;

    static int maxVal =0;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N+1][M+1];

        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                map[i][j] = sc.nextInt();
            }
        }

        type1();

        type2();

        System.out.println(maxVal);


    }

    static void type1(){

        //가로로 4칸
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M-3; j++){
                int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3];
                maxVal = Math.max(maxVal, sum);
            }
        }

        //세로로 4칸
        for(int i=1; i<=N-3; i++){
            for(int j=1; j<=M; j++){
                int sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+3][j];
                maxVal = Math.max(maxVal, sum);
            }
        }
    }



    static void type2(){

        //3x2(가로)
        for(int i=1; i<=N-1; i++){
            for(int j=1; j<=M-2; j++){

                int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j] + map[i+1][j+1] + map[i+1][j+2];

                cal(sum, map[i][j], map[i+1][j]);
                cal(sum, map[i][j+2], map[i+1][j+2]);

                cal(sum, map[i][j+1], map[i][j+2]);
                cal(sum, map[i][j], map[i][j+1]);
                cal(sum, map[i+1][j], map[i+1][j+1]);
                cal(sum, map[i+1][j+1], map[i+1][j+2]);

                cal(sum, map[i][j+2], map[i+1][j]);
                cal(sum, map[i][j], map[i+1][j+2]);

                cal(sum, map[i][j], map[i][j+2]);
                cal(sum, map[i+1][j], map[i+1][j+2]);
            }
        }

        //2x3(세로)
        for(int i=1; i<=N-2; i++){
            for(int j=1; j<=M-1; j++){

                int sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i][j+1] + map[i+1][j+1] + map[i+2][j+1];

                //4칸 뚱땡이는 위에서 모두 체크되어서 안해도 괜찮음

                cal(sum, map[i][j], map[i+1][j]);
                cal(sum, map[i+1][j], map[i+2][j]);
                cal(sum, map[i][j+1], map[i+1][j+1]);
                cal(sum, map[i+1][j+1], map[i+2][j+1]);

                cal(sum, map[i][j], map[i+2][j+1]);
                cal(sum, map[i][j+1], map[i+2][j]);

                cal(sum, map[i][j], map[i+2][j]);
                cal(sum, map[i][j+1], map[i+2][j+1]);
            }
        }


    }

    static void cal(int sum, int remove1, int remove2){
        int value = sum - remove1 - remove2;
        maxVal = Math.max(maxVal, value);
    }
}
