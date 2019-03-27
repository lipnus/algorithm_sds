import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        //N개의 정수를 배열로 받음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt( st.nextToken() );

        MyStack stack = new MyStack();

        for(int i=0; i<N; i++) {
            stack.setCommand( new StringTokenizer(br.readLine()) );
        }

    }


    public static class MyStack{

        int top=0;
        int[] arr = new int[10001];

        public void setCommand(StringTokenizer st) {
            String command = st.nextToken();

            if(command.equals("push")) {
                push(Integer.parseInt(st.nextToken()));
            }else if(command.equals("pop")) {
                System.out.println(pop());
            }else if(command.equals("size")) {
                System.out.println(size());
            }else if(command.equals("empty")){
                System.out.println(empty());
            }else if(command.equals("top")) {
                System.out.println(top());
            }
        }

        public void push(int num){
            arr[top++] = num;
        }

        public int pop() {
            if(top==0) return -1;
            return arr[--top];
        }

        public int size() {
            return top;
        }

        public int empty() {
            if(top==0) return 1;
            return 0;
        }

        public int top() {
            if(top==0) return -1;
            return arr[top-1];
        }
    }
}