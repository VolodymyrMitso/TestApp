package com.mitso.testapp.recycler_view;

import com.mitso.testapp.models.Entry;

public interface IEntryHandler {

    void showInfo(Entry _entry);
    void addToFavourites(Entry _entry);
}
