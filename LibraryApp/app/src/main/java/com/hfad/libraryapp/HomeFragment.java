package com.hfad.libraryapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView genreRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_home, container, false);

        String[] genres = new String[genre.Genre.length];
        for(int i=0; i<genre.Genre.length; i++) {
            genres[i] = genre.Genre[i].getName();
        }
        int[] genreImages = new int[genre.Genre.length];
        for(int i=0; i<genre.Genre.length; i++) {
            genreImages[i] = genre.Genre[i].getImageResourceId();
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        genreRecycler.setLayoutManager(layoutManager);
        GenreAdapter adapter = new GenreAdapter(genres, genreImages);
        genreRecycler.setAdapter(adapter);
        adapter.setListener(new GenreAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), SearchScreenActivity.class);
                intent.putExtra("pos", position);
                getActivity().startActivity(intent);
            }
        });
        return genreRecycler;
    }

}
