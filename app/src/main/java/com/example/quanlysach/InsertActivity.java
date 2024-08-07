package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.os.WorkSource;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsertActivity extends AppCompatActivity {
    EditText etMa, etTen;
    Button btnXoaTrang, btnThemOrUpdate;
    MyDatabaseHelper db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        db = MyDatabaseHelper.getsInstance(this);
        if(db.getTotalBooks() == 0) fakeData();

        getWidget();
        handleEvent();
    }

    public void fakeData() {
        db.deleteAllBook();
        db.insertBook(new Book("B1", "Trinh thám"));
        db.insertBook(new Book("B2", "Ngôn tình"));
        db.insertBook(new Book("B3", "Anime"));
    }


    private void handleEvent() {
        String ma = etMa.getText().toString();
        String ten = etTen.getText().toString();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

        } else {
            btnThemOrUpdate.setOnClickListener(v -> {
                Book book = new Book();
                book.setMaTg(ma);
                book.setTenTg(ten);
                db.insertBook(book);

                Intent intent = new Intent(InsertActivity.this, ManagerActivity.class);
                startActivity(intent);
            });
        }
    }

    private void getWidget() {
        etMa = findViewById(R.id.etMa);
        etTen = findViewById(R.id.etTen);
        btnXoaTrang = findViewById(R.id.btnXoaTrang);
        btnThemOrUpdate = findViewById(R.id.btnLuuAndUpdate);
    }
}