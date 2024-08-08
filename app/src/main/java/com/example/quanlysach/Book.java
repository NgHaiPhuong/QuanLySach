package com.example.quanlysach;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Book implements Serializable {
    private String maTg;
    private String tenTg;

    public Book(){

    }

    public Book(String maTg, String tenTg) {
        this.maTg = maTg;
        this.tenTg = tenTg;
    }

    public String getMaTg() {
        return maTg;
    }

    public void setMaTg(String maTg) {
        this.maTg = maTg;
    }

    public String getTenTg() {
        return tenTg;
    }

    public void setTenTg(String tenTg) {
        this.tenTg = tenTg;
    }

    @NonNull
    @Override
    public String toString() {
        return getMaTg() + "," + getTenTg();
    }

    public static Book fromString(String str){
        String[] part = str.split(",");
        return new Book(part[0], part[1]);
    }
}

