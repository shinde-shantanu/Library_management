package com.hfad.libraryapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shantanu Shinde on 18-07-2018.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder>{

    private ArrayList<String> name;
    private ArrayList<String> author;
    private ArrayList<String> pages;
    private int count;

    private Listener listener;
    interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public BookListAdapter(ArrayList<String> name,  ArrayList<String> author, ArrayList<String> pages,int count){
        this.name = name;
        this.author = author;
        this.count = count;
        this.pages = pages;
    }

    @Override
    public int getItemCount(){
        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_card_view, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        final CardView cardView = holder.cardView;
        TextView textView = (TextView)cardView.findViewById(R.id.book_name);
        textView.setText("Name: "+name.get(position));
        TextView textView1 = (TextView)cardView.findViewById(R.id.book_author);
        textView1.setText("Author: "+author.get(position));
        TextView textView2 = (TextView)cardView.findViewById(R.id.book_pages);
        textView2.setText("Pages: "+pages.get(position));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

}
