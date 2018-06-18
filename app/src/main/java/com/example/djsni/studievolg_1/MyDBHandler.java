package com.example.djsni.studievolg_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Vakken.db";
//     tabel voor de vakken
    private static final String TABLE_NAME = "Vak";
    private static final int DATABASE_VERSION = 1;

//    om de naam op te slaan
    private static final String TABLE_NAME_2 = "Naam";
    public static final String NAAM = "naam";
//    om de ec op te slaan
    private static final String TABLE_NAME_3 = "Studiepunten";
    public static final String EC = "EC";

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
        System.out.println("dit is een test" );


        String CREATE_NAAM_TABLE = "CREATE TABLE " + TABLE_NAME_2 + "("
                + NAAM + " TEXT"
                + ")";


        String CREATE_EC_TABLE = "CREATE TABLE " + TABLE_NAME_3 + "("
                + EC + " TEXT"
                + ")";

        System.out.println("dit is een test"+ CREATE_NAAM_TABLE);

        db.execSQL(CREATE_NAAM_TABLE);
        db.execSQL(CREATE_VAK_TABLE);
        db.execSQL(CREATE_EC_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
            System.out.println("De data word opgeslagen");
            }

        catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            db.endTransaction();
        }


    };


    public void addNaam(String snummer) {

        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(NAAM, snummer);
            db.insertOrThrow(TABLE_NAME_2, null, values);
            db.setTransactionSuccessful();
            System.out.println("De naam word opgeslagen");
        }

        catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            db.endTransaction();
        }


    };
    public void addEC(int studiepunt) {

        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(EC, studiepunt);
            db.insertOrThrow(TABLE_NAME_3, null, values);
            db.setTransactionSuccessful();
            System.out.println("De EC word opgeslagen");
        }

        catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            db.endTransaction();
        }


    };

    public boolean checkIfexists(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;
        String sql = "SELECT * FROM "+ TABLE_NAME ;
        cursor = db.rawQuery(sql ,null);
        int aantal = cursor.getCount();

        if (aantal == 0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean checkIfexistNaam(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;
        String sql = "SELECT * FROM "+ TABLE_NAME_2 ;
        cursor = db.rawQuery(sql ,null);
        int aantal = cursor.getCount();

        if (aantal == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<vak> getAll() {

        SQLiteDatabase db = getWritableDatabase();
        ArrayList<vak> Vaklijst = new ArrayList<>();

        Cursor cursor  = db.rawQuery("SELECT * FROM "+ TABLE_NAME + ";", null);

        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                System.out.println("name: "+ cursor.getString(cursor.getColumnIndex(VAK_NAME)));
                System.out.println("ec: "+ cursor.getString(cursor.getColumnIndex(VAK_EC)));
                System.out.println("desc: "+ cursor.getString(cursor.getColumnIndex(VAK_DESC)));
                System.out.println("id: "+ cursor.getString(cursor.getColumnIndex(VAK_ID)));

                Vaklijst.add(new vak(
                        cursor.getString(cursor.getColumnIndex(VAK_NAME)) ,
                        cursor.getString(cursor.getColumnIndex(VAK_EC)) ,
                        cursor.getString(cursor.getColumnIndex(VAK_DESC))  ,
                        cursor.getInt(cursor.getColumnIndex(VAK_ID))));
                cursor.moveToNext();

            }

        }

    return Vaklijst;

    }

    public  String getNaam() {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor  = db.rawQuery("SELECT naam FROM "+ TABLE_NAME_2 + ";", null);
        String naam = "";

        System.out.println("name: ");
        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                naam = cursor.getString(cursor.getColumnIndex(NAAM));

                return naam;
            }
        }
        return naam;
    }
    public  String getEC() {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor  = db.rawQuery("SELECT EC FROM "+ TABLE_NAME_3 + ";", null);
        String ec = "";

        System.out.println("name: ");
        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                ec = cursor.getString(cursor.getColumnIndex(EC));

                return ec;
            }
        }
        return ec;
    }
    public  int telEC() {
        SQLiteDatabase db = getWritableDatabase();

        Cursor c  = db.rawQuery("SELECT SUM(EC) FROM "+ TABLE_NAME_3 + ";", null);
        int amount = 0;
        if(c.moveToFirst())
            amount = c.getInt(0);
        else
            amount = -1;
        c.close();

        return amount;
    }
    public  void deleteEC(String modulecode) {
        String module = modulecode;
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME +
                " WHERE "+ VAK_NAME +" = "+ "'" + module + "'");

    }
    public  void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME +
                " WHERE "+ VAK_NAME +" != "+ "'" + "waarde" + "'");

        db.execSQL("DELETE FROM " + TABLE_NAME_3 +
                " WHERE "+ EC +" != "+ "'" + 1 + "'");

    }
}
