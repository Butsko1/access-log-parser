import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
                int countOfStrings = 0;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader =
                            new BufferedReader(fileReader);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        countOfStrings++;
                        int length = line.length();
                        if(length > 1024){
                            throw new ToLongException("Длина строки номер " + countOfStrings + " превышает 1024 символа");
                        }
                        if(length > max){
                            max = length;
                        }
                        if(length < min){
                            min = length;
                        }
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    System.out.println("Общее количество строк: " + countOfStrings);
                    System.out.println("Максимальная длина строки: " + max);
                    System.out.println("Минимальная длина строки: " + min);
                }

            }
            if(!fileExists){
                System.out.println("По указанному пути ничего не найдено. Попробуйте еще раз.");
            }


        }
    }
}
