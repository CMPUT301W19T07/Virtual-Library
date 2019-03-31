package vl.team07.com.virtuallibrary;

/**
 * Stores the search input
 * @version 1.0
 * @since 1.0
 */
public class Search {

    private String Search;

    /**
     * Instantiates a new Search.
     */
    public Search() {
        this.Search = "Search";
    }

    /**
     * Get search string.
     *
     * @return the string
     */
    public String getSearch(){
        return this.Search;
    }

    /**
     * Set search.
     *
     * @param inputSearch the input search
     */
    public void setSearch(String inputSearch){
        this.Search = inputSearch;
    }

}
