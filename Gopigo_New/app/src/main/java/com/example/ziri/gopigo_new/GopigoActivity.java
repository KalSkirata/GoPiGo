package com.example.ziri.gopigo_new;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.button;

public class GopigoActivity extends Activity {

    Button up, down, right, left, start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gopigo);

        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);
        up = (Button)findViewById(R.id.up);
        down = (Button)findViewById(R.id.down);
        right = (Button)findViewById(R.id.up);
        left = (Button)findViewById(R.id.up);

    }


    public void onClick(View view){

        final int id = view.getId();
        switch (id) {

            case R.id.start:
                Toast.makeText(getApplicationContext(),
                        " ROBOT START", Toast.LENGTH_LONG).show();
                break;
            case R.id.stop:
                Toast.makeText(getApplicationContext(),
                        "ROBOT STOP", Toast.LENGTH_LONG).show();
                break;
            case R.id.up:
                Toast.makeText(getApplicationContext(),
                        "ROBOT GO FORWARD", Toast.LENGTH_LONG).show();
                break;
            case R.id.down:
                Toast.makeText(getApplicationContext(),
                        "ROBOT GO BACKWARD", Toast.LENGTH_LONG).show();
                break;
            case R.id.right:
                Toast.makeText(getApplicationContext(),
                        "ROBOT TURN RIGHT", Toast.LENGTH_LONG).show();
                break;
            case R.id.left:
                Toast.makeText(getApplicationContext(),
                        "ROBOT TURN LEFT", Toast.LENGTH_LONG).show();
                break;

        }
    }
}
