package 이름순서바꾸기;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);


        for(int i=0; i<100; i++){
            String str = sc.nextLine();
            String[] s = str.split(" ");

            if(s.length==3){
                System.out.println( s[1] + s[2].toLowerCase() + " " + s[0]);
            }if(s.length==2){
                System.out.println(s[1] + " " + s[0]);
            }
        }
    }
}
