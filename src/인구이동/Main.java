package 인구이동;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int L;
    static int R;

    static int[] dn = {-1, 0, 1, 0};
    static int[] dm = {0, 1, 0, -1};

    static int[][] map;
    static int[][] visited;

    static int count=0; //인구이동 횟수

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        visited = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


//        printAllDoor();

        loop();
        System.out.println(count);

    }

    static void loop(){

        boolean moved = false;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N;  j++){

                if(visited[i][j]==0 && bfs(new Position(i,j)) ){
                    moved=true;
                }
            }
        }

        if(moved==true){
            count++;
            reset();
            loop();
        }else{
            return;
        }

    }



    static boolean bfs(Position sP){

        int tSum = 0;
        Queue<Position> team = new LinkedList<>(); //같은편끼리 여기 넣는다
        Queue<Position> q = new LinkedList<>(); //bfs탐색에 필요한 큐

        q.add(sP);
        visited[sP.n][sP.m] = 1;
        team.add(sP);
        tSum += map[sP.n][sP.m];

        while (!q.isEmpty()){

            Position pos = q.poll();

            //4방향 탐색
            for(int i=0; i<4; i++){

                Position nPos = new Position(pos.n + dn[i], pos.m + dm[i]);

                if(isIn(nPos) && visited[nPos.n][nPos.m]==0 && isOpen(pos, nPos) ){
                    visited[nPos.n][nPos.m]=1;
                    q.add(nPos);

                    team.add(nPos);
                    tSum += map[nPos.n][nPos.m];
                }
            }
        }
        return sharePeople(team, tSum);
    }


    //같은팀끼리 나눠가진다
    static boolean sharePeople(Queue<Position> team, int tSum){

        int tSize = team.size();
        int nPeople = tSum/tSize;

        boolean moved = false;

        for(Position p: team){
            if(map[p.n][p.m] != nPeople){
                map[p.n][p.m] = nPeople;
                moved = true;
            }
        }
        return moved;
    }




    //이동가능한지 확인
    static boolean isOpen(Position p1, Position p2){
        int diff = Math.abs( map[p1.n][p1.m] - map[p2.n][p2.m] );

        if( L <= diff && diff <= R ) return true;
        return false;
    }


    //범위 안이냐
    static boolean isIn(Position p){
        if(p.n<1 || N<p.n || p.m<1 || N<p.m) return false;
        return true;
    }


    static void reset(){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                visited[i][j] = 0;
            }
        }
    }


    static class Position{
        int n;
        int m;

        Position(int n, int m){
            this.n = n;
            this.m = m;
        }
    }
}
