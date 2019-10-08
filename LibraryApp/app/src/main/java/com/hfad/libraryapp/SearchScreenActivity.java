package com.hfad.libraryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchScreenActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    ArrayList<String> n = new ArrayList<String>();
    ArrayList<String> a = new ArrayList<String>();
    ArrayList<String> desc = new ArrayList<String>();
    ArrayList<String> pages = new ArrayList<String>();
    ArrayList<String> id = new ArrayList<String>();
    ArrayList<Integer> status = new ArrayList<Integer>();
    ArrayList<String> user = new ArrayList<String>();
    int count=0;
    String g;
    Intent intent;
    int pos;
    String search_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();
        pos = intent.getIntExtra("pos",-1);
        if(pos==-1) {
            search_input = intent.getStringExtra("name");
        }
        if(pos!=-1) {
            switch (pos) {
                case 0:
                    g = "Horror";
                    break;
                case 1:
                    g = "Fantasy";
                    break;
                case 2:
                    g = "Young Adults";
                default:
                    Toast.makeText(this, "No books found!!!", Toast.LENGTH_SHORT).show();
            }
        }
        databaseReference.child("books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(pos!=-1) {
                        int cg = snapshot.child("count_genre").getValue(int.class);
                        for(int i =0;i<cg;i++) {
                            String j = Integer.toString(i);
                            String gen = snapshot.child("genres").child(j).getValue(String.class);
                            if(gen.equals(g)) {
                                n.add(snapshot.child("name").getValue(String.class));
                                a.add(snapshot.child("author").getValue(String.class));
                                desc.add(snapshot.child("desc").getValue(String.class));
                                pages.add(snapshot.child("pages").getValue(String.class));
                                id.add(snapshot.child("id").getValue(String.class));
                                status.add(snapshot.child("status").getValue(Integer.class));
                                user.add(snapshot.child("user").getValue(String.class));
                                count++;
                                break;
                            }
                        }
                    }
                    else {
                        String x = snapshot.child("name").getValue(String.class);
                        if(x.contains(search_input)) {
                            n.add(snapshot.child("name").getValue(String.class));
                            a.add(snapshot.child("author").getValue(String.class));
                            desc.add(snapshot.child("desc").getValue(String.class));
                            pages.add(snapshot.child("pages").getValue(String.class));
                            id.add(snapshot.child("id").getValue(String.class));
                            status.add(snapshot.child("status").getValue(Integer.class));
                            user.add(snapshot.child("user").getValue(String.class));
                            count++;
                        }
                    }
                }
                RecyclerView BookRecycler = findViewById(R.id.book_recycler);
                LinearLayoutManager layoutManager = new LinearLayoutManager(SearchScreenActivity.this);
                BookRecycler.setLayoutManager(layoutManager);
                BookListAdapter adapter = new BookListAdapter(n, a, pages, count);
                BookRecycler.setAdapter(adapter);
                adapter.setListener(new BookListAdapter.Listener() {
                    public void onClick(int position) {
                        Intent intent = new Intent(SearchScreenActivity.this, BookInfoActivity.class);
                        intent.putExtra("name",n.get(position));
                        intent.putExtra("auth",a.get(position));
                        intent.putExtra("desc",desc.get(position));
                        intent.putExtra("pages",pages.get(position));
                        intent.putExtra("status",status.get(position));
                        intent.putExtra("id", id.get(position));
                        intent.putExtra("user",user.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
