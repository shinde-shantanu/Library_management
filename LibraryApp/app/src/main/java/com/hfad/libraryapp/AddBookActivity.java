package com.hfad.libraryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void saveBook(View view) {
        EditText bookid = (EditText) findViewById(R.id.bookid);
        EditText bookname = (EditText) findViewById(R.id.bookname);
        EditText author = (EditText) findViewById(R.id.author);
        EditText desc = (EditText) findViewById(R.id.description);
        EditText pages = (EditText) findViewById(R.id.pages);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        String id = bookid.getText().toString().trim();
        Books books;
        books = new Books(bookname.getText().toString().trim(),
                author.getText().toString().trim(),
                desc.getText().toString().trim(),
                pages.getText().toString().trim());
        databaseReference.child("books").child(id).setValue(books);
        int count=0;
        CheckBox checkBox_horror = (CheckBox) findViewById(R.id.checkbox_horror);
        if(checkBox_horror.isChecked()) {
            String c = Integer.toString(count);
            count++;
            databaseReference.child("books").child(id).child("genres").child(c).setValue("Horror");
            databaseReference.child("books").child(id).child("id").setValue(id);
        }

        CheckBox checkBox_fantasy = (CheckBox) findViewById(R.id.checkbox_fantasy);
        if(checkBox_fantasy.isChecked()) {
            String c = Integer.toString(count);
            count++;
            databaseReference.child("books").child(id).child("genres").child(c).setValue("Fantasy");
        }

        CheckBox checkBox_Youngadults = (CheckBox) findViewById(R.id.young_adults);
        if(checkBox_Youngadults.isChecked()) {
            String c = Integer.toString(count);
            count++;
            databaseReference.child("books").child(id).child("genres").child(c).setValue("Young Adults");
            databaseReference.child("books").child(id).child("id").setValue(id);
        }

        CheckBox checkBox_childrens = (CheckBox) findViewById(R.id.checkbox_childrens);
        if(checkBox_childrens.isChecked()) {
            String c = Integer.toString(count);
            count++;
            databaseReference.child("books").child(id).child("genres").child(c).setValue("Childrens");
        }

        CheckBox checkBox_classic = (CheckBox) findViewById(R.id.checkbox_classic);
        if(checkBox_classic.isChecked()) {
            String c = Integer.toString(count);
            count++;
            databaseReference.child("books").child(id).child("genres").child(c).setValue("Classic");
            databaseReference.child("books").child(id).child("id").setValue(id);
        }

        CheckBox checkBox_crime = (CheckBox) findViewById(R.id.checkbox_crime);
        if(checkBox_crime.isChecked()) {
            String c = Integer.toString(count);
            count++;
            databaseReference.child("books").child(id).child("genres").child(c).setValue("Crime");
            databaseReference.child("books").child(id).child("id").setValue(id);
        }

        CheckBox checkBox_fiction = (CheckBox) findViewById(R.id.checkbox_fiction);
        if(checkBox_fiction.isChecked()) {
            String c = Integer.toString(count);
            count++;
            databaseReference.child("books").child(id).child("genres").child(c).setValue("Fiction");
            databaseReference.child("books").child(id).child("id").setValue(id);
        }

        CheckBox checkBox_mys = (CheckBox) findViewById(R.id.checkbox_mystery);
        if(checkBox_mys.isChecked()) {
            String c = Integer.toString(count);
            count++;
            databaseReference.child("books").child(id).child("genres").child(c).setValue("Mystery");
            databaseReference.child("books").child(id).child("id").setValue(id);
        }

        databaseReference.child("books").child(id).child("count_genre").setValue(count);
        databaseReference.child("books").child(id).child("status").setValue(1);
        databaseReference.child("books").child(id).child("user").setValue("-1");
        Toast.makeText(this, "Book added to database", Toast.LENGTH_SHORT).show();
    }

}
