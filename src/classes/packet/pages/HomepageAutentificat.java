package classes.packet.pages;

public final class HomepageAutentificat extends SitePage {
    private static HomepageAutentificat instance = new HomepageAutentificat();

    private HomepageAutentificat() {
        super("homepage autentificat");
    }
    public static HomepageAutentificat getInstance() {
        return instance;
    }
}
