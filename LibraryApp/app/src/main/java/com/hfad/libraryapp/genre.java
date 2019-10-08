package com.hfad.libraryapp;

/**
 * Created by Shantanu Shinde on 17-07-2018.
 */

public class genre {
    private String name;
    private int imageResourceId;

    public static final genre[] Genre = {
            new genre("Horror", R.drawable.horror),
            new genre("Fantasy", R.drawable.fantasy),
            new genre("Young Adults", R.drawable.youngadult),
            new genre("Childrens", R.drawable.childrens),
            new genre("Classics", R.drawable.classic),
            new genre("Crime", R.drawable.crime),
            new genre("Fiction", R.drawable.fiction),
            new genre("Mystery", R.drawable.mystery)
    };

    private genre(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }
    public String getName() {
        return name;
    }
    public int getImageResourceId() {
        return imageResourceId;
    }
}
