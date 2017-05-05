package com.mitso.testapp.database.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.database.DatabaseHelper;
import com.mitso.testapp.models.json_entry_list.Entry;

import java.util.List;

public class DbSaveEntryListTask extends AsyncTask<Void, Void, Void> {

    public String LOG_TAG = Constants.DB_SAVE_ENTRY_LIST_TASK_LOG_TAG;

    public interface Callback {

        void onSuccess();
        void onFailure(Throwable _error);
    }

    private DatabaseHelper      mDatabaseHelper;
    private List<Entry>         mEntryList;
    private String              mTableName;
    private Callback            mCallback;
    private Exception           mException;
    private SQLiteDatabase      mSQLiteDatabase;

    public DbSaveEntryListTask(Context _context, List<Entry> _entryList, String _tableName) {

        this.mDatabaseHelper = DatabaseHelper.getDatabaseHelper(_context);
        this.mEntryList = _entryList;
        this.mTableName = _tableName;
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

            mSQLiteDatabase.execSQL(DatabaseHelper.DELETE_FROM + DatabaseHelper.SPACE + mTableName);

            for (int i = 0; i < mEntryList.size(); i++) {

                final Entry entry = mEntryList.get(i);

                final ContentValues values = new ContentValues();

                values.put(DatabaseHelper.COLUMN_ENTRY_NAME, new Gson().toJson(entry.getImName()));
                values.put(DatabaseHelper.COLUMN_ENTRY_PRICE, new Gson().toJson(entry.getImPrice()));
                values.put(DatabaseHelper.COLUMN_ENTRY_IMAGE, new Gson().toJson(entry.getImImage()));
                values.put(DatabaseHelper.COLUMN_ENTRY_ARTIST, new Gson().toJson(entry.getImArtist()));
                values.put(DatabaseHelper.COLUMN_ENTRY_CONTENT_TYPE, new Gson().toJson(entry.getImContentType()));
                values.put(DatabaseHelper.COLUMN_ENTRY_RELEASE_DATE, new Gson().toJson(entry.getImReleaseDate()));
                values.put(DatabaseHelper.COLUMN_ENTRY_LINK, new Gson().toJson(entry.getLink()));
                values.put(DatabaseHelper.COLUMN_ENTRY_RIGHTS, new Gson().toJson(entry.getRights()));
                values.put(DatabaseHelper.COLUMN_ENTRY_TITLE, new Gson().toJson(entry.getTitle()));
                values.put(DatabaseHelper.COLUMN_ENTRY_ID, new Gson().toJson(entry.getId()));
                values.put(DatabaseHelper.COLUMN_ENTRY_CATEGORY, new Gson().toJson(entry.getCategory()));
                values.put(DatabaseHelper.COLUMN_ENTRY_SUMMARY, new Gson().toJson(entry.getSummary()));

                mSQLiteDatabase.insert(mTableName, null, values);
            }

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
