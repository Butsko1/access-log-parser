import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime min_time;
    private LocalDateTime max_time;
    private HashSet<String> existPages;
    HashMap<String,Integer> osCount;
    public Statistics(){
        this.totalTraffic = 0;
        this.max_time = LocalDateTime.MIN;
        this.min_time = LocalDateTime.MAX;
        this.existPages = new HashSet<>();
        this.osCount = new HashMap<>();
    }


    public void addEntry(LogEntry logEntry){
        this.totalTraffic += logEntry.getResponseSize();
        if(logEntry.getTime().isAfter(this.max_time)){
            this.max_time = logEntry.getTime();
        }
        if(logEntry.getTime().isBefore(this.min_time)){
            this.min_time = logEntry.getTime();
        }
        if(logEntry.getResponseCode() == 200){
            existPages.add(logEntry.getPath());
        }
        if(!this.osCount.containsKey(logEntry.getUserAgent().getOs())){
            this.osCount.put(logEntry.getUserAgent().getOs(),1);
        }
        else{
            this.osCount.put(logEntry.getUserAgent().getOs(),osCount.get(logEntry.getUserAgent().getOs()) + 1);
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

    public HashSet<String> getExistingPages(){
        return this.existPages;
    }

    public HashMap<String, Double> getOsStat(){
        double allOs = 0;
        for(int i: this.osCount.values()){
            allOs += i;
        }
        HashMap<String, Double> stat = new HashMap<>();
        for(Map.Entry<String,Integer> entry:this.osCount.entrySet()){
            stat.put(entry.getKey(),entry.getValue()/allOs);
        }
        return stat;
    }


}
