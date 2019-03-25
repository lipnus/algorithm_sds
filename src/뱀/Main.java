package 뱀;

import javax.swing.text.Position;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static int K; //사과의 개수
    static int L; //이동명령어
    static int[][] map;
    static Queue<Position> body = new LinkedList<>();
    static int dir = 1;

    static int count = 0;

    //뱀의 현재위치
    static Position p;

    static int[] dn = {-1, 0, 1, 0};
    static int[] dm = {0, 1 ,0, -1};

    static String[] rotation = new String[10001]; //회전정보를 담고있음

    static boolean isFinish = false;

    public static void main(String[] args){

        Scanner sc= new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N+1][N+1];
        K = sc.nextInt();


        //사과배치
        for(int i=0; i<K; i++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            map[n][m] = 2;
        }

        int L = sc.nextInt();

        p = new Position(1,1); //뱀 대가리 위치
        body.add(new Position(1,1)); //뱀 전체를 담고있는 큐


        //이동명령 저
        for(int i=0; i<L; i++){
            int X = sc.nextInt();
            String C = sc.next();
            rotation[X] = C;
        }

        moviing();
        System.out.println(count);

    }

    //이동 명령을 수행
    static void moviing(){

        while (true){

            count++;
            Position nextP = new Position(p.n + dn[dir], p.m+dm[dir]);


            if(isWall(nextP) || isSelfEat(nextP)){ //뒤짐

                isFinish=true;
                return;

            }else if(isApple(nextP)){ //사과냠냠

                body.add(nextP);
                map[nextP.n][nextP.m] = 1; //대가리칸 확보
                p.move(nextP);

            }else{ //빈땅이동

                body.add(nextP);
                map[nextP.n][nextP.m] = 1; //대가리칸 확보

                Position tail = body.poll();
                map[tail.n][tail.m] = 0; //꼬리칸 제거

                p.move(nextP);
            }

            //회전스
            if(rotation[count]!=null){
               dir = rotate(dir, rotation[count]);
            }

        }//while

    }




    //사과가 있는지 없는지
    static boolean isApple(Position pos){
        if(map[pos.n][pos.m]==2) return true;
        return false;
    }


    //대가리가 벽에 부딪혔는지
    static boolean isWall(Position pos){
        if(1<= pos.n && pos.n <= N && 1 <=pos.m && pos.m <= N) return false;
        return true;
    }


    //지 몸을 먹었는지
    static boolean isSelfEat(Position pos){
        if(map[pos.n][pos.m]==1){
            return true;
        }
        return false;
    }


    /**
     *
     * @param now 현재방향[상,우,하,좌] - (0,1,2,3)
     * @param dir 회전방향(L,D)
     */
    static int rotate(int now, String dir){

        if(now==0){
            if (dir.equals("L")) return 3;
            else if(dir.equals("D")) return 1;
        }

        if(now==1){
            if (dir.equals("L")) return 0;
            else if(dir.equals("D")) return 2;
        }

        if(now==2){
            if (dir.equals("L")) return 1;
            else if(dir.equals("D")) return 3;
        }

        if(now==3){
            if (dir.equals("L")) return 2;
            else if(dir.equals("D")) return 0;
        }

        return -1;
    }


    static class Position{
        int n;
        int m;

        Position(int n, int m){
            this.n = n;
            this.m = m;
        }

        void move(Position nextP){
            this.n = nextP.n;
            this.m = nextP.m;
        }
    }
}
