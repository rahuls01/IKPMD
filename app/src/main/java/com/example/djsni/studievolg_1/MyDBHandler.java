package com.example.djsni.studievolg_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Vakken.db";
    private static final String TABLE_NAME = "Vak";
    private static final int DATABASE_VERSION = 1;

    public static final String VAK_ID = "id";
    public static final String VAK_SNUMMER = "studentnummer";
    public static final String VAK_EC = "ec";
    public static final String VAK_DESC = "description";
    public static final String VAK_NAME = "name";

    private static MyDBHandler sInstance;

    public static synchronized MyDBHandler getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new MyDBHandler(context);
        }
        return sInstance;

    }


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_VAK_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + VAK_ID + " INTEGER,"
                + VAK_SNUMMER + " INTEGER,"
                + VAK_EC + " INTEGER,"
                + VAK_NAME + " TEXT,"
                + VAK_DESC + " TEXT"
                + ")";

        db.execSQL(CREATE_VAK_TABLE);
    }

    public void addVak(vak vak, String snummer) {

        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();

            values.put(VAK_ID, vak.getid());
            values.put(VAK_SNUMMER, snummer);
            values.put(VAK_EC, vak.getec());
            values.put(VAK_NAME, vak.getName());
            values.put(VAK_DESC, vak.getdescription());

            db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
            System.out.println("geert het werkt");
            }

        catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            db.endTransaction();
        }


    };


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
