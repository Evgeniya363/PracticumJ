// В калькулятор добавьте возможность отменить последнюю операцию.
// Добавлены унарные операции: возведение в квадрат, смена знака

package HomeWork4.Calculator;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Calculator {
    private static Scanner scanner;
    private static Logger logger;
    private static LinkedList<Double> resalts = new LinkedList<>();

    private static double x;
    private static double y;
    private static char operator;
    private static boolean isUnary = false;

    private static boolean setLoggerSetting() {
        try {
            FileHandler fh = new FileHandler("log.txt");
            logger = Logger.getLogger("Calculator");
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        if (setLoggerSetting()) {
            String msg = "Простейший калькулятор";
            logger.info(msg);
            System.out.println(msg);
            System.out.println();
            mainMenu();
            msg = "Программа завершена";
            logger.info(msg);
            System.out.print(msg);
            System.out.println();
        } else
            logger.info("Выполнение программы прервано");
        scanner.close();
    }

    static void mainMenu() {
        // Метод управленим циклами ввода данных и вычислений
        String messageInputValue = "Введите операнд, используя цифры (0-9) и знак '.':";
        String messageInputOperator = "Введите операцию или команду: +, -, /, *, =, ^ - квадрат, ~ - смена знака, c - отмена, q - Выход";
        System.out.println(messageInputValue);
        System.out.println(messageInputOperator);
        System.out.print("\n");

        x = inputDouble(scanner, " операнд > ", messageInputValue);
        resalts.addLast(0.0);
        while (!inputChar(scanner, "операция > ", messageInputOperator)) {
            if (!isUnary) {
                y = inputDouble(scanner, " операнд > ", messageInputValue);
                calculation();
            } else {
                isUnary = false;
            }
            System.out.println("======== > " + Double.toString(x));
        }

    }

    static boolean isBynaryOperation() {
        // Метод проверки допустимости введенной операции/команды
        String operations = "+-/*";
        return (operations.indexOf(operator) >= 0);
    }

    static boolean isUnaryOperation() {
        String operations = "=c^~q";
        if (operations.indexOf(operator) >= 0) {
            isUnary = true;
            if (operator != 'c')
                resalts.addLast(x);
            switch (operator) {
                case 'c':

                    if (resalts.size() > 0)
                        x = resalts.pollLast();
                    break;
                case '~':
                    x = -x;
                    break;
                case '^':
                    x = x * x;
                    break;
            }
            logger.info("Результат вычисления: " + x);
        } else {
            isUnary = false;
        }
        return isUnary;
    }

    static void calculation() {
        // Метод выполнения бинарных операций
        double oldX = x;
        switch (operator) {
            case '=':
                x = y;
                break;
            case '+':
                x = x + y;
                break;
            case '-':
                x = x - y;
                break;
            case '/':
                if (y == 0) {
                    String msg = "Деление на 0, операция не может быть выполнена";
                    System.out.println(msg);
                    logger.warning(msg);
                    return;
                } else {
                    x = x / y;
                }
                break;
            case '*':
                x = x * y;
                break;
            default:
                String msg = "Неизвестная операция";
                logger.warning(msg);
                System.out.println(msg);
                return;
        }
        resalts.addLast(oldX);
        logger.info("Результат вычисления: " + x);
    }

    static double inputDouble(Scanner scanner, String message, String messageError) {
        // Метод ввода числа Double
        double num;
        System.out.print(message);
        try {
            String line = scanner.nextLine();
            logger.info("Введено пользователем: " + line);
            if (line.trim().isEmpty()) {
                throw new NumberFormatException(); // если не ввели ничего или пробел(ы)
            }
            num = Double.parseDouble(line);
        } catch (NumberFormatException e) {
            String msg = "Неверный ввод. ";
            System.out.println(msg + messageError);
            logger.warning(msg);
            num = inputDouble(scanner, message, messageError);
        }
        logger.info("Введен операнд: " + String.valueOf(num));
        return num;
    }

    static boolean inputChar(Scanner scanner, String message, String messageError) {
        // Метод ввода символа
        System.out.print(message);
        try {
            String line = scanner.nextLine();
            logger.info("Введено пользователем " + line);
            if (line.length() > 0)
                operator = line.charAt(0);
            else
                throw new Exception(String
                        .format("Неверный ввод\n%s", messageError));
            if (!isBynaryOperation() && !isUnaryOperation()) {
                throw new Exception(String
                        .format("Неверный ввод\n%s", messageError));
            }
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            System.out.print("\n");
            logger.warning("Неверный ввод");
            inputChar(scanner, message, messageError); // при неудаче рекурсивный вызов
        }
        logger.info("Введена операция: " + String.valueOf(operator));
        return (operator == 'q');
    }
}