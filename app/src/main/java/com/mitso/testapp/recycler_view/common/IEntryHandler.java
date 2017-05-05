package com.mitso.testapp.recycler_view.common;

import com.mitso.testapp.models.json_entry_list.Entry;

public interface IEntryHandler {

    void showInfo(Entry _entry);
    void addToFavourites(Entry _entry);
}