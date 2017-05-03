package com.mitso.testapp.models.recycler_view;

public abstract class BaseModel {

    public static final int TYPE_HEADER         = 1;
    public static final int TYPE_ENTRY          = 2;
    public static final int TYPE_SEPARATOR      = 3;

    abstract public int getType();
}