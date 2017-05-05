package com.mitso.testapp.database.tasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.database.DatabaseHelper;
import com.mitso.testapp.models.json_entry_list.Entry;
import com.mitso.testapp.models.json_entry_list.entry.EntryCategory;
import com.mitso.testapp.models.json_entry_list.entry.EntryId;
import com.mitso.testapp.models.json_entry_list.entry.EntryLink;
import com.mitso.testapp.models.json_entry_list.entry.EntryRights;
import com.mitso.testapp.models.json_entry_list.entry.EntrySummary;
import com.mitso.testapp.models.json_entry_list.entry.EntryTitle;
import com.mitso.testapp.models.json_entry_list.entry.ImArtist;
import com.mitso.testapp.models.json_entry_list.entry.ImContentType;
import com.mitso.testapp.models.json_entry_list.entry.ImImage;
import com.mitso.testapp.models.json_entry_list.entry.ImName;
import com.mitso.testapp.models.json_entry_list.entry.ImPrice;
import com.mitso.testapp.models.json_entry_list.entry.ImReleaseDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbGetEntryListTask extends AsyncTask<Void, Void, List<Entry>> {

    public String               LOG_TAG = Constants.DB_GET_ENTRY_LIST_TASK_LOG_TAG;

    public interface Callback {

        void onSuccess(List<Entry> _result);
        void onFailure(Throwable _error);
    }

    private DatabaseHelper      mDatabaseHelper;
    private List<Entry>         mEntryList;
    private String              mTableName;
    private Callback            mCallback;
    private Exception           mException;
    private SQLiteDatabase      mSQLiteDatabase;
    private Cursor              mCursor;

    public DbGetEntryListTask(Context _context, String _tableName) {

        this.mDatabaseHelper = DatabaseHelper.getDatabaseHelper(_context);
        this.mEntryList = new ArrayList<>();
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
    protected List<Entry> doInBackground(Void ... _voids) {

        try {

            mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();

            final String[] projection = {
                    DatabaseHelper.COLUMN_ENTRY_NAME,
                    DatabaseHelper.COLUMN_ENTRY_PRICE,
                    DatabaseHelper.COLUMN_ENTRY_IMAGE,
                    DatabaseHelper.COLUMN_ENTRY_ARTIST,
                    DatabaseHelper.COLUMN_ENTRY_CONTENT_TYPE,
                    DatabaseHelper.COLUMN_ENTRY_RELEASE_DATE,
                    DatabaseHelper.COLUMN_ENTRY_LINK,
                    DatabaseHelper.COLUMN_ENTRY_RIGHTS,
                    DatabaseHelper.COLUMN_ENTRY_TITLE,
                    DatabaseHelper.COLUMN_ENTRY_ID,
                    DatabaseHelper.COLUMN_ENTRY_CATEGORY,
                    DatabaseHelper.COLUMN_ENTRY_SUMMARY
            };

            mCursor = mSQLiteDatabase.query(mTableName, projection,
                    null, null, null, null, null);

            while (mCursor.moveToNext()) {

                final String name = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_NAME));
                final String price = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_PRICE));
                final String image = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_IMAGE));
                final String artist = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_ARTIST));
                final String contentType = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_CONTENT_TYPE));
                final String releaseDate = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_RELEASE_DATE));
                final String link = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_LINK));
                final String rights = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_RIGHTS));
                final String title = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_TITLE));
                final String id = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_ID));
                final String category = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_CATEGORY));
                final String summary = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_ENTRY_SUMMARY));

                final ImName imName = new Gson().fromJson(name, ImName.class);
                final ImPrice imPrice = new Gson().fromJson(price, ImPrice.class);
                final List<ImImage> imImage = new ArrayList<>(Arrays.asList(new Gson().fromJson(image, ImImage[].class)));
                final ImArtist imArtist = new Gson().fromJson(artist, ImArtist.class);
                final ImContentType imContentType = new Gson().fromJson(contentType, ImContentType.class);
                final ImReleaseDate imReleaseDate = new Gson().fromJson(releaseDate, ImReleaseDate.class);
                final List<EntryLink> entryLink = new ArrayList<>(Arrays.asList(new Gson().fromJson(link, EntryLink[].class)));
                final EntryRights entryRights = new Gson().fromJson(rights, EntryRights.class);
                final EntryTitle entryTitle = new Gson().fromJson(title, EntryTitle.class);
                final EntryId entryId = new Gson().fromJson(id, EntryId.class);
                final EntryCategory entryCategory = new Gson().fromJson(category, EntryCategory.class);
                final EntrySummary entrySummary = new Gson().fromJson(summary, EntrySummary.class);

                final Entry entry = new Entry();

                entry.setImName(imName);
                entry.setImPrice(imPrice);
                entry.setImImage(imImage);
                entry.setImArtist(imArtist);
                entry.setImContentType(imContentType);
                entry.setImReleaseDate(imReleaseDate);
                entry.setLink(entryLink);
                entry.setRights(entryRights);
                entry.setTitle(entryTitle);
                entry.setId(entryId);
                entry.setCategory(entryCategory);
                entry.setSummary(entrySummary);

                mEntryList.add(entry);
            }

        } catch (Exception _error) {

            _error.printStackTrace();
            mException = _error;

        } finally {

            if (mCursor != null && !mCursor.isClosed())
                mCursor.close();

            if (mSQLiteDatabase != null && mSQLiteDatabase.isOpen())
                mSQLiteDatabase.close();

            if (mDatabaseHelper != null)
                mDatabaseHelper.close();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Entry> _entryList) {
        super.onPostExecute(_entryList);

        if (mCallback != null) {

            if (mException == null)
                mCallback.onSuccess(mEntryList);
            else
                mCallback.onFailure(mException);
        }
    }
}

