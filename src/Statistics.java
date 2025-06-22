import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime min_time;
    private LocalDateTime max_time;
    private HashSet<String> existPages;
    private HashSet<String> unExistPages;
    private HashMap<String,Integer> osCount;
    private HashMap<String,Integer> browserCount;
    private long visitsByNormalBrowser;
    private long errorRequests;
    private HashSet<String> uniqueUsers;
    public Statistics(){
        this.totalTraffic = 0;
        this.max_time = LocalDateTime.MIN;
        this.min_time = LocalDateTime.MAX;
        this.existPages = new HashSet<>();
        this.osCount = new HashMap<>();
        this.unExistPages = new HashSet<>();
        this.browserCount = new HashMap<>();
        this.visitsByNormalBrowser = 0;
        this.errorRequests = 0;
        this.uniqueUsers = new HashSet<>();
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
            this.existPages.add(logEntry.getPath());
        } else if (logEntry.getResponseCode() == 404) {
            this.unExistPages.add(logEntry.getPath());
        }
        if(String.valueOf(logEntry.getResponseCode()).startsWith("4") || String.valueOf(logEntry.getResponseCode()).startsWith("5")){
            this.errorRequests++;
        }
        if(!this.osCount.containsKey(logEntry.getUserAgent().getOs())){
            this.osCount.put(logEntry.getUserAgent().getOs(),1);
        }
        else{
            this.osCount.put(logEntry.getUserAgent().getOs(),osCount.get(logEntry.getUserAgent().getOs()) + 1);
        }

        if(!this.browserCount.containsKey(logEntry.getUserAgent().getBrowser())){
            this.browserCount.put(logEntry.getUserAgent().getBrowser(),1);
        }
        else{
            this.browserCount.put(logEntry.getUserAgent().getBrowser(),browserCount.get(logEntry.getUserAgent().getBrowser()) + 1);
        }
        if(!logEntry.getUserAgent().getIsBot()){
            this.visitsByNormalBrowser++;
            this.uniqueUsers.add(logEntry.getIpAddr());
        }

    }
    public double getTrafficRate(){
        try{
            return this.totalTraffic/getDuration();
        }
        catch (ArithmeticException ex){
            return this.totalTraffic; //На случай, если одна запись или все записи в течении одного часа
        }
    }

    private long getDuration(){
        Duration duration = Duration.between(this.max_time, this.min_time);
        long hours = Math.abs(duration.toHours());
        return hours;
    }

    public HashSet<String> getExistingPages(){
        return this.existPages;
    }

    public HashSet<String> getUnExistingPages(){
        return this.unExistPages;
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

    public HashMap<String, Double> getBrowserStat(){
        double allBrowsers = 0;
        for(int i: this.browserCount.values()){
            allBrowsers += i;
        }
        HashMap<String, Double> stat = new HashMap<>();
        for(Map.Entry<String,Integer> entry:this.browserCount.entrySet()){
            stat.put(entry.getKey(),entry.getValue()/allBrowsers);
        }
        return stat;
    }

    public double getAverageVisitsPerHour(){
        try{
            return this.visitsByNormalBrowser/getDuration();
        }
        catch (ArithmeticException ex){
            return this.visitsByNormalBrowser; //На случай, если одна запись или все записи в течении одного часа
        }

    }

    public double getErrorVisitsPerHour(){

        try{
            return this.errorRequests/getDuration();
        }
        catch (ArithmeticException ex){
            return this.errorRequests; //На случай, если одна запись или все записи в течении одного часа
        }

    }

    public double getUniqueVisitsPerHour(){

        try{
            return this.uniqueUsers.size()/getDuration();
        }
        catch (ArithmeticException ex){
            return this.uniqueUsers.size(); //На случай, если одна запись или все записи в течении одного часа
        }

    }


}
