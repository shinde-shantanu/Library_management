package com.hfad.libraryapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users/");
        DatabaseReference df = firebaseDatabase.getReference();
        df.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String x = dataSnapshot.child(firebaseUser.getUid()).child("name").getValue(String.class);
                if(x.equals("admin")) {
                    Button addcus = getActivity().findViewById(R.id.addcus);
                    Button addbook = getActivity().findViewById(R.id.addbook);
                    addbook.setVisibility(View.VISIBLE);
                    addcus.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                if(key.equals(firebaseUser.getUid())) {
                    TextView name = (TextView) getActivity().findViewById(R.id.name);
                    TextView date = (TextView) getActivity().findViewById(R.id.joindate);
                    User user = dataSnapshot.getValue(User.class);
                    name.setText(user.name);
                    date.setText(user.joindate);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Button pro = (Button) getActivity().findViewById(R.id.showinfo);
        //pro.setEnabled(true);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}