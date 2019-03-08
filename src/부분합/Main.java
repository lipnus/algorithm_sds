package 부분합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N; //수의 개수
    static long S; //합
    static long[] arr;
    static int INF = 100000001;

    public static void main(String[] args)throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Long.parseLong(st.nextToken());
        arr = new long[N+1];

        //입력받음
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long minLen = INF;
        int tempSum = 0;

        int left=1;
        int right=0;

        while(true){

            /**
             * [종결]
             * right가 끝에 닿았는데 S못넘음
             * left가 끝에 닿음
             */
            if(right==N && tempSum<=S) break;
            if(left==N) break;

            if(tempSum<=S) tempSum += arr[++right];            //S보다 작거나 같으면 rightIdx이동(길이늘림)
            else{
                if(right==left) tempSum += arr[++right];       //S보다 크지만, left가 바싹 따라와서 도망
                else tempSum -= arr[left++];                   //S보다 큰 일반적인 경우 left이동(길이줄어듬)
            }

            if(S <= tempSum){
//                System.out.printf("요시 %d~%d = %d\n", left, right, right-left+1);
                minLen = Math.min(minLen, right-left+1);
            }
        }


        //출력
        if(minLen==INF) System.out.println(0);
        else System.out.println(minLen);
    }
}
