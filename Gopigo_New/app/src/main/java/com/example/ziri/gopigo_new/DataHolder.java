package com.example.ziri.gopigo_new;

import java.util.List;

/**
 * Singleton class useful to keep list of measure between activities
 */

public class DataHolder {

    List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance(){return holder;}
}
