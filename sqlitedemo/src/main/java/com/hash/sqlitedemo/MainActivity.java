package com.hash.sqlitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hash.sqlitedemo.bean.Person;
import com.hash.sqlitedemo.sqlite.FeedReaderDbHelper;
import com.hash.sqlitedemo.sqlite.FeedReaderDbManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FeedReaderDbManager dbManager;

    private static final String Tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new FeedReaderDbManager(this);


    }

    //删除
    public void deleteFun(View v) {
        Person person = new Person();
        person.setAge(10);
        dbManager.deletePerson(person);

    }

    //更改
    public void updateFun(View view) {
        Person person = new Person();
        person.setAge(155);
        person.setPersonName("kk");
        dbManager.updateAge(person);


    }

    //插入
    public void insertFun(View view) {
        List<Person> data = new ArrayList<>();
        Person person1 = new Person();
        person1.setAge(12);
        person1.setPersonName("jone");
        person1.setPersonId("123");
        person1.setCity("jx");
        data.add(person1);


        Person person2 = new Person();
        person2.setAge(14);
        person2.setPersonName("kone");
        person2.setPersonId("354");
        person2.setCity("hongkong");
        data.add(person2);

        Person person3 = new Person();
        person3.setAge(15);
        person3.setPersonName("kk");
        person3.setPersonId("111");
        person3.setCity("sz");
        data.add(person3);


        Person person4 = new Person();
        person4.setAge(10);
        person4.setPersonName("nick");
        person4.setPersonId("1144");
        person4.setCity("gz");
        data.add(person4);


        dbManager.addData(data);
    }

    //查询
    public void queryFun(View view) {
        List<Person> people = dbManager.queryAllPerson();
        if (people == null) {
            Toast.makeText(this,"暂未查询到数据.",Toast.LENGTH_LONG).show();
        } else {
            Log.e(Tag, "person:" + people.toString());

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDb();
    }
}
