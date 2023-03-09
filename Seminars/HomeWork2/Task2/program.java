// 2.Реализуйте алгоритм сортировки пузырьком числового массива,
// результат после каждой итерации запишите в лог-файл.
package HomeWork2.Task2.program;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BubbleSorting {
    private static Logger logger = null;

    static {
        Logger mainLogger = Logger.getLogger("HomeWork2.Task2");
        mainLogger.setUseParentHandlers(false);
        // ConsoleHandler fh = new ConsoleHandler();
        FileHandler fh = null;
        try {
            fh = new FileHandler("log.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        fh.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format,
                        new Date(lr.getMillis()),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage());
            }
        });
        mainLogger.addHandler(fh);
        logger = Logger.getLogger(BubbleSorting.class.getName());
    }

    public static void main(String[] args) throws IOException {
        logger.log(Level.INFO, "Сортировка массива методом пузырька");
        int N = 10;
        int maxValue = 100;
        int[] array = new int[N];
        array = initArray(array, maxValue);
        array = bubbleSorting(array);
        logger.warning("Обработка массива завершена");
    }

    public static int[] initArray(int[] array, int maxValue) {
        logger.log(Level.WARNING, "Создание массива");
        for (int i = 0; i < array.length; i++) {
            array[i] = getRandomInt(100);
        }
        String msg = String.format("Массив создан. Количество элементов: %d\n%s", array.length, Arrays.toString(array));
        logger.info(msg);
        System.out.println(msg);
        return array;
    }

    public static int getRandomInt(int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue);
    }

    public static int[] bubbleSorting(int[] array) {
        logger.log(Level.WARNING, "Начало сортировки массива");

        StringBuilder cycleMsg = new StringBuilder("Внешний цикл: ");
        StringBuilder changeMsg = new StringBuilder("Меняются местами элементы: ");
        int lengthC = cycleMsg.length();
        int lengthM = changeMsg.length();

        for (int i = 0; i < array.length; i++) {
            cycleMsg.delete(lengthC, cycleMsg.length()).append(i);
            logger.info(cycleMsg.toString());

            for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    changeMsg.delete(lengthM, changeMsg.length()).append(array[j - 1]);
                    changeMsg.append("[").append(j - 1).append("]").toString();
                    changeMsg.append(" <-> ").append(array[j]);
                    logger.info(changeMsg.append("[").append(j).append("]").toString());

                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        cycleMsg.replace(0, changeMsg.length(), "Отсортированный массив\n").append(Arrays.toString(array));
        logger.info(changeMsg.toString());
        System.out.println(cycleMsg.toString());
        return array;
    }
}