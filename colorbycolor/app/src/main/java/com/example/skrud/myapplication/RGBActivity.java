package com.example.skrud.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class RGBActivity extends AppCompatActivity {
    BitmapXY setxy;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgbacitivity);
        final ImageView imageView=(ImageView)findViewById(R.id.image);

        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.lip);
        //resource에 있는 jpeg를 bitmap으로
        imageView.setImageBitmap(bitmap);

//       int[] location=new int[2];
//       imageView.getLocationOnScreen(location);//이미지뷰에서 좌표값 추출
//

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float bitmapWidth=bitmap.getWidth();
                float bitmapHeight=bitmap.getHeight();

                float imageWidth=imageView.getWidth();
                float imageHeight=imageView.getHeight();

                float proportionateWidth=bitmapWidth/imageWidth;
                float proportionateHeight=bitmapHeight/imageHeight;

                int tapX=(int)(event.getX()*proportionateWidth);
                int tapY=(int)(event.getY()*proportionateHeight);
                Toast.makeText(getApplicationContext(),tapX+" "+tapY,Toast.LENGTH_LONG).show();
                setxy=new BitmapXY(tapX,tapY);
                return false;

            }
        });


    }

    public void btnClick(View view) {
        int rgb=bitmap.getPixel(setxy.getX(), setxy.getY());//rgb값 추출
        int A= Color.alpha(rgb);//alpha->투명도
        int R=Color.red(rgb);//빨강
        int G=Color.green(rgb);//초록
        int B=Color.blue(rgb);//파랑
//        String rgbHex;
        String r=Integer.toHexString(R).toUpperCase();
        if(r.length()<2)
            r="0"+r;
        String g=Integer.toHexString(G).toUpperCase();
        if(g.length()<2)
            g="0"+g;
        String b=Integer.toHexString(B).toUpperCase();
        if(b.length()<2)
            b="0"+b;
//        rgbHex="#"+r+g+b;
//        Toast.makeText(getApplicationContext(),rgbHex,Toast.LENGTH_LONG).show();
        int HexR=Integer.parseInt(r,16);
        int HexG=Integer.parseInt(g,16);
        int HexB=Integer.parseInt(b,16);

        ColoUtils colorUtils=new ColoUtils();
        String colorName=colorUtils.getColorNameFromRgb(HexR,HexG,HexB);

        Intent intent=new Intent(getApplicationContext(),SearchActivity.class);
        intent.putExtra("colorName",colorName);
        startActivity(intent);
       // Toast.makeText(getApplicationContext(),colorName,Toast.LENGTH_LONG).show();
    }
}