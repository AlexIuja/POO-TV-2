package classes.packet.pages;

public final class RegisterPage extends SitePage {
    private static RegisterPage instance = new RegisterPage();
    private RegisterPage() {
        super("register");
    }
    public static RegisterPage getInstance() {
        return instance;
    }
}
