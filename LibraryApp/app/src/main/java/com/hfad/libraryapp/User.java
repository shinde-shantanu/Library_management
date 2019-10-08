package com.hfad.libraryapp;

import java.lang.ref.SoftReference;

/**
 * Created by Shantanu Shinde on 15-07-2018.
 */

public class User {
    public String name;
    public String add;
    public String age;
    public String joindate;
    public String b1;
    public String b2;
    public String[] books_read;

    public User(String name, String add, String age, String joindate, String b1, String b2, String[] books_read) {
        this.name = name;
        this.add = add;
        this.age = age;
        this.joindate = joindate;
        this.b1 = b1;
        this.b2 = b2;
        this.books_read = books_read;
    }

    public User() {
    }
}
