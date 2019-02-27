package 에라스토테네스의체;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        for(int i=1; i<=N; i++){
            arr[i] = i;
        }

        int count=0;

        for(int i=2; i<=N; i++){

            if(arr[i]==-1) continue;

            for(int j=1; i*j<=N; j++){
                if(arr[i*j]!=-1){
                    count++;
                    if(count==K) System.out.println(arr[i*j]);
                    arr[i*j]=-1;
                }
            }
        }

    }
}
