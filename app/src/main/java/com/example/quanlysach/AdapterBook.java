package com.example.quanlysach;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterBook extends ArrayAdapter<Book> {
    Activity context;
    int resource;
    List<Book> object;

    public AdapterBook(@NonNull Activity context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.object = objects;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        view = layoutInflater.inflate(resource, null);

        TextView tvInfor = view.findViewById(R.id.tvInfor);

        Book book = object.get(position);

        tvInfor.setText(book.toString());

        return view;
    }
}
