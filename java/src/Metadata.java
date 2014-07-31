package com.cakoose.json_tuple_databinding_examples;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.cakoose.json_tuple_databinding_examples.util.Builder.*;

public class Metadata
{
    public final int size;
    public final String date;

    @JsonCreator
    public Metadata(@JsonProperty("size") int size, @JsonProperty("date") String date)
    {
        this.size = size;
        this.date = date;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metadata metadata = (Metadata) o;

        if (size != metadata.size) return false;
        if (!date.equals(metadata.date)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = size;
        result = 31 * result + date.hashCode();
        return result;
    }

    public String toJson()
    {
        return dict(field("size", num(size)), field("date", str(date)));
    }
}
