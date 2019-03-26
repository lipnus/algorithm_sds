package 시험감독;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] room;
    static int B;
    static int C;

    static long count=0;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        room = new int[N+1];



        //입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            room[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        //세어보자
        for(int i=1; i<=N; i++){

            if(room[i] <= B){
                count++;
            }else{
                count++;
                if((room[i]-B)%C==0) count += (room[i]-B)/C;
                else count += (room[i]-B)/C +1;
            }
        }

        System.out.println(count);

    }
}
