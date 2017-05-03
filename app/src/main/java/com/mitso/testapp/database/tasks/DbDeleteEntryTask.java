package com.mitso.testapp.database.tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.database.DatabaseHelper;
import com.mitso.testapp.models.Entry;

public class DbDeleteEntryTask extends AsyncTask<Void, Void, Void> {

    public String               LOG_TAG = Constants.DB_DELETE_ENTRY_TASK_LOG_TAG;

    public interface Callback{

        void onSuccess();
        void onFailure(Throwable _error);
    }

    private DatabaseHelper      mDatabaseHelper;
    private Entry               mEntry;
    private Callback            mCallback;
    private Exception           mException;
    private SQLiteDatabase      mSQLiteDatabase;

    public DbDeleteEntryTask(Context _context, Entry _entry) {

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

            mSQLiteDatabase.delete(DatabaseHelper.DATABASE_TABLE,
                    DatabaseHelper.COLUMN_ENTRY_ID + DatabaseHelper.EQUALS_SIGN + DatabaseHelper.QUESTION_MARK,
                    new String[] { new Gson().toJson(mEntry.getId()) });

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
