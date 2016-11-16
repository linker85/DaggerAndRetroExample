
package com.example.com.examw4.model2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Example {

    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("search_results")
    @Expose
    private List<SearchResult> searchResults = new ArrayList<SearchResult>();

    /**
     * 
     * @return
     *     The metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * 
     * @param metadata
     *     The metadata
     */
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /**
     * 
     * @return
     *     The searchResults
     */
    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    /**
     * 
     * @param searchResults
     *     The search_results
     */
    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

}
