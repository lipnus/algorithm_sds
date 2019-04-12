package 보급로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int T;
    static int N;
    static int[][] map;
    static int[][] visited;
    static int[][] D; //원점에서부터의 최솟값

    static int[] dn = {-1, 0, 1, 0};
    static int[] dm = {0, 1, 0, -1};

    public static void main(String[] args)throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N+1][N+1];
            initD();
            visited = new int[N+1][N+1];

            //입력받기
            for(int i=1; i<=N; i++){
                String line = br.readLine();
                for(int j=1; j<=N; j++){
                    map[i][j] = line.charAt(j-1) - '0';
                }
            }

            bfs();
            System.out.println("#"+t + " " + D[N][N]);
        }
    }


    static boolean isIn(int n, int m){
        if(n<1 || N<n || m<1 || N<m) return false;
        return true;
    }


    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.add( new Node(1,1,0) );

        while (!q.isEmpty()){

            Node now = q.poll();
            if(now.cost < D[now.n][now.m] ) D[now.n][now.m]=now.cost;
            else continue;

            for(int i=0; i<4; i++){
                int nN = now.n+dn[i];
                int nM = now.m+dm[i];

                if(isIn(nN, nM) && visited[nN][nM]==0){
                    visited[nN][nM]=1;
                    q.add( new Node(nN, nM, D[now.n][now.m] + map[nN][nM]));
                }
            }
        }
    }


    static void initD(){
        D = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                D[i][j] = Integer.MAX_VALUE;
            }
        }
    }


    static class Node implements Comparable<Node>{
        int cost; //출발점에서부터의 비용의 합
        int n;
        int m;

        Node(int n, int m, int cost){
            this.n = n;
            this.m = m;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cost < o.cost) return -1;
            return 1;
        }
    }
}
