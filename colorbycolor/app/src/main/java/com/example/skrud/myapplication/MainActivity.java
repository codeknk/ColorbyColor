package com.example.skrud.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnQuick(View view) {
        Intent quick = new Intent(this, QuickSearchActivity.class);
        startActivity(quick);
    }

<<<<<<< HEAD
    public void btnDetail(View view) {
        Intent intent =new Intent(this,RGBActivity.class);
       // intent.putExtra("item","red lipstick");
        startActivity(intent);
    }
=======
>>>>>>> f0fbfb471b67d1cfe85f2317941ae7066b486dcc
}