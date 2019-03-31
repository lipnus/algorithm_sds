package 테스트;



public class Main {

    static int[] target = {1,2,3,4,5};
    static int[] arr = new int[3];
    static int[] visited = new int[5];

    public static void main(String[] args) {
        dfs(0,0);
    }



    static void dfs(int index, int len){

        if(len==3){
            for(int a: arr){
                System.out.printf("%d ", a);
            }
            System.out.println();
            return;
        }

        for(int i=0; i<target.length; i++){
            if(visited[i]==0){
                visited[i]=1;
                arr[index]=target[i];
                dfs(index+1, len+1);
            }
        }
    }





}


