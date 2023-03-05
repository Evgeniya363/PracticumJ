// *+Задано уравнение вида q + w = e, q, w, e >= 0. 
// Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69. 
// Требуется восстановить выражение до верного равенства. 
// Предложить хотя бы одно решение или сообщить, что его нет.

import java.util.Scanner;

public class program {
    // Предлагаю хотя бы одно хромое решение
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nРешение уравнения вида q + w = e, q, w, e >= 0");
        System.out.println("Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69\n");

        char[] letters = { 'q', 'w', 'e' };
        String[] qwe = new String[3]; // { "12?", "?25", "7?6" };

        int maxlen = inputData(scanner, letters, qwe);
        System.out.println(String.format("%s - %s = %s", qwe[0], qwe[1], qwe[2]));

        int[][] tempResalt = analisStrinds(qwe, maxlen);
        System.out.println(findSolutions(tempResalt));
        scanner.close();
    }

    static int inputData(Scanner scanner, char[] letters, String[] qwe) {
        int maxlen = 0;
        for (int i = 0; i < letters.length; i++) {
            String input = inputStr(scanner, letters[i] + " = ", 10, 99);
            qwe[i] = input;
            int len = input.length();
            if (maxlen < len)
                maxlen = len;
        }
        return maxlen;
    }

    static int[][] analisStrinds(String[] qwe, int len) {
        // Разбор строки на запчасти
        char[] digits[] = new char[3][len];
        int[] resalt[] = new int[len][5];
        int lenQwe;
        for (int i = 0; i < qwe.length; i++) {
            lenQwe = qwe[i].length();

            for (int j = 0; j < lenQwe; j++) {

                int index = len - lenQwe + j;
                char ch = qwe[i].charAt(j);
                digits[i][index] = ch;

                if (ch == '?') {
                    resalt[index][i]++;
                    resalt[index][4]++;
                } else {
                    Integer digit = Integer.parseInt(String.valueOf(ch));
                    if (i < 2)
                        resalt[index][3] += digit;
                    else
                        resalt[index][3] -= digit;
                }
            }

        }
        return resalt;
    }

    static String findSolutions(int[][] digit) {
        // Метод поиска хотя бы одного решения
        StringBuilder builder = new StringBuilder();
        builder.append("Решение от младших разрядов к старшим: ");
        int isOwer = 0; // Возможный перенос
        for (int i = digit.length - 1; i >= 0; i--) {
            int s = digit[i][3];
            int q = digit[i][0];
            int w = digit[i][1];
            int e = digit[i][2];
            int c = digit[i][4];

            s += +isOwer;
            if (s >= 10) {
                s -= 10;
                isOwer = 1;
            } else {
                isOwer = 0;
            }

            if (s < 0) {
                if (q > 0)
                    builder.append(String.format("%d-q  ", -s));
                else if (w > 0)
                    builder.append(String.format("%d-w ", -s));
                else if (s == -1) // Возможен перенос
                    isOwer = 1;
                else
                    return builder.append("Левая часть: решения нет в разряде " + (digit.length - i)).toString();

            } else if (s > 0)
                if (e > 0)
                    builder.append(String.format("%d-e ", s));
                else if (q + w > 0) // Возможен пренос
                    if (q + w > 1)
                        builder.append("Множество решений в разряде " + (digit.length - i));
                    else {
                        if (q > 0)
                            builder.append(String.format("%d-q ", 10 - s));
                        else if (w > 0)
                            builder.append(String.format("%d-w ", 10 - s));
                        isOwer = 1;
                    }
                else
                    return builder.append("Правая часть: решения нет в разряде " + (digit.length - i)).toString();
            else if (c > 1)
                builder.append("Множество решений в разряде " + (digit.length - i));
            else // Возможен перенос
                isOwer = isOwer;
        }
        if (isOwer == 1)
            builder.append("Тривиальных решений не найдено");
        return builder.toString();
    }

    static String inputStr(Scanner scanner, String message, int minValue, int maxValue) {
        // метод ввода макета двухзначного числа
        String line;
        try {
            System.out.print(message);
            line = scanner.nextLine();
            if (line.trim().isEmpty() || !isNumeric(line)) {
                throw new NumberFormatException(); // если не ввели ничего или пробел(ы)
            }

        } catch (NumberFormatException ex) {
            System.out.printf("Неверный ввод. Ожидается число, нопример '71' или '?1' \n", minValue, maxValue);
            line = inputStr(scanner, message, minValue, maxValue);
        }
        return line;
    }

    static boolean isDigit(char simbol) {
        // Метод проверки допустимости введенной операции/команды
        String operations = "0123456789?";
        return (operations.indexOf(simbol) >= 0);
    }

    static boolean isNumeric(String str) {
        // Метод проверки допустимости введенной операции/команды
        for (int i = 0; i < str.length(); i++)
            if (!isDigit(str.charAt(i)))
                return false;
        return true;
    }
}
