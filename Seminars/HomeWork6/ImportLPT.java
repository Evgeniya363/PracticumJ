import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class ImportLPT {
    static void initLaptops(Set<Laptop> laptop) {
        ArrayList<String> aList = readFromFile("Laptops.csv");
        for (String string : aList) {
            String[] word = string.split(";");
            int word3 = 0, word5 = 0;
            float word7 = 0f, word9 = 0f;
            try {
                word3 = Integer.parseInt(word[3]);
                if (word[5].contains("Gb"))
                    word5 = Integer.parseInt(word[5].replace("Gb", ""));
                else if (word[5].contains("Tb"))
                    word5 = Integer.parseInt(word[5].replace("Tb", "")) * 1024;
                else
                    word5 = Integer.parseInt(word[5]);
                word7 = Float.parseFloat(word[7].replace(",", "."));
                word9 = Float.parseFloat(word[9]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            laptop.add(new Laptop(word[0], word[1], word[2], word3, word[4], word5, word[6], word7, word[8], word9));
        }
    }

    static ArrayList<String> readFromFile(String nameFile) {
        ArrayList<String> aList = new ArrayList<>();

        try (FileReader fr = new FileReader(nameFile)) {
            Scanner scan1 = new Scanner(fr);
            while (scan1.hasNextLine()) {
                aList.add(scan1.nextLine().replace("\n", ""));
            }
            fr.close();
            scan1.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return aList;
    }
}
