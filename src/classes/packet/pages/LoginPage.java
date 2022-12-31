package classes.packet.pages;


public final class LoginPage extends SitePage {
    private static LoginPage instance = new LoginPage();
    private LoginPage() {
        super("login");
    }
    public static LoginPage getInstance() {
        return instance;
    }
}
