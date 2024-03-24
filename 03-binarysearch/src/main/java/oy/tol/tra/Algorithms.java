package oy.tol.tra;

public class Algorithms {
    private Algorithms() {
        // Empty
    }

    public static int binarySearch(int value, Integer[] array, int fromIndex, int toIndex) {

        while (fromIndex <= toIndex) {
            int mid = (toIndex + fromIndex) / 2;
            if(array[mid] == value){
                return mid;
            }else if(array[mid] < value){
                fromIndex = mid + 1;
            }else{
                toIndex = mid - 1;
            }

        }

        return -1;
    }

    public static int binarySearch(String value, String[] array, int fromIndex, int toIndex) {

        while (fromIndex <= toIndex) {
            int mid = (toIndex + fromIndex) / 2;
            int cmp = value.compareTo(array[mid]);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                toIndex = mid + 1;
            } else {
                fromIndex = mid - 1;
            }
        }

        return -1;
    }
    public static void sort(Integer[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    public static void sort(String[] array) {
        fastSort(array, 0, array.length - 1);
    }

    public static void fastSort(String[] array, int start, int end){
        if(start < end){
            int pivotIndex = partition(array, start, end);
            fastSort(array, start, pivotIndex - 1);
            fastSort(array, pivotIndex + 1, end);
        }

    }
    private static int partition(String[] array, int start, int end){
        String pivot = array[end];
        int i = start - 1;
        for(int j = start; j < end; j++){
            if(array[j].compareTo(pivot) < 0){
                i++;
                String tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        String tmp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = tmp;
        return i + 1;
    }



}
