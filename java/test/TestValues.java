package com.cakoose.json_tuple_databinding_examples;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestValues
{
    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode parse(String s)
    {
        if (s == null) throw new IllegalArgumentException("'s' can't be null");
        try {
            return mapper.readTree(s);
        }
        catch (IOException ex) {
            throw (AssertionError) new AssertionError("unexpected").initCause(ex);
        }
    }

    public static final SearchResults O1 = new SearchResults(1234, new Entry[] {
        new Entry("Manifesto.txt", new Metadata(914235, "2014-01-12")),
        new Entry(".bashrc", new Metadata(670, "2010-09-28")),
    });

    public static final String S1 = O1.toJson();

    public static final JsonNode T1 = parse(S1);
}
