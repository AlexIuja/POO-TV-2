package classes.packet.pages;

public final class HomepageNeautentificat extends SitePage {
    private static HomepageNeautentificat instance = new HomepageNeautentificat();
    private HomepageNeautentificat() {
        super("homepage neautentificat");
    }
    public static HomepageNeautentificat getInstance() {
        return instance;
    }
}
