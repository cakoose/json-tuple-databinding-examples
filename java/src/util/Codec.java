package com.cakoose.json_tuple_databinding_examples.util;

/**
 * A simple interface to a JSON library that has databinding support.
 */
public abstract class Codec
{
    public abstract <T> T decode(String s, Class<? extends T> clazz);
    public abstract String encode(Object o);
}
