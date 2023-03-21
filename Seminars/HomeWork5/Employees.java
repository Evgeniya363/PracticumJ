// Пусть дан список сотрудников:
// Иван Иванов Светлана Петрова Кристина Белова Анна Мусина Анна Крутова Иван Юрин Петр Лыков Павел Чернов Петр Чернышов Мария Федорова Марина Светлова Мария Савина Мария Рыкова Марина Лугова Анна Владимирова Иван Мечников Петр Петин Иван Ежов
// Написать программу, которая найдёт и выведет повторяющиеся имена с количеством повторений. 
// Отсортировать по убыванию популярности.

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Employees {

    public static void main(String[] args) {
        ArrayList<String> employees = new ArrayList<>();
        employees = initEmployees("list.txt");
        System.out.println("\nСписок сотрудников:");
        employees.stream()
                .forEach(System.out::println);

        Map<String, Integer> allNames = new TreeMap<>();
        allNames = CalculateNames(employees);
        System.out.println("\nСписок имен:");
        allNames.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(System.out::println);
    }

    static Map<Integer, String> convertNames(Map<String, Integer> allNames) {
        Map<Integer, String> names = new HashMap<>();
        for (Entry<String, Integer> name : allNames.entrySet()) {
            names.put(name.getValue(), name.getKey());
        }
        return names;
    }

    static Map<String, Integer> CalculateNames(ArrayList<String> employees) {
        Map<String, Integer> allNames = new HashMap<>();
        for (String string : employees) {
            String currentName = string.substring(0, string.indexOf(' '));
            Integer value = allNames.get(currentName);
            if (value == null)
                allNames.put(currentName, 1);
            else
                allNames.put(currentName, value + 1);
        }
        return allNames;
    }

    static void printContacts(Map<String, String[]> contacts) {
        System.out.printf("\n%30s  %s\n", "Список контактов", "Номера телефонов");
        for (Entry<String, String[]> contact : contacts.entrySet()) {
            System.out.printf("\n%30s", contact.getKey());
            for (String telephones : contact.getValue()) {
                System.out.print("  " + telephones);
            }
        }
    }

    static ArrayList<String> initEmployees(String nameFile) {
        ArrayList<String> aList = new ArrayList<>();
        try (FileReader fr = new FileReader(nameFile)) {
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                aList.add(scan.nextLine().replace("\n", ""));
            }
            fr.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return aList;
    }
}
