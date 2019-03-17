package com.hash.sqlitedemo.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hash.sqlitedemo.bean.Person;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

/**
 * Created by HashWaney on 2019/3/17.
 */

public class FeedReaderDbManager {
    private FeedReaderDbHelper helper;

    private SQLiteDatabase db;

    public FeedReaderDbManager(Context context) {
        helper = new FeedReaderDbHelper(context);
        db = helper.getWritableDatabase();
    }

    //添加操作
    public void addData(List<Person> dataList) {
        try {
            //开启事物,事件回滚
            db.beginTransaction();
            for (Person p : dataList) {
                //插入之前先进行查询是否有该条记录,如果有则不插入
//                if (cursor.getCount() == 0) {
//                    //插入数据
//                    System.err.println("/////////插入数据表//////");
//
//                } else {
//                    System.err.println("数据库中存在该成员");
//                }

                Cursor query = db.query(true, FeedReaderContract.FeedEntry.TABLE_NAME,
                        new String[]{FeedReaderContract.FeedEntry.COLUMN_NAME_PERSON_ID},
                        FeedReaderContract.FeedEntry.COLUMN_NAME_PERSON_ID + " = ?",
                        new String[]{p.getPersonId()}, null, null, null, null

                );
                System.err.println("是否有符合查询条件的数据:" + query.getCount());
                if (query.getCount() == 0) {
                    db.execSQL("insert into person values(null,?,?,?,?)", new Object[]{p.getPersonId(), p.getAge(),
                            p.getPersonName(), p.getCity()}
                    );
                } else {
                    System.err.println("数据库中已经存在该条数据了,不在进行插入操作");
                }

            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("error :" + e.getMessage());

        } finally {
            db.endTransaction();
        }

    }


    //更新操作
    public void updateAge(Person person) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("age", person.getAge());
        db.update(FeedReaderContract.FeedEntry.TABLE_NAME, contentValues,
                "person_name=?", new String[]{person.getPersonName()}
        );

    }

    //查询操作
    public List<Person> queryAllPerson() {
        ArrayList<Person> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            System.err.println("/////查询数据表//////");
            Person person = new Person();
            person.setPersonId(cursor.getString(cursor.getColumnIndex("person_id")));
            person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            person.setPersonName(cursor.getString(cursor.getColumnIndex("person_name")));
            person.setCity(cursor.getString(cursor.getColumnIndex("city")));
            list.add(person);
        }
        cursor.close();
        return list.isEmpty() ? null : list;


    }

    //删除操作
    public void deletePerson(Person person) {
        System.err.println("此处模拟清空数据库操作");
        db.execSQL("delete from person where age=12 ");
        db.execSQL("delete from person where age=15");
        db.execSQL("delete from person where age=10");
        db.execSQL("delete from person where age=14");
    }


    //关闭数据库连接
    public void closeDb() {
        db.close();
    }


}
