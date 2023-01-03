package classes.packet.actions;

import classes.packet.otherClasses.Output;
import classes.packet.otherClasses.Site;
import classes.fileio.ActionInput;
import classes.fileio.ContainsInput;
import classes.fileio.SortInput;

public final class Filter implements Action {
    private String type;
    private String feature;
    private SortInput sort;
    private ContainsInput contains;
    private Site site;

    public Filter(final ActionInput input, final Site site) {
        type = input.getType();
        feature = input.getFeature();
        sort = input.getFilters().getSort();
        contains = input.getFilters().getContains();
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

    public SortInput getSort() {
        return sort;
    }

    public void setSort(final SortInput sort) {
        this.sort = sort;
    }

    public ContainsInput getContains() {
        return contains;
    }

    public void setContains(final ContainsInput contains) {
        this.contains = contains;
    }


    @Override
    public Output accept(final ActionVisitor visitor) {
        return visitor.visit(this, site);
    }


    @Override
    public String toString() {
        return "Filter{"
                + "feature='" + feature + '\''
                + ", sort=" + sort
                + ", contains=" + contains
                + '}';
    }
}
