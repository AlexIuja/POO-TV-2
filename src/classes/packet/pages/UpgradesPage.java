package classes.packet.pages;

public final class UpgradesPage extends SitePage {
    private static UpgradesPage instance = new UpgradesPage();
    private UpgradesPage() {
        super("upgrades");
    }
    public static UpgradesPage getInstance() {
        return instance;
    }
}
