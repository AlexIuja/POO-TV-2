package classes.packet.actions;

import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;
import classes.fileio.ActionInput;
import classes.fileio.CredentialsInput;

public final class Register implements Action {
    private String type;
    private String feature;
    private CredentialsInput credentials;
    private Site site;

    public Register(final ActionInput input, final Site site) {
        type = input.getType();
        feature = input.getFeature();
        credentials = input.getCredentials();
        this.site = site;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(final Site site) {
        this.site = site;
    }


    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }


    @Override
    public Output accept(final ActionVisitor visitor) {
        return visitor.visit(this, site);
    }

    @Override
    public String toString() {
        return "Register{"
                + "feature='" + feature + '\''
                + '}';
    }
}
