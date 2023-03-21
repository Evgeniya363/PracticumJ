// Подумать над структурой класса Ноутбук для магазина техники—выделить поля и методы.Реализовать в Java.
// Создать множество ноутбуков.
// Написать метод,который будет запрашивать у пользователя критерий(или критерии)фильтрации и выведет ноутбуки,
// отвечающие фильтру.Критерии фильтрации можно хранить в Map.Например:
// “Введите цифру,соответствующую необходимому критерию:
// 1-ОЗУ
// 2-Объём ЖД
// 3-Операционная система
// 4-Цвет…
// Далее нужно запросить минимальные значения для указанных критериев—сохранить параметры фильтрации можно также в Map.
// Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SearchLT {

    static Scanner scanner = new Scanner(System.in);
    static String[][] titles = { { "Brand", "brand" }, { "Model", "model" }, { "Processor", "processor" },
            { "RAM", "ramSize" }, { "Disk type", "discType" }, { "Volume", "diskSize" },
            { "Video adapter", "videoAdapter" }, { "Diagonal", "displaySize" }, { "OS", "operatingSystem" },
            { "Price", "price" } };

    public static void main(String[] args) {
        Set<Laptop> laptop = new LinkedHashSet();
        Map<String, String> criteria = new HashMap<>();
        ImportLPT.initLaptops(laptop);
        mainMenu(laptop, criteria);
        scanner.close();
    }

    static void mainMenu(Set<Laptop> laptop, Map<String, String> criteria) {
        System.out.print("\nChoose your new laptop\nEnter the search criteria");
        int mod;
        Laptop filtersLPT = new Laptop(criteria);
        do {
            mod = enterCriteria(criteria);
            if (mod == 1) {
                applyFilter(laptop, filtersLPT);
                System.out.print("Press Enter to coninue......");
                scanner.nextLine();
            } else if (mod == 12) {
                criteria.clear();
            } else if (mod > 0) {
                System.out.printf("Enter %s: ", titles[mod - 2][0]);
                criteria.put(titles[mod - 2][1], scanner.nextLine());
                filtersLPT = new Laptop(criteria);
            }
            System.out.printf("\nSearching...... %s", criteria);
        } while (mod > 0);
        System.out.println("\nProgram compleated\n");
    }

    static int enterCriteria(Map<String, String> criteteria) {
        System.out.println("\n\n1. Show results");
        int i = 1;
        for (String[] title : titles) {
            System.out.println(++i + ". " + title[0]);
        }
        System.out.println(++i + ". Clear filters");
        System.out.println("0. Exit");
        int youCase = -1;
        System.out.print("\nEnter an action: ");
        try {
            youCase = scanner.nextInt();
            if (youCase > i)
                youCase = -1;
        } catch (Exception e) {

        }
        scanner.nextLine();

        return youCase;
    }

    static void applyFilter(Set<Laptop> laptop, Laptop comparator) {
        System.out.println("\n      Brand              Model                     Processor   RAM  "
                + "Disc Size                 Video adapt         Diag           OS      Price");
        for (var lpt : laptop) {
            if (lpt.moreThan(comparator))
                System.out.println(lpt);
        }
    }
}
