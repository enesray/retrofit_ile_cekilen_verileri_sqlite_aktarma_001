package com.raytech.retrofit_ile_veri_cekmek_001.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.raytech.retrofit_ile_veri_cekmek_001.model.Albums;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "albums_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "albums";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_TITLE = "title";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_USER_ID + " INTEGER,"
                    + COLUMN_TITLE + " TEXT"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addAlbums(List<Albums> albums) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (Albums album : albums) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, album.getId());
            values.put(COLUMN_USER_ID, album.getUserId());
            values.put(COLUMN_TITLE, album.getTitle());

            db.insert(TABLE_NAME, null, values);
        }
    }
}
