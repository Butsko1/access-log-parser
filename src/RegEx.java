import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class RegEx {
    static Pattern ipAddrPattern = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");
    static Pattern timePattern = Pattern.compile("\\[(.*?)\\]");
    static Pattern methodPattern = Pattern.compile("\"(.*?) /");
    static  Pattern pathPattern = Pattern.compile(" (/.*) HTTP");
    static  Pattern codePattern = Pattern.compile(" (\\d{3}) ");
    static  Pattern sizePattern = Pattern.compile(" \\d{3} (\\d*) ");
    static  Pattern refererPattern = Pattern.compile("\\d \"(.*?)\"");
    static  Pattern browserPattern = Pattern.compile("\\d \".*?\" \"(Chrome|Mozilla|Edge|Opera)/");
    static  Pattern osPattern = Pattern.compile("/\\d.\\d \\((Windows|macOS|Linux);");
    static Pattern isBotPattern = Pattern.compile("\\(.*Bot.*\\)");
    static  Pattern refererPagePattern = Pattern.compile("//(.*?)/");

    public static String returnIp(String s){
        Matcher matcher = ipAddrPattern.matcher(s);
        if(matcher.find()){
            return matcher.group();
        }
        return "IP адрес не определен";
    }

    public static String returnTime(String s){
        Matcher matcher = timePattern.matcher(s);
        if(matcher.find()){
            return matcher.group(1);
        }
        return "Время не определено";
    }

    public static String returnMethod(String s){
        Matcher matcher = methodPattern.matcher(s);
        if(matcher.find()){
            return matcher.group(1);
        }
        return "Метод не определен";
    }

    public static String returnPath(String s){
        Matcher matcher = pathPattern.matcher(s);
        if(matcher.find()){
            return matcher.group(1);
        }
        return "Адрес вызова не определен";
    }

    public static String returnCode(String s){
        Matcher matcher = codePattern.matcher(s);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }

    public static String returnSize(String s){
        Matcher matcher = sizePattern.matcher(s);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }

    public static String returnReferer(String s){
        Matcher matcher = refererPattern.matcher(s);
        if(matcher.find()){
            return matcher.group(1);
        }
        return "Страница перехода не определена";
    }

    public static String returnBrowser(String s){
        Matcher matcher = browserPattern.matcher(s);
        if(matcher.find()){
            return matcher.group(1);
        }
        return "Браузер не определен";
    }

    public static String returnOS(String s){
        Matcher matcher = osPattern.matcher(s);
        if(matcher.find()){
            return matcher.group(1);
        }
        return "ОС не определена";
    }
    public static boolean returnIsBot(String s){
        Matcher matcher = isBotPattern.matcher(s);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    public static String returnPageReferer(String s){
        Matcher matcher = refererPagePattern.matcher(s);
        if(matcher.find()){
            return matcher.group(1).trim();
        }
        return "Страница перехода не определена";
    }

    public enum Browsers{
        EDGE,
        CHROME,
        MOZILLA,
        OPERA
    }

}

