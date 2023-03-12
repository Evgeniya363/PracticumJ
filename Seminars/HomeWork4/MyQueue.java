//     Реализуйте очередь с помощью LinkedList со следующими методами:
//     • enqueue() — помещает элемент в конец очереди,
//     • dequeue() — возвращает первый элемент из очереди и удаляет его,
//     • first() — возвращает первый элемент из очереди, не удаляя.

package Seminars.HomeWork4;

import java.util.LinkedList;
import java.util.Scanner;

public class MyQueue<T> {
    private LinkedList<T> myQueue = new LinkedList<>();
    private static Scanner scanner;

    public void enqueue(T item) {
        this.myQueue.addLast(item);
    }

    public T dequeue() {
        return this.myQueue.pollFirst();
    }

    public T first() {
        return this.myQueue.peekFirst();
    }

    public void printMyQueue() {
        if (myQueue.isEmpty())
            System.out.println("\nYour list is empty");
        else {
            System.out.print("\nYour list: ");
            System.out.println(this.myQueue.toString());
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        menu();
        System.out.println("The program is completed\n");
        scanner.close();
    }

    public static void menu() {
        MyQueue<String> i = new MyQueue<>();
        i.printMyQueue();
        String menu = "\n1. enqueue()\n2. dequeue()\n3. first()\n0. quit ";
        int mode = 0;
        do {
            System.out.println(menu);
            System.out.print("Your choice: ");
            try {
                mode = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid enter");
                continue;
            }
            switch (mode) {
                case 1:
                    System.out.print("Enter the line: ");
                    i.enqueue(scanner.nextLine());
                    break;
                case 2:
                    i.dequeue();
                    break;
                case 3:
                    System.out.println("The first element is \"" + i.first() + "\"");
                    break;
            }
            i.printMyQueue();
        } while (mode > 0);
    }
}