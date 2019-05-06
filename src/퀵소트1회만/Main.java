package 퀵소트1회만;

public class Main {

    static int[] arr = {1,8,7,4,  5,  2,6,3,9};

    public static void main(String[] args){
        partition(arr, 0, arr.length-1);
    }



    private static void partition(int[] arr, int start, int end){

        int pivot = arr[(start+end)/2];
        System.out.printf("pivot:%d\n", pivot);

        while(start<=end){
            while (arr[start] < pivot) start++;
            while (pivot < arr[end]) end--;

            if(start<=end){
                swap(arr, start, end);
                start++;
                end--;
            }
        }

        System.out.printf("왼쪽: ");
        for(int i=0; i<start; i++){
            System.out.printf("%d ", arr[i]);
        }

        System.out.println();

        System.out.printf("오른쪽: ");
        for(int i=start; i<arr.length; i++){
            System.out.printf("%d ", arr[i]);
        }



    }

    private static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
