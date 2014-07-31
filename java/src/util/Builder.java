package com.cakoose.json_tuple_databinding_examples.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Primitive helper functions for generating JSON.
 */
public class Builder
{
    public static String str(String s)
    {
        StringWriter out = new StringWriter();
        try {
            JsonGenerator gen = new JsonFactory().createGenerator(out);
            gen.writeString(s);
            gen.flush();
        }
        catch (IOException ex) {
            throw (AssertionError) new AssertionError("IOException from StringWriter?").initCause(ex);
        }
        return out.toString();
    }

    public static String num(int v) { return Integer.toString(v); }

    public static String list(String... elements)
    {
        StringBuilder b = new StringBuilder();
        String sep = "";
        b.append("[");
        for (String e : elements) {
            b.append(sep); sep = ", ";
            b.append(e);
        }
        b.append("]");
        return b.toString();
    }

    public static String dict(String... elements)
    {
        StringBuilder b = new StringBuilder();
        String sep = "";
        b.append("{");
        for (String e : elements) {
            b.append(sep); sep = ", ";
            b.append(e);
        }
        b.append("}");
        return b.toString();
    }

    public static String field(String name, String value)
    {
        return str(name) + ": " + value;
    }
}
