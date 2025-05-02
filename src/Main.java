import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        int firtstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int secondNumber = new Scanner(System.in).nextInt();
        System.out.println("Сумма: " + (firtstNumber + secondNumber));
        System.out.println("Разность: " + (firtstNumber - secondNumber));
        System.out.println("Произведение: " + (firtstNumber * secondNumber));
        System.out.println("Частное: " + ((double) firtstNumber / secondNumber));
    }
}
