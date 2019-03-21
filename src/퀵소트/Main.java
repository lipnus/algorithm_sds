package 퀵소트;

public class Main {
    public static void main(String[] args){
        int arr[] = {3,9,4,7,5,5,0,1,6,8,2};
        print(arr);
        System.out.println();
        quickSort(arr);
        print(arr);
    }



    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }


    public static void quickSort(int[] arr, int start, int end){
        int part2 = partition(arr, start, end);

        if(start < part2-1) quickSort(arr, start, part2-1);
        if(part2 < end) quickSort(arr, part2, end);
    }


    public static int partition(int[] arr, int start, int end){
        int pivot = arr[(start+end)/2]; //피벗은 위치 상 가운데 값

        while(start<=end){
            while(arr[start]<pivot) start++;
            while(pivot<arr[end]) end--;

            if(start<=end){
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        return start;
    }


    static void swap(int[] arr, int start, int end){
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }


    static void print(int[] arr){
        for(int a: arr){
            System.out.printf("%d ", a);
        }
    }

}
