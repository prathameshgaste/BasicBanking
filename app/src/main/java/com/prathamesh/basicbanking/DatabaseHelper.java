package com.prathamesh.basicbanking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9876543210,'Prathamesh',1000.00,'user1@gmail.com','16xxxxxx01','BKID3333214')");
        db.execSQL("insert into user_table values(9876543211,'Aniket',1000.00,'user2@gmail.com','16xxxxxx02','BKID3333214')");
        db.execSQL("insert into user_table values(9876543212,'Yash',1000.00,'user3@gmail.com','16xxxxxx03','BKID3333214')");
        db.execSQL("insert into user_table values(9876543213,'Kayyum',1000.00,'user4@gmail.com','16xxxxxx04','BKID3333214')");
        db.execSQL("insert into user_table values(9876543214,'Aditya',1000.00,'user5@gmail.com','16xxxxxx05','BKID3333214')");
        db.execSQL("insert into user_table values(9876543215,'Rushikesh',1000.00,'user6@gmail.com','16xxxxxx06','BKID3333214')");
        db.execSQL("insert into user_table values(9876543216,'Rohan',1000.00,'user7@gmail.com','16xxxxxx07','BKID3333214')");
        db.execSQL("insert into user_table values(9876543217,'Tushar',1000.00,'user8@gmail.com','16xxxxxx08','BKID3333214')");
        db.execSQL("insert into user_table values(9876543218,'Swarup',1000.00,'user9@gmail.com','16xxxxxx09','BKID3333214')");
        db.execSQL("insert into user_table values(9876543219,'Shivam',1000.00,'user10@gmail.com','16xxxxxx10','BKID3333214')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
