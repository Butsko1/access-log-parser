import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        while (true){
            System.out.print("Введите путь к файлу: ");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if(fileExists && isDirectory){
                System.out.println("Указан путь к папке, а не к файлу");
                continue;
            }
            if(fileExists){
                count++;
                System.out.println("Это файл номер " + count);
                continue;
            }
            if(!fileExists){
                System.out.println("По указанному пути ничего не найдено. Попробуйте еще раз.");
            }
        }
    }
}
