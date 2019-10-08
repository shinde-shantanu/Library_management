package com.hfad.libraryapp;

/**
 * Created by Shantanu Shinde on 14-07-2018.
 */

public class Books {
    public String name;
    public String author;
    public String desc;
    public String pages;
    public int count_genre;

    public Books(String name, String author, String desc, String pages) {
        this.name = name;
        this.author = author;
        this.desc = desc;
        this.pages = pages;
    }
}
