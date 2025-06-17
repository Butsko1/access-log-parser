public class UserAgent {
    private final String os;
    private final String browser;

    public String getOs() {
        return os;
    }

    public String getBrowser() {
        return browser;
    }

    UserAgent(String s){
        this.browser = RegEx.returnBrowser(s);
        this.os = RegEx.returnOS(s);
    }
}
