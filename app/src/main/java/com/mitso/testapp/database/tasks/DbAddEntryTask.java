package com.mitso.testapp.database.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.database.DatabaseHelper;
import com.mitso.testapp.models.Entry;

public class DbAddEntryTask extends AsyncTask<Void, Void, Void> {

    public String               LOG_TAG = Constants.DB_ADD_ENTRY_TASK_LOG_TAG;

    public interface Callback{

        void onSuccess();
        void onFailure(Throwable _error);
    }

    private DatabaseHelper      mDatabaseHelper;
    private Entry               mEntry;
    private Callback            mCallback;
    private Exception           mException;
    private SQLiteDatabase      mSQLiteDatabase;

    public DbAddEntryTask(Context _context, Entry _entry) {

        this.mDatabaseHelper = DatabaseHelper.getDatabaseHelper(_context);
        this.mEntry = _entry;
    }

    public void setCallback(Callback _callback) {

        if (mCallback == null)
            mCallback = _callback;
    }

    public void releaseCallback() {

        if (mCallback != null)
            mCallback = null;
    }

    @Override
    protected Void doInBackground(Void... _voids) {

        try {
            mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();

            final ContentValues values = new ContentValues();

            values.put(DatabaseHelper.COLUMN_ENTRY_NAME, new Gson().toJson(mEntry.getImName()));
            values.put(DatabaseHelper.COLUMN_ENTRY_PRICE, new Gson().toJson(mEntry.getImPrice()));
            values.put(DatabaseHelper.COLUMN_ENTRY_IMAGE, new Gson().toJson(mEntry.getImImage()));
            values.put(DatabaseHelper.COLUMN_ENTRY_ARTIST, new Gson().toJson(mEntry.getImArtist()));
            values.put(DatabaseHelper.COLUMN_ENTRY_CONTENT_TYPE, new Gson().toJson(mEntry.getImContentType()));
            values.put(DatabaseHelper.COLUMN_ENTRY_RELEASE_DATE, new Gson().toJson(mEntry.getImReleaseDate()));
            values.put(DatabaseHelper.COLUMN_ENTRY_LINK, new Gson().toJson(mEntry.getLink()));
            values.put(DatabaseHelper.COLUMN_ENTRY_RIGHTS, new Gson().toJson(mEntry.getRights()));
            values.put(DatabaseHelper.COLUMN_ENTRY_TITLE, new Gson().toJson(mEntry.getTitle()));
            values.put(DatabaseHelper.COLUMN_ENTRY_ID, new Gson().toJson(mEntry.getId()));
            values.put(DatabaseHelper.COLUMN_ENTRY_CATEGORY, new Gson().toJson(mEntry.getCategory()));
            values.put(DatabaseHelper.COLUMN_ENTRY_SUMMARY, new Gson().toJson(mEntry.getSummary()));

            mSQLiteDatabase.insert(DatabaseHelper.DATABASE_TABLE, null, values);

        } catch (Exception _error) {

            _error.printStackTrace();
            mException = _error;

        } finally {

            if (mSQLiteDatabase != null && mSQLiteDatabase.isOpen())
                mSQLiteDatabase.close();

            if (mDatabaseHelper != null)
                mDatabaseHelper.close();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void _aVoid) {
        super.onPostExecute(_aVoid);

        if (mCallback != null) {

            if (mException == null)
                mCallback.onSuccess();
            else
                mCallback.onFailure(mException);
        }
    }
}
