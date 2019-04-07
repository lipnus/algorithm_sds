package 로또;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] group;
    static int[] visited;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            if(N==0) return;

            group = new int[N];
            visited = new int[N];
            for(int i=0; i<N; i++){
                group[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, new int[6], 0);
            System.out.println();
        }

    }

    static void dfs(int index, int[] arr, int target){

        if(index==arr.length){
            print(arr);
            return;
        }

        for(int i=target; i<group.length; i++){
            arr[index] = group[i];
            dfs(index+1, arr, i+1);
        }
    }


    static void print(int[] arr) {
        for (int a : arr) {
            System.out.printf("%d ", a);
        }
        System.out.println();
    }
}
