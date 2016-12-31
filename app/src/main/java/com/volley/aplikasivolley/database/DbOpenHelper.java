package com.volley.aplikasivolley.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ahmad on 31/12/2015.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
//    private static final String INTEGER_TYPE = " INTEGER";
    private static final String KOMA = ", ";

    /**
     * SQL CREATE TABLE SENTENCE
     */

    private static final String CREATE_KATEGORI_TABLE = "CREATE TABLE "
            + DatabaseConstract.KategoriTable.TABLE_NAME + " ("
            + DatabaseConstract.KategoriTable.ID + TEXT_TYPE + KOMA
            + DatabaseConstract.KategoriTable.KATEGORI_NAME + TEXT_TYPE + KOMA
            + DatabaseConstract.KategoriTable.KATEGORI_IMAGE + TEXT_TYPE + KOMA
            + DatabaseConstract.KategoriTable.AUTHOR + TEXT_TYPE + KOMA
            + DatabaseConstract.KategoriTable.STATUS + TEXT_TYPE + " )";

    public static final String DROP_KATEGORI_TABLE = "DROP TABLE IF EXISTS "+DatabaseConstract.KategoriTable.TABLE_NAME;

    public DbOpenHelper(Context context){
        super(context, DatabaseConstract.DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_KATEGORI_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_KATEGORI_TABLE);
        onCreate(db);
    }
}
