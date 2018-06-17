package com.einhard_gymnasium.eliteapp.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

public class UserDatabaseHelper extends SQLiteOpenHelper{

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + FeedEntry.TABLE_NAME + " ( "
            + FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FeedEntry.COLUMN_NAME_USER
            + " TEXT, " + FeedEntry.COLUMN_NAME_PW + " TEXT, " + FeedEntry.COLUMN_NAME_CLASS + " INTEGER, " + FeedEntry.COLUMN_NAME_NAME + " TEXT )";

    private static final String SQL_QUERY_TEXT = "SELECT * FROM " + FeedEntry.TABLE_NAME + " WHERE (" + FeedEntry.COLUMN_NAME_USER + " = ?  AND "
            + FeedEntry.COLUMN_NAME_PW + " = ? )";
    /**
     * Innere Klasse, welche den Datenbanknamen, Tabellennamen, und die Spaltennamen verwaltet (damits übersichtlicher ist)
     */
    public static class FeedEntry implements BaseColumns{
        private static final String DATABASE_NAME = "UserList.db";
        private static final String TABLE_NAME = "UserList";
        private static final String COLUMN_NAME_USER = "username";
        private static final String COLUMN_NAME_PW = "password";
        private static final String COLUMN_NAME_CLASS = "class";
        private static final String COLUMN_NAME_NAME = "Name";
    }

    /**
     * Konstruktor der Klasse. Führt nur den Konstruktor der Oberklasse aus
     * @param context
     */

    public UserDatabaseHelper(Context context) {
        super(context, FeedEntry.DATABASE_NAME, null, 1);
    }

    /**
     * Wird beim erstellen der Datenbank ausgeführt
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
        this.insertValues(sqLiteDatabase);
    }

    /**
     * Wird aufgerufen wenn etwas an der Datenbank geändert werden soll
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }



    private void insertValues(SQLiteDatabase sqLiteDatabase) {
        //Beispiel Daten
        String[] userValues = {"niclig", "svewir", "matbei", "fabhae"};
        String[] pwValues = {"12345", "abcde", "12abc", "passwort"};
        int[] classValues = {11, 9, 6, 12};
        String[] nameValues = {"Nick Ligmann", "Sven Wirts", "Mathis Beißner", "Fabian Haese"};

        for (int i = 0; i < userValues.length; i++){
            ContentValues content = new ContentValues();
            content.put(FeedEntry.COLUMN_NAME_USER, userValues[i]);
            content.put(FeedEntry.COLUMN_NAME_PW, pwValues[i]);
            content.put(FeedEntry.COLUMN_NAME_CLASS, classValues[i]);
            content.put(FeedEntry.COLUMN_NAME_NAME, nameValues[i]);
            sqLiteDatabase.insert(FeedEntry.TABLE_NAME, null, content);
        }
    }

    public String[] check(String user, String pw){

        String[] resolution = new String[4];

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(SQL_QUERY_TEXT, new String[]{user,pw});

        int[] indexColumns = {res.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_USER), res.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_PW),
                res.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_CLASS), res.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_NAME)};

        res.moveToFirst();
        for(int i = 0; i < 4 ; i++){
            resolution[i] = res.getString(indexColumns[i]);
        }

        return resolution;
    }
}
