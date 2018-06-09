package com.example.skrud.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    final int SELECT_IMAGE = 10;
    final int SELECT_CAMERA = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    boolean checkAppPermission(String[] requestPermission){
        boolean[] requestResult = new boolean[requestPermission.length];
        for(int i=0;i<requestResult.length;i++){
            requestResult[i] = (ContextCompat.checkSelfPermission
                    (this,requestPermission[i])== PackageManager.PERMISSION_GRANTED);
            if(!requestResult[i])
                return false;
        }
        return true;
    }
    //int REQ_PERMISSION - 구분자역할
    void askPermission(String[] requestPermission, int REQ_PERMISSION){
        ActivityCompat.requestPermissions(this, requestPermission,REQ_PERMISSION);
        //자동으로 호출되는 함수 있음 -> onRequestPermissionResult
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case SELECT_IMAGE:
                if (checkAppPermission(permissions)) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, SELECT_IMAGE);
                } else {
                    Toast.makeText(this, "권한이 거절됨", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case SELECT_CAMERA:
                if (checkAppPermission(permissions)) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, SELECT_CAMERA);
                } else {
                    Toast.makeText(this, "권한이 거절됨", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                imageView.setImageURI(data.getData());
            }
        }
    }

    public void btnCamera(View view) {
        if(checkAppPermission(new String[]{Manifest.permission.CAMERA})){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, SELECT_CAMERA);
        }else{
            askPermission(new String[]{Manifest.permission.CAMERA}, SELECT_CAMERA);
        }
    }

    public void btnGallary(View view) {
        if(checkAppPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE})){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_IMAGE);
        }else{
            askPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SELECT_IMAGE);
        }
    }

    public void btnQuick(View view) {
    }

    public void btnDetail(View view) {
        Intent intent =new Intent(this,RGBActivity.class);
       // intent.putExtra("item","red lipstick");
        startActivity(intent);
    }
}
