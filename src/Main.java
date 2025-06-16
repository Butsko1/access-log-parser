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
                int yandexBotCount = 0;
                int googleBotCount = 0;
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
                        String[] components = line.split(" \"");
                        String[] parts = components[3].split(";");
                        if (parts.length >= 2) {
                            String fragment = parts[1].trim();
                            try{
                                fragment = fragment.substring(0,fragment.indexOf("/"));
                                if(fragment.equals("YandexBot")){
                                    yandexBotCount++;
                                }
                                else if (fragment.equals("Googlebot")) {
                                    googleBotCount++;
                                }
                            }
                            catch (StringIndexOutOfBoundsException e){
                            }

                        }

                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    System.out.println("YandexBot: " + yandexBotCount);
                    System.out.println("Googlebot: " + googleBotCount);
                }

            }
            if(!fileExists){
                System.out.println("По указанному пути ничего не найдено. Попробуйте еще раз.");
            }


        }
    }
}
