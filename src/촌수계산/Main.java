package 촌수계산;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N, start, end, E;
    static int[][] arr;
    static int[] visited;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        start = sc.nextInt();
        end = sc.nextInt();
        E = sc.nextInt();

        arr = new int[N+1][N+1];
        visited = new int[N+1];

        for(int i=0; i<E; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        System.out.println( bfs() );
    }

    static int bfs(){

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = 1;

        int count =0;

        while (!q.isEmpty()){

            count++;
            int qSize = q.size();
            for(int i=0; i<qSize; i++){
                int picked = q.poll();

                for(int j=1; j<=N; j++){
                    if( arr[picked][j]==1 && visited[j]==0 ){

                        if(j==end) return count;
                        else{
                            visited[j] = 1;
                            q.offer(j);
                        }
                    }
                }
            }
        }//while

        return -1;

    }
}
