package com.example.ziri.gopigo_new;

import android.app.Activity;
import android.os.Bundle;

public class GraphActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GraphView(this));
    }
}
