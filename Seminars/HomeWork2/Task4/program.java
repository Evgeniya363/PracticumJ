// 4*.К калькулятору из предыдущего дз добавить логирование.
// Две последние со звёздочками-на усмотрение,можно не делать
package HomeWork2.Task4;

import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class calculator {
    private static Scanner scanner;
    private static Logger logger;
    static double x;
    static double y;
    static char operator;

    static void setLoggerSetting() {
        try {
            FileHandler fh = new FileHandler("log.txt");
            logger = Logger.getLogger(calculator.class.getName());
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
            // logger.setLevel(Level.WARNING);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        setLoggerSetting();
        logger.info("Простейший калькулятор\\Вход в программу выполнен");
        System.out.println("\nПростейший калькулятор\n");
        mainMenu();

        logger.info("Программа завершена");
        System.out.print("\nПрограмма завершена\n");
        scanner.close();
    }

    static void mainMenu() {
        // Метод управленим циклами ввода данных и вычислений
        String messageInputValue = "Введите операнд, используя цифры (0-9) и знак '.':";
        String messageInputOperator = "Введите операцию или команду: +, -, /, *, q - Выход";
        System.out.println(messageInputValue);
        System.out.println(messageInputOperator);
        System.out.print("\n");

        x = inputDouble(scanner, " операнд > ", messageInputValue);
        while (!inputChar(scanner, "операция > ", messageInputOperator)) {
            y = inputDouble(scanner, " операнд > ", messageInputValue);
            calculation();
            System.out.println("======== > " + Double.toString(x));
        }

    }

    static boolean isOperation(char simbol) {
        // Метод проверки допустимости введенной операции/команды
        String operations = "+-/*=q";
        return (operations.indexOf(simbol) >= 0);
    }

    static void calculation() {
        // Метод выполнения бинарных операций
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
                    System.out.println("Деление на 0, операция не может быть выполнена");
                    logger.warning("Деление на 0, операция не может быть выполнена");
                    return;
                } else {
                    x = x / y;
                }
                break;
            case '*':
                x = x * y;
                break;
            default:
                logger.warning("Неизвестная операция");
                System.out.println("Неизвестная операция. ");
                return;
        }
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
            System.out.printf("Неверный ввод. %s", messageError);
            System.out.print("\n");
            // logger.log(Level.WARNING,"" e);
            logger.warning("Неверный ввод." + messageError);
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
            operator = line.charAt(0);
            if (!isOperation(operator)) {
                throw new Exception(String
                        .format("Неверный ввод. %s", messageError));
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