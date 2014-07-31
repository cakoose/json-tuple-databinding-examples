package com.cakoose.json_tuple_databinding_examples;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

/**
 * Jackson serialization and deserialization code to represent {@link Entry} using
 * a JSON array instead of a JSON object.
 *
 * See {@link #configure} to see how to make Jackson use our custom serializer and
 * deserializer.
 */
public class JacksonEntryCustomizer
{
    /**
     * Configures the given {@code }ObjectMapper} to use our custom serializer and
     * deserializer.  Alternatively, you can use the Jackson {@code JsonSerialize}
     * and {@code JsonDeserialize} annotations on the class (see: {@code Entry}).
     */
    public static void configure(ObjectMapper mapper)
    {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Entry.class, new Serializer());
        module.addDeserializer(Entry.class, new Deserializer());

        mapper.registerModule(module);
    }

    public static class Serializer extends JsonSerializer<Entry>
    {
        @Override
        public void serialize(Entry value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException
        {
            jgen.writeStartArray();
            jgen.writeString(value.name);
            jgen.writeObject(value.info);
            jgen.writeEndArray();
        }
    }

    public static class Deserializer extends JsonDeserializer<Entry>
    {
        public Entry deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException
        {
            JsonToken t;

            t = jp.getCurrentToken();
            if (t != JsonToken.START_ARRAY) {
                throw new InvalidFormatException("expecting JSON array, got " + t, jp.getCurrentLocation(), t, Entry.class);
            }
            jp.nextToken();

            String path = jp.readValueAs(String.class);
            Metadata info = jp.readValueAs(Metadata.class);

            t = jp.nextToken();
            if (t != JsonToken.END_ARRAY) {
                throw new InvalidFormatException("expecting only two elements in array", jp.getCurrentLocation(), t, Entry.class);
            }

            return new Entry(path, info);
        }
    }

}
