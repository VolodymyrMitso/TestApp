package com.mitso.testapp.support;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.mitso.testapp.R;
import com.mitso.testapp.database.DatabaseHelper;

public class Support {

    public boolean checkNetworkConnection(Context _context) {

        final ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfoWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final NetworkInfo networkInfoMobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return ((networkInfoWifi != null && networkInfoWifi.isConnected()) || (networkInfoMobile != null && networkInfoMobile.isConnected()));
    }

    public void showToastNoNetworkConnection(Context _context) {

        Toast.makeText(_context, R.string.s_no_network_connection, Toast.LENGTH_LONG).show();
    }

    public boolean checkDatabaseExistence(Context _context) {

        return _context.getDatabasePath(DatabaseHelper.DATABASE_NAME).exists();
    }

    public void showToastEmptyFavourites(Context _context) {

        Toast.makeText(_context, R.string.s_empty_favourites, Toast.LENGTH_LONG).show();
    }
}
