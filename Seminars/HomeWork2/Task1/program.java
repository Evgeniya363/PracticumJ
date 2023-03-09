//     1. Дана строка sql-запроса"select * from students where ".
//     Сформируйте часть WHERE этого запроса, используя StringBuilder.
//     Данные для фильтрации приведены ниже в виде json строки.
//     Если значение null, то параметр не должен попадать в запрос.
//     Параметры для фильтрации:
//     {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}

package HomeWork2.Task1;

import java.util.Arrays;

public class program {
    // Программа формирования предложения WHERE оператора SELECT
    // согласно параметров фильтрации, заданных в строке
    public static void main(String[] args) {
        String stringSelect = "select * from students where ";
        String stringValues = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";
        System.out.println(bulldWhere(stringSelect, stringValues));
    }

    static String bulldWhere(String stringSelect, String stringValues) {
        // Метод преобразования массива из строки с данными для фильтрации
        // в предложение WHERE

        StringBuilder builder = new StringBuilder(stringSelect);
        // Формирование массива данных и спецсимволов, разделенных "
        String[] words = stringValues.split("\"");

        // Выделение пар данных <перемнная>:<значение> из блоков по 4 элемента массива
        if ((words.length - 1) / 4 > 0)
            for (int i = 1; i < words.length; i += 4)
                if (!words[i + 2].equals("null")) {
                    // Формирование предложения WHERE
                    if (i > 1)
                        builder.append(" and ");
                    builder.append(words[i]).append(" = \"").append(words[i + 2]).append("\"");
                }
        return builder.toString();
    }
}
