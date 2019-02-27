package LCA1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N; //노드개수
    static int M; //공통조상을 알고싶은 쌍의 수
    static List<Integer>[] map;

    static boolean[] visited;
    static int[] depth;
    static int[] parent;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new ArrayList[N+1];
        visited = new boolean[N+1];
        parent = new int[N+1];
        depth = new int[N+1];

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
//        print();

        M = Integer.parseInt(br.readLine());
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.printf("%d\n", lca(a,b) );
        }
    }

    static int lca(int a, int b){

        int low,high;

        //높이맞추기
        if(depth[a] >= depth[b]){
            low=a; high=b;
        }else{
            low=b; high=a;
        }

//        System.out.printf("a:%d b:%d low:%d(%d) high:%d(%d)\n", a,b,low,depth[low], high, depth[high]);

        int count = depth[low]-depth[high];
        for(int i=0; i<count; i++){
            low = parent[low]; //상승!
        }

//        System.out.printf("[높이맞춘뒤] low:%d(d:%d) high:%d(d:%d)\n", low, depth[low], high, depth[high]);

        //높이만 맞췄는데 같아져버림
        if(low==high) return high;


        //같이 올라가자
        while(low!=high){
            low = parent[low];
            high = parent[high];
        }

        return high;
    }


    static void print() {
        for (int i=1; i<=N; i++ ){
//            System.out.printf("pareint[%d] = %d\n", i, parent[i]);
            System.out.printf("depth[%d] = %d\n", i, depth[i]);
        }
    }




    static void bfs(){

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1]=true;

        int d=0;

        while (!q.isEmpty()){

            int qSize = q.size();
            for(int i=0; i<qSize; i++){

                int now = q.poll();
                depth[now] = d;

                if(map[now]!=null){
                    for(int next: map[now]){

                        if(visited[next]==false){
                            parent[next] = now;
                            visited[next]=true;
                            q.add(next);
                        }
                    }
                }
            }

            d++;
        }

    }
}