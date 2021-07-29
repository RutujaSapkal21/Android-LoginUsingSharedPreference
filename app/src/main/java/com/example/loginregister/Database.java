package com.example.loginregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public  static final int Data_Base_VERSION=1;
    public static final String DATABASE_NAME="Data";
    public static final String TABLE_NAME="Dataa";
    public final String Username="Username";
    public final String Mobile="Mobile";
    public final String email="email";
    public final String Password="Password";

    public Database(@Nullable Context context) {
        super(context,DATABASE_NAME, null, Data_Base_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table="CREATE TABLE "+TABLE_NAME+"("+Username+" VARCHAR(100),"+
                Mobile+" VARCHAR(100),"+email+" VARCHAR(100),"+Password+" VARCHAR(100)"+")";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void adduser(User user){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(Username,user.getUsername());
        contentValues.put(Mobile,user.getMobile());
        contentValues.put(email,user.getEmail());
        contentValues.put(Password,user.getPassword());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
    }

    public void updateuser(User user){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(Username,user.getUsername());
        contentValues.put(Mobile,user.getMobile());
        contentValues.put(email,user.getEmail());

        sqLiteDatabase.update(TABLE_NAME,contentValues,Mobile+"=?",new String[]{user.getMobile()});
        sqLiteDatabase.close();
    }

    public List<User> viewUser(){

        List<User> users=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String sqlquery="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=sqLiteDatabase.rawQuery(sqlquery,null);

        if (cursor.moveToFirst()){
            do {
                User user=new User();
                user.setUsername(cursor.getString(0));
                user.setMobile(cursor.getString(1));
                user.setEmail(cursor.getString(2));

            }while (cursor.moveToNext());
        }
        return users;
    }

    public void deleteStudent(String name1){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,Username+"=?",new String[]{name1});
        sqLiteDatabase.close();
    }

    public boolean loginUser(User user){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String select="SELECT * FROM "+TABLE_NAME+" WHERE "+Mobile+" =? AND "+Password+" =?";
        Cursor cursor=sqLiteDatabase.rawQuery(select,new String[]{user.getMobile(),user.getPassword()});
        Log.d("cursor",cursor.toString());

        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }

    public List<User> getUserDetails(User user){
        List<User> userList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String select="SELECT * FROM "+TABLE_NAME+" WHERE "+Mobile+" =?";
        Cursor cursor=sqLiteDatabase.rawQuery(select,new String[]{user.getMobile()});

        if (cursor!=null){
            cursor.moveToFirst();
            User user1=new User();
            user1.setUsername(cursor.getString(0));
            user1.setEmail(cursor.getString(2));
            user1.setMobile(cursor.getString(1));
            userList.add(user1);
        }
        return userList;

    }
}
