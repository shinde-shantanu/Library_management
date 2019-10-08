package com.hfad.libraryapp;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shantanu Shinde on 18-07-2018.
 */

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder>{

    private String[] name;
    private String[] author;
    private int count;

    public RecordsAdapter(String[] name, String[] author, int count){
        this.name = name;
        this.author = author;
        this.count = count;
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
    public RecordsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_card_view, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        CardView cardView = holder.cardView;
        //ImageView imageView = (ImageView)cardView.findViewById(R.id.book_image);
        //Drawable drawable =
          //      ContextCompat.getDrawable(cardView.getContext(), R.drawable.image);
        //imageView.setImageDrawable(drawable);
        //imageView.setContentDescription(name[position]);
        TextView textView = (TextView)cardView.findViewById(R.id.book_name);
        textView.setText(name[position]);
        TextView textView1 = (TextView)cardView.findViewById(R.id.book_author);
        textView1.setText(author[position]);
    }

}
