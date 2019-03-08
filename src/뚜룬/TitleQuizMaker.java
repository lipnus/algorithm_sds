package 뚜룬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class TitleQuizMaker {

    static String[] title;
    static int N;

    public static void main(String[] args) throws Exception {

        Random ran = new Random();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        title = new String[1000];

        //입력받기

        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            title[i] = br.readLine();
        }


        //텍스트생성
        for(int i=0; i<N; i++){

            String[] choice = new String[3];
            choice[0] = title[i] + "\t" + title[i] + "\t" + "1";    //정답
            choice[1] = title[i] + "\t" + title[ ran.nextInt(N) ] + "\t" + "0"; //오답
            choice[2] = title[i] + "\t" + title[ ran.nextInt(N) ] + "\t" + "0"; //오답

            int a = ran.nextInt(3);
            int b = ran.nextInt(3);

            String temp = choice[a];
            choice[a] = choice[b];
            choice[b] = temp;

            //출력
            for(String str: choice){
                System.out.println(str);
            }
        }

    }

    }
