package classes.packet.actions;

import classes.fileio.ActionInput;
import classes.packet.otherClasses.Site;

public final class ActionFactory {
    private static Site site;

    private ActionFactory() {
    }

    /**
     *
     * @param input
     * @return
     */
    public static Action createAction(final ActionInput input) {
        switch (input.getType()) {
            case "change page": return new ChangePage(input, site);
            case "back": return new Back(input, site);
            default: //checkstyle handling
        }
        switch (input.getFeature()) {
            case "subscribe": return new Subscribe(input, site);
            case "add": return new AddMovie(input, site);
            case "delete": return new DeleteMovie(input, site);
            case "login": return new Login(input, site);
            case "register": return new Register(input, site);
            case "search": return new Search(input, site);
            case "filter": return new Filter(input, site);
            case "purchase": return new Purchase(input, site);
            case "watch": return new Watch(input, site);
            case "like": return new Like(input, site);
            case "rate": return new Rate(input, site);
            case "buy premium account": return new BuyPrem(input, site);
            case "buy tokens": return new BuyTokens(input, site);
            default: //checkstyle handling
        }
        throw new IllegalArgumentException("The action "
                + input.getFeature() + " is not recognized.");
    }

    public static Site getSite() {
        return site;
    }

    public static void setSite(final Site site) {
        ActionFactory.site = site;
    }
}
