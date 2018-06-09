package com.example.skrud.myapplication;

import java.io.Serializable;
import java.util.List;

/**
 * Created by skrud on 2018-06-07.
 */

public class QSearchImages implements Serializable{
    private List<QSearchImage> qSearchImageList;

    public List<QSearchImage> getqSearchImageList() {
        return qSearchImageList;
    }

    public QSearchImages(List<QSearchImage> qSearchImageList) {
        this.qSearchImageList = qSearchImageList;
    }
}
