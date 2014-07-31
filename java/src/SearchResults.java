package com.cakoose.json_tuple_databinding_examples;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import static com.cakoose.json_tuple_databinding_examples.util.Builder.*;

public class SearchResults
{
    public final int elapsed;
    public final Entry[] entries;

    @JsonCreator
    public SearchResults(@JsonProperty("elapsed") int elapsed, @JsonProperty("entries") Entry[] entries)
    {
        this.elapsed = elapsed;
        this.entries = entries;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchResults searchResults = (SearchResults) o;

        if (elapsed != searchResults.elapsed) return false;
        if (!Arrays.equals(entries, searchResults.entries)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = elapsed;
        result = 31 * result + Arrays.hashCode(entries);
        return result;
    }

    public String toJson()
    {
        String[] resultsJson = new String[entries.length];
        for (int i = 0; i < entries.length; i++) {
            resultsJson[i] = entries[i].toJson();
        }
        return dict(
            field("elapsed", num(elapsed)),
            field("entries", list(resultsJson)));
    }
}
