package com.cakoose.json_tuple_databinding_examples.util;

import com.cakoose.json_tuple_databinding_examples.GsonEntryCustomizer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonCodec extends Codec
{
    static final GsonBuilder builder = new GsonBuilder();
    static {
        builder.registerTypeAdapterFactory(GsonEntryCustomizer.Factory);
    }
    static final Gson gson = builder.create();

    public String encode(Object o)
    {
        return gson.toJson(o);
    }

    public <T> T decode(String s, Class<? extends T> clazz)
    {
        return gson.fromJson(s, clazz);
    }
}
