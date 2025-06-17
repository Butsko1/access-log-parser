import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    private final String ipAddr;
    private final LocalDateTime time;
    private final HttpMethod method;
    private final String path;
    private final  int responseCode;
    private final  int responseSize;
    private final String referer;
    UserAgent userAgent;

    public UserAgent getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getReferer() {
        return referer;
    }

    public int getResponseSize() {
        return responseSize;
    }


    public LogEntry(String string){;
        this.ipAddr = RegEx.returnIp(string);
        String time = RegEx.returnTime(string);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss X", Locale.ENGLISH);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(time, dateTimeFormatter);
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        this.time = localDateTime;
        String method = RegEx.returnMethod(string);
        this.method = HttpMethod.valueOf(method.toUpperCase());
        this.path = RegEx.returnPath(string);
        this.responseCode = Integer.parseInt(RegEx.returnCode(string));
        this.responseSize = Integer.parseInt(RegEx.returnSize(string));
        this.referer = RegEx.returnReferer(string);
        this.userAgent = new UserAgent(string);
    }



    public enum HttpMethod{
        GET,
        POST,
        PUT,
        DELETE
    }
}
