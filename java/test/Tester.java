package com.cakoose.json_tuple_databinding_examples;

import com.cakoose.json_tuple_databinding_examples.util.Codec;
import com.cakoose.json_tuple_databinding_examples.util.GsonCodec;
import com.cakoose.json_tuple_databinding_examples.util.JacksonCodec;
import com.fasterxml.jackson.databind.JsonNode;
import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Tester
{
    @Test(dataProvider = "codecs")
    public void testRead(Codec codec)
    {
        assertEquals(codec.decode(TestValues.S1, SearchResults.class), TestValues.O1);
    }

    @Test(dataProvider = "codecs")
    public void testWrite(Codec codec)
    {
        JsonNode rt = TestValues.parse(codec.encode(TestValues.O1));
        assertEquals(rt, TestValues.T1);
    }

    @DataProvider(name = "codecs")
    public Object[][] codecProvider()
    {
        return new Object[][] {
            {new JacksonCodec()},
            {new GsonCodec()},
        };
    }
}
