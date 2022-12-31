package classes.packet.pages;

public final class LogoutPage extends SitePage {
    private static LogoutPage instance = new LogoutPage();
    private LogoutPage() {
        super("logout");
    }
    public static LogoutPage getInstance() {
        return instance;
    }
}
