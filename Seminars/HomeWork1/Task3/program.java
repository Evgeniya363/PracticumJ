import java.util.Scanner;

public class program {
    static double x;
    static double y;
    static char operator;

    public static void main(String[] args) {
        System.out.println("\nПростейший калькулятор\n");
        Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);
        scanner.close();
    }

    static void mainMenu(Scanner scanner) {
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
        System.out.print("\nКонец программы\n");
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
                } else {
                    x = x / y;
                }
                break;
            case '*':
                x = x * y;
                break;
            default:
                System.out.println("Неизвестная операция");
        }
    }

    static double inputDouble(Scanner scanner, String message, String messageError) {
        // Метод ввода числа Double
        double num;
        System.out.print(message);
        try {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                throw new NumberFormatException(); // если не ввели ничего или пробел(ы)
            }
            num = Double.parseDouble(line);
        } catch (NumberFormatException e) {
            System.out.printf("Неверный ввод .%s", messageError);
            System.out.print("\n");
            num = inputDouble(scanner, message, messageError);
        }
        return num;
    }

    static boolean inputChar(Scanner scanner, String message, String messageError) {
        // Метод ввода символа
        System.out.print(message);
        try {
            String line = scanner.nextLine();
            operator = line.charAt(0);
            if (!isOperation(operator)) {
                throw new Exception(String
                        .format("Неверный ввод. %s", messageError));
            }
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            System.out.print("\n");
            inputChar(scanner, message, messageError); // при неудаче рекурсивный вызов
        }
        return (operator == 'q');
    }
}