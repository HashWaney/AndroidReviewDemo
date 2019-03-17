package com.hash.sqlitedemo.bean;

/**
 * Created by HashWaney on 2019/3/17.
 */

public class Person {
    private int age;
    private String personName;
    private String personId;
    private String city;

    private int _Id;


    public Person() {
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", personName='" + personName + '\'' +
                ", personId='" + personId + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
