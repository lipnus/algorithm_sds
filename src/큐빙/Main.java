package 큐빙;

import java.util.Scanner;

public class Main {

    static Box[][][] cube = new Box[3][3][3];

    public static void main(String[] args){


        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++){
            int N = sc.nextInt();
            initCube();

            //명령 입력받기
            for(int i=0; i<N ;i++){
                String s = sc.next();
                String which = s.substring(0,1);
                String dir = s.substring(1, 2);
                order(which, dir);
            }

            printTop();

        }

    }


    static void printTop(){
        System.out.println(cube[0][0][0].plnne[0] +  cube[0][0][1].plnne[0] + cube[0][0][2].plnne[0]);
        System.out.println(cube[0][1][0].plnne[0] +  cube[0][1][1].plnne[0] + cube[0][1][2].plnne[0]);
        System.out.println(cube[0][2][0].plnne[0] +  cube[0][2][1].plnne[0] + cube[0][2][2].plnne[0]);
    }

    static void printTop(int i){
        System.out.println(cube[i][0][0].plnne[0] +  cube[i][0][1].plnne[0] + cube[i][0][2].plnne[0]);
        System.out.println(cube[i][1][0].plnne[0] +  cube[i][1][1].plnne[0] + cube[i][1][2].plnne[0]);
        System.out.println(cube[i][2][0].plnne[0] +  cube[i][2][1].plnne[0] + cube[i][2][2].plnne[0]);
    }

    //3번 돌리면 반시계
    static void order(String which, String dir){

        if(which.equals("U")){
            if(dir.equals("+")) rotateZ(0);
            else for(int i=0; i<3; i++) rotateZ(0);
        }

        if(which.equals("D")){
            if(dir.equals("+")) for(int i=0; i<3; i++) rotateZ(2);
            else rotateZ(2);
        }

        if(which.equals("F")){
            if(dir.equals("+")) rotateX(2);
            else for(int i=0; i<3; i++) rotateX(2);
        }

        if(which.equals("B")){
            if(dir.equals("+")) for(int i=0; i<3; i++) rotateX(0);
            else rotateX(0);
        }

        if(which.equals("L")){
            if(dir.equals("+")) rotateY(0);
            else for(int i=0; i<3; i++) rotateY(0);
        }

        if(which.equals("R")){
            if(dir.equals("+")) for(int i=0; i<3; i++) rotateY(2);
            else rotateY(2);
        }
    }

    static void rotateX(int level){

        Box[] circle = new Box[8];
        circle[0] = cube[0][level][0];
        circle[1] = cube[0][level][1];
        circle[2] = cube[0][level][2];
        circle[3] = cube[1][level][2];
        circle[4] = cube[2][level][2];
        circle[5] = cube[2][level][1];
        circle[6] = cube[2][level][0];
        circle[7] = cube[1][level][0];

        for(int count=0; count<2; count++){

            Box temp = circle[0];
            for(int i=7; 1<=i; i--){
                circle[(i+1)%8] = circle[i]; //위치이동
            }
            circle[1] = temp;
        }

        //내부의 박스도 회전
        for(int i=0; i<8; i++){
            circle[i].rotX();
        }

        cube[0][level][0] = circle[0];
        cube[0][level][1] = circle[1];
        cube[0][level][2] = circle[2];
        cube[1][level][2] = circle[3];
        cube[2][level][2] = circle[4];
        cube[2][level][1] = circle[5];
        cube[2][level][0] = circle[6];
        cube[1][level][0] = circle[7];
    }

    static void rotateZ(int level){

        Box[] circle = new Box[8];
        circle[0] = cube[level][0][0];
        circle[1] = cube[level][0][1];
        circle[2] = cube[level][0][2];
        circle[3] = cube[level][1][2];
        circle[4] = cube[level][2][2];
        circle[5] = cube[level][2][1];
        circle[6] = cube[level][2][0];
        circle[7] = cube[level][1][0];


        for(int count=0; count<2; count++){

            Box temp = circle[0];
            for(int i=7; 1<=i; i--){
                circle[(i+1)%8] = circle[i]; //위치이동
            }
            circle[1] = temp;
        }

        //내부의 박스도 회전
        for(int i=0; i<8; i++){
            circle[i].rotZ();
        }

        cube[level][0][0] = circle[0];
        cube[level][0][1] = circle[1];
        cube[level][0][2] = circle[2];
        cube[level][1][2] = circle[3];
        cube[level][2][2] = circle[4];
        cube[level][2][1] = circle[5];
        cube[level][2][0] = circle[6];
        cube[level][1][0] = circle[7];
    }

    static void rotateY(int level){

        Box[] circle = new Box[8];
        circle[0] = cube[0][0][level];
        circle[1] = cube[0][1][level];
        circle[2] = cube[0][2][level];
        circle[3] = cube[1][2][level];
        circle[4] = cube[2][2][level];
        circle[5] = cube[2][1][level];
        circle[6] = cube[2][0][level];
        circle[7] = cube[1][0][level];


        for(int count=0; count<2; count++){

            Box temp = circle[0];
            for(int i=7; 1<=i; i--){
                circle[(i+1)%8] = circle[i]; //위치이동
            }
            circle[1] = temp;
        }

        //내부의 박스도 회전
        for(int i=0; i<8; i++){
            circle[i].rotY();
        }

        cube[0][0][level] = circle[0];
        cube[0][1][level] = circle[1];
        cube[0][2][level] = circle[2];
        cube[1][2][level] = circle[3];
        cube[2][2][level] = circle[4];
        cube[2][1][level] = circle[5];
        cube[2][0][level] = circle[6];
        cube[1][0][level] = circle[7];
    }

    static void initCube(){

        //뚜껑
        cube[0][0][0] = new Box("w", "_", "_", "o", "g", "_");
        cube[0][0][1] = new Box("w", "_", "_", "o", "_", "_");
        cube[0][0][2] = new Box("w", "_", "_", "o", "_", "b");

        cube[0][1][0] = new Box("w", "_", "_", "_", "g", "_");
        cube[0][1][1] = new Box("w", "_", "_", "_", "_", "_");
        cube[0][1][2] = new Box("w", "_", "_", "_", "_", "b");

        cube[0][2][0] = new Box("w", "r", "_", "_", "g", "_");
        cube[0][2][1] = new Box("w", "r", "_", "_", "_", "_");
        cube[0][2][2] = new Box("w", "r", "_", "_", "_", "b");


        //중간
        cube[1][0][0] = new Box("_", "_", "_", "o", "g", "_");
        cube[1][0][1] = new Box("_", "_", "_", "o", "_", "_");
        cube[1][0][2] = new Box("_", "_", "_", "o", "_", "b");

        cube[1][1][0] = new Box("_", "_", "_", "_", "g", "_");
        cube[1][1][1] = new Box("_", "_", "_", "_", "_", "_");
        cube[1][1][2] = new Box("_", "_", "_", "_", "_", "b");

        cube[1][2][0] = new Box("_", "r", "_", "_", "g", "_");
        cube[1][2][1] = new Box("_", "r", "_", "_", "_", "_");
        cube[1][2][2] = new Box("_", "r", "_", "_", "_", "b");

        //바닥
        cube[2][0][0] = new Box("_", "_", "y", "o", "g", "_");
        cube[2][0][1] = new Box("_", "_", "y", "o", "_", "_");
        cube[2][0][2] = new Box("_", "_", "y", "o", "_", "b");

        cube[2][1][0] = new Box("_", "_", "y", "_", "g", "_");
        cube[2][1][1] = new Box("_", "_", "y", "_", "_", "_");
        cube[2][1][2] = new Box("_", "_", "y", "_", "_", "b");

        cube[2][2][0] = new Box("_", "r", "y", "_", "g", "_");
        cube[2][2][1] = new Box("_", "r", "y", "_", "_", "_");
        cube[2][2][2] = new Box("_", "r", "y", "_", "_", "b");

    }

    static void printBox(Box b){

        for(String p: b.plnne){
            System.out.printf("%s ", p);
        }
        System.out.println();

    }





    static class Box{
        String[] plnne = new String[6]; //작은 직육면체의 면 정보

        Box(String p0, String p1, String p2, String p3, String p4, String p5){
            plnne[0]=p0; plnne[1]=p1; plnne[2]=p2; plnne[3]=p3; plnne[4]=p4; plnne[5]=p5;
        }

        //위에서 시계방향
        void rotZ(){
            String temp = plnne[3];
            plnne[3] = plnne[4];
            plnne[4] = plnne[1];
            plnne[1] = plnne[5];
            plnne[5] = temp;
        }

        //왼쪽에서 시계방향
        void rotY(){
            String temp = plnne[0];
            plnne[0] = plnne[3];
            plnne[3] = plnne[2];
            plnne[2] = plnne[1];
            plnne[1] = temp;
        }

        //정면에서 시계방향
        void rotX(){
            String temp = plnne[0];
            plnne[0] = plnne[4];
            plnne[4] = plnne[2];
            plnne[2] = plnne[5];
            plnne[5] = temp;
        }
    }


}
