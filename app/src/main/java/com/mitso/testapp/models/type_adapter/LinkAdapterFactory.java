package com.mitso.testapp.models.type_adapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class LinkAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson _gson, TypeToken<T> _type)  {

        return (TypeAdapter<T>) new LinkAdapter(_gson);
    }
}