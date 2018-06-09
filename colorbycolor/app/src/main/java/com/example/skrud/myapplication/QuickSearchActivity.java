package com.example.skrud.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuickSearchActivity extends AppCompatActivity {

    private EditText colorname;
    private EditText keyword;
    final static String searchUri1= "https://www.google.co.kr/search?q=";
    final static String searchUri2 = "&source=lnms&tbm=isch&sa=X&ved=0ahUKEwi2qs3mn7_bAhXMXrwKHe7NCHgQ_AUICigB&biw=1396&bih=690";
    final static String TAG_CLASS_NAME = "rg_meta notranslate";
    List<String> jsonList;
    ArrayList<QSearchImage> qSearchImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_search);

        init();
    }
    public void init(){
        colorname = (EditText)findViewById(R.id.colorname);
        keyword = (EditText)findViewById(R.id.keyword);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public void btnGoogleSearch(View view) {
        String colorstr = colorname.getText().toString();
        String keystr = keyword.getText().toString();
        String searchUri = searchUri1+colorstr+"+"+keystr+searchUri2;

        try {
            //Connection.Response response = Jsoup.connect(searchUrl).method(Connection.Method.GET).execute();
            //Document rawData2 = response.parse();
            Document rawData = Jsoup.connect(searchUri).get();
            String html = rawData.html();

            Elements contents = rawData.select("div[class="+TAG_CLASS_NAME+"]");
            jsonList = new ArrayList<String>();
            for(Element content:contents){
                //StringBuffer contentJSON = new StringBuffer(content.text());
                String contentJSON = content.text();
                //int c_length_index = contentJSON.length()-1;
                //contentJSON = contentJSON.deleteCharAt(c_length_index);
                //contentJSON = contentJSON.deleteCharAt(0);

                jsonList.add(contentJSON);
            }
            //String test = jsonList.get(0);
            //Toast.makeText(this,test+" "+jsonList.size(),Toast.LENGTH_SHORT).show();
            parsingJSON(makeJSONObject(jsonList));
            test();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
        }

    }
    public String makeJSONObject(List<String> jsonList){
        String result = "{\"images\":[";
        for(int i=0;i<jsonList.size();i++){
            result = result+jsonList.get(i);
            if(i!=jsonList.size())
                result = result+",";
        }
        result = result+"]}";
        return result;
    }

    public void parsingJSON(String result){
        try{
            qSearchImageList = new ArrayList<QSearchImage>();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray("images");

            for (int i=0;i<qSearchImageList.size();i++){
                String imageURI= array.getJSONObject(i).getString("ou");
                String pageURL = array.getJSONObject(i).getString("ru");
                qSearchImageList.add(new QSearchImage(imageURI,pageURL));
            }

            if (qSearchImageList.size()==array.length()){
                Log.v("parsing JSON","in");
                Intent intent = new Intent(this, QSearchResultActivity.class);
                QSearchImages qs = new QSearchImages(qSearchImageList);
                intent.putExtra("QSearchImage", qs);
                startActivity(intent);

            }else{
                Toast.makeText(this,"Save fail",Toast.LENGTH_SHORT).show();
            }

        }catch (JSONException e){
            e.printStackTrace();
            Log.v("ErrorHerer","ErrorHere");
        }
    }

    public void test(){
        String test = qSearchImageList.get(0).getImageURI();
        int s = qSearchImageList.size();
        Log.v("size",s+"");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(test));
        startActivity(intent);
    }

}
