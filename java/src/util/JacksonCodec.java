package com.cakoose.json_tuple_databinding_examples.util;

import com.cakoose.json_tuple_databinding_examples.JacksonEntryCustomizer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class JacksonCodec extends Codec
{
    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        // Tell the ObjectMapper about our custom serializer and deserializer.  This is
        // redundant with the @JsonSerializer and @JsonDeserializer annotations on the
        // Entry class; we're doing this just to show what it looks like.
        JacksonEntryCustomizer.configure(mapper);
    }

    public String encode(Object o)
    {
        try {
            return mapper.writeValueAsString(o);
        }
        catch (JsonProcessingException ex) {
            throw (AssertionError) new AssertionError("unexpected exception").initCause(ex);
        }
    }

    public <T> T decode(String s, Class<? extends T> clazz)
    {
        try {
            return mapper.readValue(s, clazz);
        }
        catch (IOException ex) {
            throw (AssertionError) new AssertionError("unexpected exception").initCause(ex);
        }
    }
}
