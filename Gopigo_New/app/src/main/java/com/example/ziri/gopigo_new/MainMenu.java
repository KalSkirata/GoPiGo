package com.example.ziri.gopigo_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainMenu extends Activity implements View.OnClickListener {

    Button video_stream, local_bdd_data, sync, graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gopigo_menu);

        video_stream = (Button) findViewById(R.id.video_stream);
        local_bdd_data = (Button) findViewById(R.id.local_bdd_data);
        graph = (Button) findViewById(R.id.graph);
        sync = (Button) findViewById(R.id.sync);

    }

    public void onClick(View view) {
        Log.e(TAG,"onclick");
        final int id = view.getId();
        switch (id) {

            case R.id.video_stream:
                Log.e(TAG,"video stream clicked");
                Intent intent = new Intent(this, RPIConnection.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        " Activity streaming video ", Toast.LENGTH_LONG).show();
                break;
            case R.id.local_bdd_data:
                Intent intent1 = new Intent(MainMenu.this, BddDataActivity.class);
                startActivity(intent1);
                break;
            case R.id.graph:
                Intent intent2 = new Intent(MainMenu.this, GraphActivity.class);
                startActivity(intent2);
                break;
            case R.id.rpi:
                Intent intent3 = new Intent(MainMenu.this, RPIDataActivity.class);
                startActivity(intent3);
                break;
            case R.id.sync:
                new SynchroBDD(this);
                break;

            default :
                Log.e(TAG,"nothing clicked");
                //Toast.makeText(getApplicationContext()," nothing selected ", Toast.LENGTH_LONG).show();
        }
    }
}
