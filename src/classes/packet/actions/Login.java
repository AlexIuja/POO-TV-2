package classes.packet.actions;

import classes.packet.Output;
import classes.packet.Site;
import classes.fileio.ActionInput;

public final class Login implements Action {
    private String type;
    private String feature;
    private String name;
    private String password;
    private Site site;

    public Login(final ActionInput input, final Site site) {
        type = input.getType();
        feature = input.getFeature();
        name = input.getCredentials().getName();
        password = input.getCredentials().getPassword();
        this.site = site;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(final Site site) {
        this.site = site;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public Output accept(final ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "Login{"
                + "feature='" + feature + '\''
                + ", name='" + name + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
