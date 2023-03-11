import java.util.ArrayList;
import java.util.Iterator;

// Реализовать алгоритм сортировки слиянием

// Пусть дан произвольный список целых чисел,удалить из него чётные числа

// Заполнить список десятью случайными числами.Отсортировать список методом sort()и вывести его на экран.

public class DellEven {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            array.add((int) (Math.random() * 100));
        System.out.printf("\nИсходный массив: %s", array);

        Iterator<Integer> col = array.iterator();
        while (col.hasNext())
            if (col.next() % 2 == 0)
                col.remove();
        System.out.printf("\nПолученный массив: %s", array);
    }
}
