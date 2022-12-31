package classes.packet.pages;

import java.util.ArrayList;

public abstract class SitePage {
    private String pageName;
    private ArrayList<SitePage> allowedPagesToChange;


    public SitePage(final String pageName) {
        this.pageName = pageName;
    }

    /**
     *
     * @return
     */
    public String getPageName() {
        return pageName;
    }

    /**
     *
     * @param pageName
     */
    public void setPageName(final String pageName) {
        this.pageName = pageName;
    }

    /**
     *
     * @return
     */
    public ArrayList<SitePage> getAllowedPagesToChange() {
        return allowedPagesToChange;
    }

    /**
     *
     * @param allowedPagesToChange
     */
    public void setAllowedPagesToChange(final ArrayList<SitePage> allowedPagesToChange) {
        this.allowedPagesToChange = allowedPagesToChange;
    }
}
