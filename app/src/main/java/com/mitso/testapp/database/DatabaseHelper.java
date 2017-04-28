package com.mitso.testapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String      DATABASE_NAME       = "entries_database.db";
    public static final int         DATABASE_VERSION    = 1;
    public static final String      DATABASE_TABLE      = "entries_table";

    public static final String      COLUMN_DATABASE_ID              = "_id";
    public static final String      COLUMN_ENTRY_NAME               = "name";
    public static final String      COLUMN_ENTRY_PRICE              = "price";
    public static final String      COLUMN_ENTRY_IMAGE              = "image";
    public static final String      COLUMN_ENTRY_ARTIST             = "artist";
    public static final String      COLUMN_ENTRY_CONTENT_TYPE       = "content_type";
    public static final String      COLUMN_ENTRY_RELEASE_DATE       = "release_date";
    public static final String      COLUMN_ENTRY_LINK               = "link";
    public static final String      COLUMN_ENTRY_RIGHTS             = "rights";
    public static final String      COLUMN_ENTRY_TITLE              = "title";
    public static final String      COLUMN_ENTRY_ID                 = "id";
    public static final String      COLUMN_ENTRY_CATEGORY           = "category";
    public static final String      COLUMN_ENTRY_SUMMARY            = "summary";

    public static final String      CREATE_TABLE            = "create table";
    public static final String      INTEGER_PRIMARY_KEY     = "integer primary key autoincrement";
    public static final String      TEXT                    = "text not null";

    public static final String      COMMA               = ",";
    public static final String      SPACE               = " ";
    public static final String      PARENTHESES_IN      = "(";
    public static final String      PARENTHESES_OUT     = ")";

    public static final String      EQUALS      = "=";

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static DatabaseHelper instance;

    private DatabaseHelper(Context _context) {

        super(_context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getDatabaseHelper(Context _context) {

        if (instance == null)
            instance = new DatabaseHelper(_context);

        return instance;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(SQLiteDatabase _db) {

        _db.execSQL(
                CREATE_TABLE + SPACE + DATABASE_TABLE + SPACE +
                        PARENTHESES_IN +
                        COLUMN_DATABASE_ID + SPACE + INTEGER_PRIMARY_KEY + COMMA + SPACE +
                        COLUMN_ENTRY_NAME + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_PRICE + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_IMAGE + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_ARTIST + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_CONTENT_TYPE + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_RELEASE_DATE + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_LINK + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_RIGHTS + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_TITLE + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_ID + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_CATEGORY + SPACE + TEXT + COMMA + SPACE +
                        COLUMN_ENTRY_SUMMARY + SPACE + TEXT +
                        PARENTHESES_OUT
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {

    }
}
