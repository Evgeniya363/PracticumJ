// Вывести все простые числа от 1 до 1000 

package HomeWork1.Task2;

import java.util.Arrays;

public class program {
    public static void main(String[] args) {

        int N = 1000;

        // lля нахождения всех простых чисел не больше заданного числа n
        // использую метод Эратосфена

        System.out.printf("Список простых чисел от 1 до %d: %s", N, eratosfen(N));
    }

    static String eratosfen(int n) {
        // Создание массива заданного размера из итинных элементов
        boolean Simpls[] = new boolean[n];
        Arrays.fill(Simpls, true);

        int j = 2;
        // В цикле убираем элементы кратные простым делителям
        while (j < n / 2) {
            if (Simpls[j])
                for (int i = j * 2; i < n; i += j)
                    Simpls[i] = false; // Отбраковка элементов кратных j

            j++;
        }
        // Формирование строки из истиных элементов массива
        StringBuilder builder = new StringBuilder();
        builder.append("[0");
        for (int i = 1; i < Simpls.length; i++) {
            if (Simpls[i]) {
                builder.append(", ");
                builder.append(i);
            }
        }
        builder.append(']');
        return builder.toString();
    }
}
