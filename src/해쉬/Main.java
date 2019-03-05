package 해쉬;

import java.util.Scanner;

public class Main {

    static int MAX_TABLE = 4096;

    public static void main(String[] args) throws Exception{

        Scanner sc  = new Scanner(System.in);
        int T = sc.nextInt();
        for(int test_case = 0; test_case<T; test_case++){

            HashTable hashTable = new HashTable(MAX_TABLE);
            int N = sc.nextInt();

            for(int i=0; i<N; i++){

                String key = sc.next();
                String data = sc.next();
                hashTable.add(key, data);
            }
            System.out.printf("#%d\n", test_case);

            int Q = sc.nextInt();
            for(int i=0; i<Q; i++){
                String key = sc.next();
                String findedData = hashTable.find(key);

                if(findedData!=null) System.out.printf("%s\n", findedData);
                else System.out.println("not find\n");

            }
        }
        sc.close();

    }
}
