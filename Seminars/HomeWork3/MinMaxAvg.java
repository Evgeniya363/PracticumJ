
// Задан целочисленный список ArrayList.Найти минимальное,максимальное и среднее из этого списка.

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class MinMaxAvg {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        array = initList(array, 100);
        System.out.printf("\nИсходный массив: %s", array);

        System.out.printf("\nМинимальный элемент: %d", Collections.min(array));
        System.out.printf("\nМаксимальный элемент: %d", Collections.max(array));
        System.out.printf("\nСреднее арифметическое: %f", overage(array));
    }

    static ArrayList<Integer> initList(ArrayList<Integer> arr, int n) {
        for (int i = 0; i < n; i++)
            arr.add((int) (Math.random() * 100));
        return arr;
    }

    static double overage(ArrayList<Integer> arr) {
        int summ = 0, count = 0;
        for (Integer integer : arr) {
            summ += integer;
            count++;
        }
        return (double) summ / ((count == 0) ? 1 : count);

    }

}
