// Вычисление n-ое треугольного числа(сумма чисел от 1 до n),
// n! (произведение чисел от 1 до n)

import java.util.Scanner;
import java.util.function.BinaryOperator;

public class program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = inputInt(scanner, "\nВведите число N: ", 1, 20);

        // Для выполнения обох вычислений можно воспользоваться циклом или рекурсией
        // в случае треугольного числа это оправдано только для вывода ряда чисел
        // для обеспечения универсальности использую встроенный функциональный интерфейс
        // BinaryOperator
        BinaryOperator<Long> summ = (x, y) -> x + y;
        BinaryOperator<Long> mult = (x, y) -> x * y;

        long resalt = calculateOperation("sum[1..N]", summ, 0, n);
        System.out.printf("%d-е треугольное число: %d", n, resalt);
        System.out.println("\n");
        resalt = calculateOperation("N!", mult, 1, n);
        System.out.printf("Факториал числа %d: %d", n, resalt);

        // В случае треугольного числа расчет лучше выполнить по формуле
        System.out.printf("\n\nСумма ряда N первых натуральных чисел: %d", (n + 1) * n / 2);

        scanner.close();
    }

    static long calculateOperation(String message, BinaryOperator<Long> operator, int beginValue, int endValue) {
        // Метод рассчета суммы прогрссии, факториала
        long resalt = beginValue;
        System.out.printf("\nРяд %s: {%d[%d]", message, beginValue, 1);
        for (long i = beginValue + 1; i <= endValue; i++) {
            resalt = operator.apply(resalt, i);
            System.out.printf(", %d [%d]", resalt, i);
        }
        System.out.println("}");
        return resalt;
    }

    static int inputInt(Scanner scanner, String message, int minValue, int maxValue) {
        // метод ввода натурального числа
        int num;
        try {
            System.out.print(message);
            num = Integer.parseInt(scanner.nextLine());
            if (num < minValue || num > maxValue) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            System.out.printf("Неверный ввод. Ожидается число в диапазоне [%d, %d]\n", minValue, maxValue);
            num = inputInt(scanner, message, minValue, maxValue);
        }
        return num;
    }
}
