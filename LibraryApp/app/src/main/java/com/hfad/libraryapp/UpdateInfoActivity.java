package com.hfad.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void saveInfo(View view) {
        EditText name = (EditText) findViewById(R.id.cus_name);
        EditText add = (EditText) findViewById(R.id.cus_add);
        EditText age = (EditText) findViewById(R.id.cus_age);
        EditText date = (EditText) findViewById(R.id.joindate);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference();
        User user = new User(name.getText().toString().trim(),
                add.getText().toString().trim(),
                age.getText().toString().trim(),
                date.getText().toString().trim(),
                "test",
                "test1",
                null);
        databaseReference.child("Users").child(firebaseUser.getUid()).setValue(user);
        Toast.makeText(this, "Information Updated", Toast.LENGTH_SHORT).show();
    }

}