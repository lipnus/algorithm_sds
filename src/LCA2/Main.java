package LCA2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N; //노드개수
    static int M; //쌍의 수
    static List<Integer>[] map;
    static boolean[] visited;
    static int[] depth;
    static int[][] parent; //parent[a][b] a의 2^b번째 조상

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new ArrayList[N+1];
        visited = new boolean[N+1];
        depth = new int[N+1];
        parent = new int[N+1][21];


        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(map[a]==null) map[a] = new ArrayList<>();
            if(map[b]==null) map[b] = new ArrayList<>();

            map[a].add(b);
            map[b].add(a);
        }

        bfs();
        fillParent();

        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println( lca(a,b) );
        }
    }

    static int lca(int a, int b){

        int low, high;

        if(depth[a] < depth[b]){ low=b; high=a; }
        else { low=a; high=b; }

        //낮은 애를 올려서 높이를 맞춰준다
        for(int i=20; 0<=i; i--){
            int diff = depth[low] - depth[high];
            if(Math.pow(2, i) <= diff){
                low = parent[low][i];
            }
        }

//        System.out.printf("low:%d(%d) high:%d(%d)",low,depth[low],high,depth[high]);

        //높이만 맞췄는데 같아져버림
        if( low == high )return high;

        //목표지점 한칸 아래까지 함께 점프
        for(int i=20; 0<=i; i--){
            if(parent[low][i] != parent[high][i]){
                low = parent[low][i];
                high = parent[high][i];
            }
        }

        //남은한칸 마저 점프
        high = parent[high][0];
        //점프 후
//        System.out.printf("[before]low:%d(%d)", low, depth[low]);

        return high;
    }

    static void fillParent(){

        //2^20 = 1,000,000
        for(int b=1; b<=20; b++){
            for(int a=1; a<=N; a++) {
                parent[a][b] = parent[ parent[a][b-1] ][ b-1 ];
            }
        }
    }


    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;
        int d=0;


        while (!q.isEmpty()){
            d++;

            int qSize = q.size();
            for(int i=0; i<qSize; i++){
                int now = q.poll();

                if(map[now]==null) continue;

                int mSize = map[now].size();
                for(int j=0; j<mSize; j++){

                    int next = map[now].get(j);
                    if(visited[next]==false){
                        parent[next][0] = now;
                        q.add(next);
                        visited[next]=true;
                        depth[next] = d;
                    }
                }
            }//for

        }
    }
}
