package com.mitso.testapp.recycler_view.favourites;

import com.mitso.testapp.models.Entry;

public interface IFavouriteHandler {

    void showInfo(Entry _entry);
    void deleteFromFavourites(Entry _entry, int _position);
}
