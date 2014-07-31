package com.cakoose.json_tuple_databinding_examples;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gson serialization and deserialization code to represent {@link Entry} using
 * a JSON array instead of a JSON object.
 *
 * You need to configure your {@code GsonBuilder} to use this serializer and deserializer:
 * <pre>
 * GsonBuilder builder = new GsonFactory();
 * builder.registerTypeAdapterFactory(GsonEntryCustomizer.Factory);
 * ...
 * Gson gson = builder.create();
 *
 * gson.toJson(...);
 * gson.fromJson(...);
 * </pre>
 */
public class GsonEntryCustomizer
{
    public static final class Adapter extends TypeAdapter<Entry>
    {
        private final Gson gson;

        public Adapter(Gson gson)
        {
            this.gson = gson;
        }

        @Override
        public void write(JsonWriter out, Entry value) throws IOException
        {
            out.beginArray();
            out.value(value.name);
            gson.getAdapter(Metadata.class).write(out, value.info);
            out.endArray();
        }

        @Override
        public Entry read(JsonReader in) throws IOException
        {
            in.beginArray();
            String path = in.nextString();
            Metadata info = gson.getAdapter(Metadata.class).read(in);
            in.endArray();

            return new Entry(path, info);
        }
    }

    public static final TypeAdapterFactory Factory = new TypeAdapterFactory()
    {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type)
        {
            if (!type.getRawType().equals(Entry.class)) return null;

            @SuppressWarnings("unchecked")
            TypeAdapter<T> casted = (TypeAdapter<T>) new Adapter(gson);

            return casted;
        }
    };
}
