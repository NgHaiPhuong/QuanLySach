package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ManagerActivity extends AppCompatActivity {
    Button btnBack;
    ListView lstView;
    AdapterBook adapter = null;
    List<Book> dsBook = new ArrayList<>();
    Book bookSeleted = new Book();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

//        dsBook.add(new Book("B1", "Novel"));
//        dsBook.add(new Book("B2", "Novel"));
//        dsBook.add(new Book("B3", "Novel"));

        getWidget();
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            Log.d("nghp", bundle.getString("ma"));
//            Log.d("nghp", bundle.getString("ten"));
//            dsBook.add(new Book(bundle.getString("ma"), bundle.getString("ten")));
//            adapter.notifyDataSetChanged();
//        }
        handleEvent();
        readFile();
    }

    private void readFile() {
        try {
            dsBook.clear();
            FileInputStream in = openFileInput("qlbook.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                dsBook.add(Book.fromString(line));
            }
            adapter.notifyDataSetChanged();
            reader.close();
        } catch (FileNotFoundException e) {
            Log.e("nghp", e.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleEvent() {
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ManagerActivity.this, MainActivity.class);
            startActivity(intent);
        });

        lstView.setOnItemClickListener((parent, view, position, id) -> {
            bookSeleted = dsBook.get(position);

            Intent intent = new Intent(ManagerActivity.this, InsertActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("fix_ma", bookSeleted.getMaTg());
            bundle.putString("fix_ten", bookSeleted.getTenTg());
            intent.putExtras(bundle);

            startActivity(intent);
        });

        lstView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });
    }

    private void getWidget() {
        btnBack = findViewById(R.id.btnBack);
        lstView = findViewById(R.id.listview);

        adapter = new AdapterBook(this,
                R.layout.item_listview,
                dsBook);
        lstView.setAdapter(adapter);
    }
}