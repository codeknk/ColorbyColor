package com.example.skrud.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class QSearchResultActivity extends AppCompatActivity {
    private QSearchImages qs;
    private ArrayList<QSearchImage> result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qsearch_result);

        Intent intent = getIntent();
        qs = (QSearchImages) intent.getSerializableExtra("send");
        result = (ArrayList<QSearchImage>) qs.getqSearchImageList();
        Log.v("QSresult",""+result.get(0));
    }
}
