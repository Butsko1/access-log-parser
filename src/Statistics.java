import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime min_time;
    private LocalDateTime max_time;
    public Statistics(){
        this.totalTraffic = 0;
        this.max_time = LocalDateTime.MIN;
        this.min_time = LocalDateTime.MAX;
    }

    public void addEntry(LogEntry logEntry){
        this.totalTraffic += logEntry.getResponseSize();
        if(logEntry.getTime().isAfter(this.max_time)){
            this.max_time = logEntry.getTime();
        }
        if(logEntry.getTime().isBefore(this.min_time)){
            this.min_time = logEntry.getTime();
        }
    }

    public double getTrafficRate(){
        Duration duration = Duration.between(this.min_time, this.max_time);
        long hours = duration.toHours();
        try{
            return this.totalTraffic/hours;
        }
        catch (ArithmeticException ex){
            return this.totalTraffic; //На случай, если одна запись или все записи в течении одного часа
        }
    }
}
