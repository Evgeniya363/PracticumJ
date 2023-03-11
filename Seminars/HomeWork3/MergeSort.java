import java.util.ArrayList;

public class MergeSort {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        array = initList(array, 10);
        System.out.printf("\nИсходный список:      %s", array);
        array = mergeSort(array);
        System.out.printf("\nСортированный список: %s", array);
    }

    static ArrayList<Integer> initList(ArrayList<Integer> arr, int n) {
        for (int i = 0; i < n; i++)
            arr.add((int) (Math.random() * 100));
        return arr;
    }

    static ArrayList<Integer> mergeSort(ArrayList<Integer> arr) {
        if (arr.size() < 2)
            return arr;
        int half = (int) (arr.size() + 1) / 2;
        ArrayList<Integer> left = new ArrayList<>(arr.subList(0, half));
        ArrayList<Integer> right = new ArrayList<>(arr.subList(half, arr.size()));
        return mergeHalf(mergeSort(left), mergeSort(right));
    }

    static ArrayList<Integer> mergeHalf(ArrayList<Integer> a, ArrayList<Integer> b) {
        int size = a.size() + b.size();
        ArrayList<Integer> result = new ArrayList<Integer>(size);
        while (a.size() > 0 && b.size() > 0) {
            if (a.get(0) < b.get(0)) {
                result.add(a.remove(0));
            } else {
                result.add(b.remove(0));
            }
        }
        if (a.size() > 0)
            result.addAll(a);
        if (b.size() > 0)
            result.addAll(b);
        return result;
    }
}
