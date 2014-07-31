package com.cakoose.json_tuple_databinding_examples;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static com.cakoose.json_tuple_databinding_examples.util.Builder.*;

/**
 * This is the class that we represent in JSON as an array.
 *
 * The code to do the custom representation is in {@link GsonEntryCustomizer} and
 * {@link JacksonEntryCustomizer}.
 */
@JsonDeserialize(using = JacksonEntryCustomizer.Deserializer.class)
@JsonSerialize(using = JacksonEntryCustomizer.Serializer.class)
public class Entry
{
    public final String name;
    public final Metadata info;

    public Entry(String name, Metadata info)
    {
        this.name = name;
        this.info = info;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry result = (Entry) o;

        if (!name.equals(result.name)) return false;
        if (!info.equals(result.info)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = name.hashCode();
        result = 31 * result + info.hashCode();
        return result;
    }

    public String toJson()
    {
        return list(str(name), info.toJson());
    }

}
