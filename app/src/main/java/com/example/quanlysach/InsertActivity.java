package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.os.WorkSource;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class InsertActivity extends AppCompatActivity {
    EditText etMa, etTen;
    Button btnXoaTrang, btnThemOrUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        getWidget();
        handleEvent();
    }

    private void handleEvent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String ma = bundle.getString("fix_ma");
            String ten = bundle.getString("fix_ten");

            etMa.setText(ma.toString());
            etTen.setText(ten.toString());
        } else {
            btnThemOrUpdate.setOnClickListener(v -> {
                String ma = etMa.getText().toString();
                String ten = etTen.getText().toString();

                Book book = new Book(ma, ten);

                saveFile(book);
                Intent intent = new Intent(InsertActivity.this, ManagerActivity.class);

//                Bundle bundle1 = new Bundle();
////                bundle1.putString("ma", ma);
////                bundle1.putString("ten", ten);
////                intent.putExtras(bundle1);

                startActivity(intent);
            });
        }
    }

    private void saveFile(Book book){
        try {
            FileOutputStream out = openFileOutput("qlbook.txt",MODE_APPEND);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            Log.d("nghp", book.toString());
            writer.newLine();
            writer.write(book.toString());
            writer.close();
        }catch (IOException e){
            Log.e("nghp", e.toString());
        }
    }

    private void getWidget() {
        etMa = findViewById(R.id.etMa);
        etTen = findViewById(R.id.etTen);
        btnXoaTrang = findViewById(R.id.btnXoaTrang);
        btnThemOrUpdate = findViewById(R.id.btnLuuAndUpdate);
    }
}