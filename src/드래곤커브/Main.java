package 드래곤커브;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map = new int[101][101];

    static List<Integer> D = new ArrayList<>();

    static int[] nextDir = {1,2,3,0};

    static int[] nextN = {0, -1, 0, 1};
    static int[] nextM = {1, 0, -1, 0};

    static Position pos;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            pos = new Position(n, m);
            startDraw(d,g);
        }

        System.out.println(countSquare());
    }


    static void startDraw(int dir, int g){

        //0세대
        map[pos.n][pos.m] = 1; //시작지점 찍음
        drawLine(dir);

        //남은 세대 그리기
        for(int i=1; i<=g; i++){
            drawGen(i);
        }
    }




    //과거를 회상하며 '한 세대'의 커브를 그린다
    static void drawGen(int g){

        int count = (int)Math.pow(2,g-1); //각 세대별 그려야 하는 선의 수
        int dIdx = D.size()-1; //D의 후장부터 턴다

        for(int i=dIdx; dIdx-count+1<=i; i--){

            //그리기
            int nowDir = nextDir[D.get(i)];
            drawLine(nowDir);
        }
    }



    static void drawLine(int dir){
        D.add(dir);
        pos.n = pos.n + nextN[dir];
        pos.m = pos.m + nextM[dir];
        map[pos.n][pos.m] = 1;
    }



    static int countSquare(){

        int count = 0;
        for(int i=0; i<=99; i++){
            for(int j=0; j<=99; j++){
                if(map[i][j]==1 && map[i][j+1]==1 && map[i+1][j]==1 && map[i+1][j+1]==1) count++;
            }
        }
        return count;
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
