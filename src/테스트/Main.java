package 테스트;


public class Main {

    public static void main(String[] args) {

        Test t1 = new Test(4);
        funA(t1);
        System.out.printf("%d\n",t1.a);


        Test t2 = new Test(4);
        funA(t2);
        System.out.printf("%d\n",t2.a);

    }



    static void funA(Test t){
        t.a = 5;
    }

    static void funB(Test t){
        t = new Test(5);
    }


    static class Test{
        int a;
        Test(int a){
            this.a = a;
        }
    }
}