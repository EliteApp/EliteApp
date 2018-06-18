package com.einhard_gymnasium.eliteapp.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


/**
 * Klasse, die die Datenbank verwaltet, welche alle User, Passwörter, Namen und Klassen beinhaltet
 *
 * erstellt von Nick
 */
public class UserDatabaseHelper extends SQLiteOpenHelper{

    private int x;

    //SQL Befehl zum erstellen der Tabelle
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + FeedEntry.TABLE_NAME + " ( "
            + FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FeedEntry.COLUMN_NAME_USER
            + " TEXT, " + FeedEntry.COLUMN_NAME_PW + " TEXT, " + FeedEntry.COLUMN_NAME_CLASS + " INTEGER, " + FeedEntry.COLUMN_NAME_NAME + " TEXT )";

    //SQL Query um zu überprüfen ob ein Benuter in der Datenbank ist. Die Fragezeichen werden von einer Methode ersetzt
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

    /**
     * Fügt die Beispieldaten in die Datenbank ein
     * @param sqLiteDatabase
     */
    private void insertValues(SQLiteDatabase sqLiteDatabase) {
        //Beispiel Daten
        String[] userValues = {"niclig", "svewir", "matbei", "fabhae"};
        String[] pwValues = {"12345", "abcde", "12abc", "passwort"};
        int[] classValues = {11, 9, 6, 12};
        String[] nameValues = {"Nick Ligmann", "Sven Wirtz", "Mathis Beißner", "Fabian Haese"};

        for (int i = 0; i < userValues.length; i++){
            //Packt die Daten in richtiger Reihenfolge in die jeweiligen Spalten
            ContentValues content = new ContentValues();
            content.put(FeedEntry.COLUMN_NAME_USER, userValues[i]);
            content.put(FeedEntry.COLUMN_NAME_PW, pwValues[i]);
            content.put(FeedEntry.COLUMN_NAME_CLASS, classValues[i]);
            content.put(FeedEntry.COLUMN_NAME_NAME, nameValues[i]);
            //Fügt den Inhalt der Tabelle hinzu
            sqLiteDatabase.insert(FeedEntry.TABLE_NAME, null, content);
        }
    }

    /**
     * checkt ob ein Nutzer in der Datenbank ist und gibt ggf dessen Daten zurück
     * @param user Username, welcher beim anmelden angegeben wird
     * @param pw Passwort, welches beim anmelden angegeben wird
     * @return Gibt ein Array zurück, welches den Nutzernamen, das Passwort, die Klasse und den vollen Namen enhält
     */
    public String[] check(String user, String pw){
        //Array fürs Ergebnis
        String[] resolution = new String[4];
        //Erstellt einen "Zeiger" der auf das Ergebnis der Query verweist
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(SQL_QUERY_TEXT, new String[]{user,pw});
        if((res != null) && (res.getCount() > 0)) {
            //Speichert die Indexe der Spalten in einem Array
            int[] indexColumns = {res.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_USER), res.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_PW),
                    res.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_CLASS), res.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_NAME)};

            res.moveToFirst();
            for (int i = 0; i < 4; i++) {
                //geht die vier Spalten durch und fügt das Ergebnis der Query dem Ergebnis-Array hinzu
                resolution[i] = res.getString(indexColumns[i]);
            }
            //gibt das Array zurück
            return resolution;
        } else {
            //falls kein user mit dem Benutzernamen und Passwort vorhanden ist, wird null zurück gegeben
            return null;
        }
    }
}
