package 뚜룬;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChoiceSqlMaker {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;


        while( (line = br.readLine()) !=null ){

            //퀴즈넣기
            String[] voca = line.split("\t");
            System.out.printf("INSERT INTO quiz (music_pk, writer) SELECT pk, writer FROM music WHERE music.title='%s';\n", voca[0]);
            System.out.println("SELECT @quiz_pk := LAST_INSERT_ID();");

            //선택지
            System.out.printf("INSERT INTO choice (quiz_pk, choice_text, truth) VALUES (@quiz_pk, '%s', %s);\n", voca[1], voca[2]);

            voca = br.readLine().split("\t");
            System.out.printf("INSERT INTO choice (quiz_pk, choice_text, truth) VALUES (@quiz_pk, '%s', %s);\n", voca[1], voca[2]);

            voca = br.readLine().split("\t");
            System.out.printf("INSERT INTO choice (quiz_pk, choice_text, truth) VALUES (@quiz_pk, '%s', %s);\n", voca[1], voca[2]);
        }
    }
}
