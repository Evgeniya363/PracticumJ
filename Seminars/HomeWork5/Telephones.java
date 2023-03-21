
// Реализуйте структуру телефонной книги с помощью HashMap, 
// учитывая, что 1 человек может иметь несколько телефонов.
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.text.MaskFormatter;
import java.util.Scanner;
import java.util.TreeMap;

public class Telephones {

    public static void main(String[] args) {
        HashMap<String, String[]> contacts = new HashMap<>();
        initContacts(contacts);
        Map<String, String[]> sortedContacts = new TreeMap<>(contacts);
        printContacts(sortedContacts);
    }

    static void initContacts(HashMap<String, String[]> contacts) {
        ArrayList<String> aList = readFromFile("list.txt");
        for (String string : aList) {
            if (string.length() > 0) {
                if (!contacts.containsKey(string))
                    contacts.put(string, randomPhones());
            }
        }
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

    static String[] randomPhones() {
        int size = (int) (Math.random() * 5);
        String[] telephones;
        telephones = new String[size];
        for (int i = 0; i < size; i++) {
            Long phoneNum = (long) (Math.random() * 100000000) + 900000000L;
            String phoneFormat = "(###)###-####";

            try {
                MaskFormatter maskFormatter = new MaskFormatter(phoneFormat);
                maskFormatter.setValueContainsLiteralCharacters(false);
                telephones[i] = maskFormatter.valueToString(phoneNum);
            } catch (Exception e) {
                telephones[i] = phoneNum.toString(size);
            }
        }
        return telephones;
    }

    static ArrayList<String> readFromFile(String nameFile) {

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
