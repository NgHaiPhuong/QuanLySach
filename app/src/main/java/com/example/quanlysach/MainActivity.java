package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id1 = item.getItemId();
        if(R.id.them == id1) themSach();
        else if(R.id.xem == id1) xemSach();
        else if(R.id.ql == id1) qlSach();
        return false;
    }

    private void qlSach() {

    }

    private void xemSach() {
        Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
        startActivity(intent);
        finish();
    }

    private void themSach() {
        Toast.makeText(MainActivity.this, "Insert", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, InsertActivity.class);
        startActivity(intent);
        finish();
    }
}



