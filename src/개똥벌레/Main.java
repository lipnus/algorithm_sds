package 개똥벌레;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;       //동굴길이
    static int H;       //동굴높이
    static int[] jong;  //종유석
    static int[] suck;  //석순

    static int[] jong_sum; //종유석 칸별 누적
    static int[] suck_sum; //석순 칸별 누적

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        jong = new int[H+1];
        suck = new int[H+1];
        suck_sum = new int[H+2];
        jong_sum = new int[H+2];

        for(int i=1; i<=N; i++){

            int a = Integer.parseInt(br.readLine());

            if(i%2==1) suck[a]++;
            else jong[a]++;
        }


        //석순과 종유석(거꾸로인데 일단 바로 구함) 누적합
        for(int i=H; 1<=i; i--){
            suck_sum[i] = suck_sum[i+1] + suck[i];
            jong_sum[i] = jong_sum[i+1] + jong[i];
        }


        int min=Integer.MAX_VALUE;
        int minCount = 0;
        for(int i=1; i<=H; i++){


            if(suck_sum[i] + jong_sum[H+1-i] < min){
                min = suck_sum[i] + jong_sum[H+1-i];
                minCount=1;
            }else if(suck_sum[i] + jong_sum[H+1-i] == min){
                minCount++;
            }
        }

        System.out.printf("%d %d",min, minCount);


    }
}
