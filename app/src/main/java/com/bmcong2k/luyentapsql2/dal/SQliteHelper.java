package com.bmcong2k.luyentapsql2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.bmcong2k.luyentapsql2.model.BaiHat;
import com.bmcong2k.luyentapsql2.model.Sort;

import java.util.ArrayList;
import java.util.List;

public class SQliteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "baihat.db";
    private static int DATABASE_VERSION = 1;

    public SQliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create database only 1
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE baihat(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenBH TEXT, " +
                "tenCasi TEXT," +
                "album TEXT," +
                "theLoai TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //getALlItem order by time

    public List<BaiHat> getAll() {
        List<BaiHat> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("baihat", null, null, null,
                null, null, null);

        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String tenBaiHat = rs.getString(1);
            String tenCasi = rs.getString(2);
            String album = rs.getString(3);
            String theLoai = rs.getString(4);
            list.add(new BaiHat(id, tenBaiHat, tenCasi, album, theLoai));
        }
        return list;
    }
//
    public long addItem(BaiHat i) {
        ContentValues values = new ContentValues();
        values.put("tenBH", i.getTenBH());
        values.put("tenCasi", i.getTenCasi());
        values.put("album", i.getAlbum());
        values.put("theLoai", i.getTheLoai());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("baihat", null, values);
    }
////
////    //get item by date
////    public List<Item> getByDate(String date) {
////        List<Item> list = new ArrayList<>();
////        String whereClause = "date like ?";
////        String[] whereAgrg = {date};
////        SQLiteDatabase st = getReadableDatabase();
////        Cursor rs = st.query("items", null,
////                whereClause, whereAgrg, null, null, null);
////
////        while (rs != null && rs.moveToNext()) {
////            int id = rs.getInt(0);
////            String title = rs.getString(1);
////            String category = rs.getString(2);
////            String price = rs.getString(3);
////            list.add(new Item(id, title, category, price, date));
////        }
////        return list;
////    }
////
//    // Update
    public int update(BaiHat i){
        ContentValues values = new ContentValues();
        values.put("tenBH", i.getTenBH());
        values.put("tenCasi", i.getTenCasi());
        values.put("album", i.getAlbum());
        values.put("theLoai", i.getTheLoai());

        String whereClause = "id=?";
        String[] whereAgrg = {Integer.toString(i.getId())};

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("baihat", values, whereClause, whereAgrg);
    }
//    //Delete
    public int delete(int id){
        String whereClause = "id=?";
        String[] whereAgrg = {Integer.toString(id)};
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("baihat", whereClause, whereAgrg);
    }
////   // Search name
////    public List<Item> searchByTitle(String key) {
////        List<Item> list = new ArrayList<>();
////        //select * from items where title like %key%
////        String whereClause = "title like ?";
////        String[] whereAgrg = {"%" + key + "%"};
////        SQLiteDatabase st = getReadableDatabase();
////        Cursor rs = st.query("items", null,
////                whereClause, whereAgrg, null, null, null);
////
////        while (rs != null && rs.moveToNext()) {
////            int id = rs.getInt(0);
////            String title = rs.getString(1);
////            String category = rs.getString(2);
////            String price = rs.getString(3);
////            String date = rs.getString(4);
////            list.add(new Item(id, title, category, price, date));
////        }
////        return list;
////    }
////
    public List<BaiHat> searchByAlbum(String album) {
        List<BaiHat> list = new ArrayList<>();
        //SELECT * FROM Khachhang where Thanhpho = "Bắc Ninh" ORDER BY MaBuudien DESC
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor rs = db.rawQuery("SELECT * FROM baihat WHERE album = ? ",
                new String[]{album});

        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String tenBH = rs.getString(1);
            String tenCaSi = rs.getString(2);
            String alum = rs.getString(3);
            String tl = rs.getString(4);
            list.add(new BaiHat(id, tenBH, tenCaSi, alum, tl));
        }
        return list;
    }

    public List<Sort> sortByTheLoai() {
        List<Sort> list = new ArrayList<>();
        //SELECT * FROM Khachhang where Thanhpho = "Bắc Ninh" ORDER BY MaBuudien DESC
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor rs = db.rawQuery("SELECT count(theLoai) AS dem, theLoai\n" +
                        "FROM baihat\n" +
                        "GROUP BY theLoai\n" +
                        "ORDER BY count(theLoai) DESC;",
                new String[]{});

        while (rs != null && rs.moveToNext()) {
            int sl = rs.getInt(0);
            String tl = rs.getString(1);
            Sort s = new Sort(sl, tl);
            list.add(s);
        }
        return list;
    }

////      // serch from to
//    public List<Book> searchByPriceFromTo(String from, String to) {
//        List<Book> list = new ArrayList<>();
//        //select * from items where title like %key%
//        String whereClause = "gia BETWEEN ? AND ?";
//        String[] whereAgrg = {from.trim(), to.trim()};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("book", null,
//                whereClause, whereAgrg, null, null, null);
//
//        while (rs != null && rs.moveToNext()) {
//            int id = rs.getInt(0);
//            String ten = rs.getString(1);
//            String nxb = rs.getString(2);
//            String nhaXB = rs.getString(3);
//            String gia = rs.getString(4);
//            list.add(new Book(id, ten, nxb, nhaXB, gia));
//        }
//        return list;
//    }
}
