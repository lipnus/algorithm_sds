package 이공사팔;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int N;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int maxValue = 0;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N+1][N+1];

        //입력받기
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                map[i][j] = sc.nextInt();
            }
        }
//
//        move(0, map);
//        print(map);

        dfs(1, map);
        System.out.println(maxValue);




    }


    static void print(int[][] map){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
    }


    static void dfs(int count, int[][] map){

        //종료
        if(count==5){
            updateMax(map);
            return;
        }

        for(int i=0; i<4; i++){
            int[][] nextMap = map.clone();
            move(i, nextMap);
            dfs(count+1, nextMap);
        }
    }


    static void move(int dir, int[][] map){

        List<Integer> line;
        //상
        if(dir==0){
            for(int i=1; i<=N; i++){
                line = new ArrayList<>();

                //리스트 추출
                for(int j=1; j<=N; j++){
                    if(map[j][i]!=0) line.add(map[j][i]);
                    map[j][i]=0;
                }
                line = sumLine(line);

                //행렬로 변환
                for(int j=0; j<line.size(); j++) map[j+1][i] = line.get(j);
            }
        }//상


        //오른쪽
        if(dir==1){
            for(int i=1; i<=N; i++){
                line = new ArrayList<>();

                //리스트 추출
                for(int j=1; j<=N; j++){
                    if(map[i][j]!=0) line.add(map[i][j]);
                    map[i][j]=0;
                }
                line = sumLine(line);

                //행렬로 변환
                for(int j=0; j<line.size(); j++) map[i][N-j] = line.get(j);
            }
        }//오른쪽


        //하
        if(dir==2){
            for(int i=1; i<=N; i++){//세로 한줄
                line = new ArrayList<>();

                //리스트 추출
                for(int j=N; 1<=j; j--){
                    if(map[j][i]!=0) line.add(map[j][i]);
                    map[j][i]=0;
                }
                line = sumLine(line);

                //행렬로 변환
                for(int j=0; j<line.size(); j++) map[N-j][i] = line.get(j);
            }
        }//하


        //왼쪽
        if(dir==3){
            for(int i=1; i<=N; i++){//세로 한줄
                line = new ArrayList<>();

                //리스트 추출
                for(int j=1; j<=N; j++){
                    if(map[i][j]!=0) line.add(map[i][j]);
                    map[i][j]=0;
                }
                line = sumLine(line);

                //행렬로 변환
                for(int j=0; j<line.size(); j++) map[i][j+1] = line.get(j);
            }
        }//왼쪽
    }

    //한줄 리스트를 입력받아서 합쳐줌
    static List<Integer> sumLine(List<Integer> line){

        for(int i=0; i<line.size()-1; i++){
            if(line.get(i)==line.get(i+1)){
                line.set(i, line.get(i)*2);
                line.remove(i+1);
            }
        }

        return line;
    }


    /**
     * maxValue를 가장 큰 블록의 값으로 바꾼다
     * @param map
     * @return
     */
    static void updateMax(int[][] map){

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                maxValue = Math.max(maxValue, map[i][j]);
            }
        }
    }
}
