// Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернёет «перевёрнутый» список.
package Seminars.HomeWork4;

import java.util.LinkedList;

public class ReverceList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list = initList(list, 10);
        System.out.println(list);
        list = revercerList(list);
        System.out.println(list);
    }

    static LinkedList<Integer> initList(LinkedList<Integer> list, int n) {
        for (int i = 0; i < 10; i++)
            list.add((int) (Math.random() * 100));
        return list;
    }

    static LinkedList<Integer> revercerList(LinkedList<Integer> list) {
        LinkedList<Integer> listR = new LinkedList<Integer>();
        for (int i = 0; i < 10; i++)
            listR.add(list.removeLast());
        return listR;
    }
}
