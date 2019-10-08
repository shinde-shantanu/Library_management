package com.hfad.libraryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        TextView name = findViewById(R.id.name);
        TextView author = findViewById(R.id.author);
        TextView desc = findViewById(R.id.description);
        TextView pages = findViewById(R.id.pages);
        Intent intent = getIntent();
        String n = intent.getStringExtra("name");
        String a = intent.getStringExtra("auth");
        String d = intent.getStringExtra("desc");
        String p = intent.getStringExtra("pages");
        int status = intent.getIntExtra("status",0);
        String user = intent.getStringExtra("user");
        name.setText("Title: "+n);
        author.setText("Author's name: "+a);
        desc.setText("Description: "+d);
        pages.setText("Number of pages: "+p);
        Button issue = findViewById(R.id.issue);
        Button ret = findViewById(R.id.ret);
        if(status==1){
            issue.setEnabled(true);
            ret.setEnabled(false);
        }
        else{
            issue.setEnabled(false);
            if(FirebaseAuth.getInstance().getCurrentUser().equals(user)) {
                ret.setEnabled(true);
            }
        }
    }
    public void issuebook(View view) {
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("books").child(id).child("user").setValue(firebaseUser.getUid());
        databaseReference.child("books").child(id).child("status").setValue(0);
        Toast.makeText(this, "Book Issued!!", Toast.LENGTH_SHORT).show();
    }

    public void returnbook(View view) {
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("books").child(id).child("user").setValue("-1");
        databaseReference.child("books").child(id).child("status").setValue(1);
        Toast.makeText(this, "Book returned!!", Toast.LENGTH_SHORT).show();
    }
}
