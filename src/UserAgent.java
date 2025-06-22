public class UserAgent {
    private final String os;
    private final String browser;
    private boolean isBot;

    public String getOs() {
        return os;
    }

    public boolean getIsBot() {
        return isBot;
    }

    public String getBrowser() {
        return browser;
    }

    UserAgent(String s){
        this.browser = RegEx.returnBrowser(s);
        this.os = RegEx.returnOS(s);
        this.isBot = RegEx.returnIsBot(s);
    }
}
