package com.mitso.testapp.models.type_adapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mitso.testapp.models.entry.EntryLink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinkAdapter extends TypeAdapter<List<EntryLink>> {

    private Gson        mGson;

    public LinkAdapter(Gson _gson) {

        this.mGson = _gson;
    }

    @Override
    public void write(JsonWriter _out, List<EntryLink> _value) throws IOException {

    }

    @Override
    public List<EntryLink> read(JsonReader _jsonReader) throws IOException {

        switch (_jsonReader.peek()) {

            case BEGIN_OBJECT:
                final EntryLink link = mGson.fromJson(_jsonReader, EntryLink.class);
                final List<EntryLink> linkList = new ArrayList<>();
                linkList.add(link);
                return linkList;

            case BEGIN_ARRAY:
                return mGson.fromJson(_jsonReader, new TypeToken<List<EntryLink>>() {}.getType());

            default:
                return null;
        }
    }
}