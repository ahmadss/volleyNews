package com.volley.aplikasivolley.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.volley.aplikasivolley.model.Kategori;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 31/12/2015.
 */
public class BeritaDAO {

    /**
     * Singleton pattren
     */
    private static BeritaDAO sInstance = null;

    /**
     * Get instance from database access Objek
     * @return
     */
    public static BeritaDAO getsInstance(){
        if (sInstance == null){
            sInstance = new BeritaDAO();
        }

        return sInstance;
    }

    public boolean simpanKategori(Context context, List<Kategori> kategoriList){

        List<Kategori> simpanKategoris = BeritaDAO.getsInstance().getPostKategoriFromDb(context);


        try {
            SQLiteDatabase db = new DbOpenHelper(context).getWritableDatabase();

            db.beginTransaction();

            for (Kategori kategori : kategoriList) {

                boolean isInDb = false;

                for (Kategori kategori1 : simpanKategoris) {
                    if (kategori.getkId().equals(kategori1.getkId())) {
                        isInDb = true;
                    }

//                    Log.i("Banding", "simpanKategori: "+kategori.getkId()+" database id: "+kategori1.getkId()+" name "+kategori1.getkName()+);
                }

                if (!isInDb) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DatabaseConstract.KategoriTable.ID, kategori.getkId());
                    contentValues.put(DatabaseConstract.KategoriTable.KATEGORI_NAME, kategori.getkName());
                    contentValues.put(DatabaseConstract.KategoriTable.KATEGORI_IMAGE, kategori.getkImage());
                    contentValues.put(DatabaseConstract.KategoriTable.AUTHOR, kategori.getAuthor());
                    contentValues.put(DatabaseConstract.KategoriTable.STATUS, kategori.getStatus());

                    db.insert(DatabaseConstract.KategoriTable.TABLE_NAME, null, contentValues);
                }
            }

            db.setTransactionSuccessful();
            db.endTransaction();

            db.close();

        } catch (Exception e){
            return false;
        }

        return true;
    }

    public List<Kategori> getPostKategoriFromDb(Context context){
        SQLiteDatabase database = new DbOpenHelper(context).getWritableDatabase();
        Cursor cursor = database.query(DatabaseConstract.KategoriTable.TABLE_NAME, null, null, null, null, null, null);

        cursor.moveToFirst();

        List<Kategori> kategoriList = new ArrayList<>();

        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex(DatabaseConstract.KategoriTable.ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseConstract.KategoriTable.KATEGORI_NAME));
            String image = cursor.getString(cursor.getColumnIndex(DatabaseConstract.KategoriTable.KATEGORI_IMAGE));
            String author = cursor.getString(cursor.getColumnIndex(DatabaseConstract.KategoriTable.AUTHOR));
            String status = cursor.getString(cursor.getColumnIndex(DatabaseConstract.KategoriTable.STATUS));

            Kategori kategori = new Kategori(id, name, image, author, status);

            kategoriList.add(kategori);

        }

        cursor.close();
        database.close();

        return kategoriList;
    }
}
