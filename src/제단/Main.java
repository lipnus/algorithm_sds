package 제단;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long[][] D; //슬라이딩 윈도우 - i번째(두개로 슬라이딩) 높이가 j인 경우의 수
    static final long MOD = 1000000007;


    public static void main(String[] args)  throws Exception{
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        D = new long[2][10001]; //D[0][]:홀수열, D[1][]:짝수열


		//첫항 세팅
        int first = Integer.parseInt( st.nextToken() ); //첫항은 뭘 받던 1
        if(first==0 || first==-1) D[0][0]=1;
        else D[0][0]=0;


        for(int i=2; i<=N; i++) {

            int n = Integer.parseInt(st.nextToken());

            //이번에 받을 것 초기화
            if(i%2==0) Arrays.fill(D[1], 0);
            else Arrays.fill(D[0], 0);

            //------------------------------------------------------------------
            if(n==-1) { //미정

                //높이0은 이전거 [그대로],[내려오기]로 두가지만 가능.
                if(i%2==0) D[1][0]=D[0][0]%MOD + D[0][1]%MOD;
                else D[0][0]=D[1][0]%MOD + D[1][1]%MOD;

                for(int j=1; j<=5000; j++) {

                    if(i%2==0) D[1][j] = D[0][j-1]%MOD + D[0][j]%MOD + D[0][j+1]%MOD;
                    else D[0][j] = D[1][j-1]%MOD + D[1][j]%MOD + D[1][j+1]%MOD;
                }

            }else if(n==0) { //값이 0이면 아래에서는 올 수 없다

                if(i%2==0) D[1][0] = D[0][0]%MOD + D[0][1]%MOD;
                else D[0][0] = D[1][0]%MOD + D[1][1]%MOD;

            }else { //값을 안다 - 이번 열의 높이는 n

                if(i%2==0) D[1][n] = D[0][n-1]%MOD + D[0][n]%MOD + D[0][n+1]%MOD;
                else D[0][n] = D[1][n-1]%MOD + D[1][n]%MOD + D[1][n+1]%MOD;

            }
            //------------------------------------------------------------------


        }//for


        if(N%2==0) System.out.println( D[1][0]%MOD );
        else System.out.println( D[0][0]%MOD );

    }
}
