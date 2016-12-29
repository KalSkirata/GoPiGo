package com.example.ziri.gopigo_new;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class GraphActivity extends Activity {

    GraphView graphView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        graphView = new GraphView(this);
        setContentView(graphView);
        autoRefresh();
    }

    public void autoRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(getClass().getSimpleName(),"autoRefresh");
                new SynchroBDD(getApplicationContext());
                graphView = new GraphView(getApplicationContext());
                autoRefresh();
            }
        }, 5000);
    }
}
