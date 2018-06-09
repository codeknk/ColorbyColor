package com.example.skrud.myapplication;

import java.io.Serializable;

/**
 * Created by skrud on 2018-06-07.
 */

public class QSearchImage implements Serializable{
    private String imageURI="";
    private String pageURL="";

    QSearchImage(String imageURI,String pageURL){
        this.imageURI=imageURI;
        this.pageURL=pageURL;
    }
    void setImageURI(String uri){
        this.imageURI = uri;
    }
    void setpageURL(String uri){
        this.pageURL = uri;
    }
    String getImageURI(){
        return this.imageURI;
    }
    String getPageURL(){
        return this.pageURL;
    }
}
