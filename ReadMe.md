# JSON Tuple Databinding Examples

Usually, logical records/structs are encoded as JSON objects.  For example, a search API might return:

```json
{
    "elapsed": 321000,
    "entries": [
        {"name": "Manifesto.txt", "metadata": {"size": 914235, "date": "2014-01-12"}},
        {"name": ".bashrc", "metadata": {"size": 670, "date": "2010-09-28"}}
    ]
}
```

If you define classes for each of the structures, JSON libraries with databinding support can automatically create object instances for you.

But lets say that instead of using a JSON object for each element of "entries", the search API returned a tuple-like representation using JSON arrays:

```json
{
    "elapsed": 321000,
    "entries": [
        ["Manifesto.txt", {"size": 914235, "date": "2014-01-12"}],
        [".bashrc", {"size": 670, "date": "2010-09-28"}]
    ]
}
```

JSON libraries with automatic databinding won't know how to convert each tuple into an `Entry` object.

This repo has code that shows you how to get tuples to work for different JSON libraries:

Java:
- Jackson - [JacksonEntryCustomizer.java](java/src/JacksonEntryCustomizer.java)
- Gson - [GsonEntryCustomizer.java](java/src/GsonEntryCustomizer.java)

TODO: C#
