package com.mitso.testapp.support;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.mitso.testapp.R;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.models.json_entry_list.Entry;
import com.mitso.testapp.models.recycler_view.BaseModel;
import com.mitso.testapp.models.recycler_view.Header;
import com.mitso.testapp.models.recycler_view.Separator;

import java.util.ArrayList;
import java.util.List;

public class Support {

    public boolean checkNetworkConnection(Context _context) {

        final ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfoWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final NetworkInfo networkInfoMobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return ((networkInfoWifi != null && networkInfoWifi.isConnected()) || (networkInfoMobile != null && networkInfoMobile.isConnected()));
    }

    public void showToastFirstRun(Context _context) {

        Toast.makeText(_context, R.string.s_first_run, Toast.LENGTH_SHORT).show();
    }

    public void showToastNetworkConnection(Context _context) {

        Toast.makeText(_context, R.string.s_network_connection, Toast.LENGTH_SHORT).show();
    }

    public void showToastError(Context _context) {

        Toast.makeText(_context, R.string.s_error, Toast.LENGTH_SHORT).show();
    }

    public void showToastEmptyFavourites(Context _context) {

        Toast.makeText(_context, R.string.s_empty_favourites, Toast.LENGTH_SHORT).show();
    }

    public void showToastAlreadyIn(Context _context) {

        Toast.makeText(_context, R.string.s_already_in, Toast.LENGTH_SHORT).show();
    }

    public void showToastAdded(Context _context) {

        Toast.makeText(_context, R.string.s_added, Toast.LENGTH_SHORT).show();
    }

    public void showToastDeleted(Context _context) {

        Toast.makeText(_context, R.string.s_deleted, Toast.LENGTH_SHORT).show();
    }

    public List<BaseModel> groupList(Context context, List<Entry> _entryList) {

        final List<BaseModel> AudiobookList = new ArrayList<>();
        final List<BaseModel> MovieList = new ArrayList<>();
        final List<BaseModel> PodcastList = new ArrayList<>();

        for (int i = 0; i < _entryList.size(); i++) {

            final Entry entry = _entryList.get(i);

            switch (entry.getImContentType().getAttributes().getLabel()) {
                case Constants.CONTENT_TYPE_AUDIOBOOK:
                    AudiobookList.add(entry);
                    AudiobookList.add(new Separator());
                    break;
                case Constants.CONTENT_TYPE_MOVIE:
                    MovieList.add(entry);
                    MovieList.add(new Separator());
                    break;
                case Constants.CONTENT_TYPE_PODCAST:
                    PodcastList.add(entry);
                    PodcastList.add(new Separator());
                    break;
            }
        }

        final List <BaseModel> favouriteList = new ArrayList<>();

        if (!AudiobookList.isEmpty()) {

            AudiobookList.remove(AudiobookList.size() - 1);
            favouriteList.add(new Header(context.getResources().getString(R.string.s_audiobooks)));
            favouriteList.addAll(AudiobookList);
        }

        if (!MovieList.isEmpty()) {

            MovieList.remove(MovieList.size() - 1);
            favouriteList.add(new Header(context.getResources().getString(R.string.s_movies)));
            favouriteList.addAll(MovieList);
        }

        if (!PodcastList.isEmpty()) {

            PodcastList.remove(PodcastList.size() - 1);
            favouriteList.add(new Header(context.getResources().getString(R.string.s_podcasts)));
            favouriteList.addAll(PodcastList);
        }

        return favouriteList;
    }
}
