import java.util.Arrays;
import java.util.Scanner;

public class HeapSorting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Пирамидальная сортировка\n\nВведите количество эдементов: ");
        int N = scanner.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(array));
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void heapSort(int[] array) {
        int len = array.length - 1;
        for (int i = len / 2; i >= 0; i--) {
            heipify(array, i, len);
        }

        for (int i = len; i >= 0; i--) {
            int tmp = array[0];
            array[0] = array[len];
            array[len] = tmp;
            heipify(array, 0, --len);

        }

    }

    private static void heipify(int[] array, int i, int len) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int maxx;
        if (left <= len && array[left] > array[i])
            maxx = left;
        else
            maxx = i;
        if (right <= len && array[right] > array[maxx])
            maxx = right;

        if (i != maxx) {
            int tmp = array[i];
            array[i] = array[maxx];
            array[maxx] = tmp;
            heipify(array, maxx, len);
        }
    }

}
