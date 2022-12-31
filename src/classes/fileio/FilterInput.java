package classes.fileio;

public final class FilterInput {
    private ContainsInput contains;
    private SortInput sort;

    public FilterInput() {
    }

    public ContainsInput getContains() {
        return contains;
    }

    public void setContains(final ContainsInput contains) {
        this.contains = contains;
    }

    public SortInput getSort() {
        return sort;
    }

    public void setSort(final SortInput sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "FilterInput{"
                + "contains=" + contains
                + ", sort=" + sort
                + '}';
    }
}
