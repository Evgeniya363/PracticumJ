
// На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen8 {
    static int v = 0;

    public static void main(String[] args) {
        List<Integer> set = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            newCallCycle(0, i, arr, set);
        }
    }

    private static void newCallCycle(int row, Integer col, List<Integer> arr, List<Integer> set) {
        var set2 = new ArrayList(set);
        var arr2 = new ArrayList(arr);
        arr2.add(col);
        set2.remove(col);
        if (arr2.size() > 7)
            printChess(arr2);
        else
            cycle(row + 1, 0, set2, arr2);
    }

    static void cycle(int row, int column, List<Integer> set, List<Integer> arr) {
        for (var col : set) {
            if (isCooll(row, col, arr))
                newCallCycle(row, col, arr, set);
        }
    }

    static boolean isCooll(int row, int col, List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            int x = Math.abs(row - i);
            var s = arr.get(i);
            int y = Math.abs(col - arr.get(i));
            if (x == 0 || y == 0 || x == y)
                return false;
        }
        return true;
    }

    private static void printChess(ArrayList<Integer> arr) {
        System.out.println(++v + ". Вариант расстановки");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char ch = (arr.get(i) == j) ? '$' : '_';
                System.out.print(ch + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
