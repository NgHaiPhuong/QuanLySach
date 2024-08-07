package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ManagerActivity extends AppCompatActivity {
    Button btnBack;
    ListView lstView;
    ArrayAdapter<Book> adapter = null;
    List<Book> dsBook = new ArrayList<>();
    Book bookSeleted = null;
    MyDatabaseHelper db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        db = MyDatabaseHelper.getsInstance(this);
        if(db.getTotalBooks() == 0) fakeData();

        getWidget();
        setUpListView();
        handleEvent();
    }

    public void fakeData() {
        db.deleteAllBook();
        db.insertBook(new Book("B1", "Trinh thám"));
        db.insertBook(new Book("B2", "Ngôn tình"));
        db.insertBook(new Book("B3", "Anime"));
    }

    private void handleEvent() {
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ManagerActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void setUpListView() {
        try{
            dsBook = db.getBook();
            adapter = new ArrayAdapter<>(this,
                    R.layout.item_listview,
                    dsBook);
            lstView.setAdapter(adapter);
        }catch (Exception e){
            Log.d("nghp", e.toString());
        }
    }

    private void getWidget() {
        btnBack = findViewById(R.id.btnBack);
        lstView = findViewById(R.id.listview);
    }
}