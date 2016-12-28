package com.example.ziri.gopigo_new;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BddDataActivity extends Activity {
    ExpandableListAdapter listAdapter;
    ExpandableListView listView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ArrayList<Measure> list_measure = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bdd_data_layout);

        listView = (ExpandableListView) findViewById(R.id.listView);

        addMeasure();

        getBddData();

        fill_listView(list_measure);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listView.setAdapter(listAdapter);
    }

    private void fill_listView(ArrayList<Measure> list_measure){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        for(int i=0; i<list_measure.size(); i++){
            ArrayList<String> list_data = new ArrayList<>();
            listDataHeader.add(String.valueOf(list_measure.get(i).getMeasure())); //TODO fix
            list_data.add(String.valueOf(list_measure.get(i).getId()));
            Log.e("fill : id = ",String.valueOf(list_measure.get(i).getId()));
            list_data.add(String.valueOf(list_measure.get(i).getMeasure()));
            Log.e("fill : measure = ", String.valueOf(list_measure.get(i).getId()));
            listDataChild.put(String.valueOf(listDataHeader.get(i)),list_data);
        }
    }

    private void getBddData(){
        try {
            int id, measure;

            MeasureBDD measureBDD = new MeasureBDD(this);
            measureBDD.open();

            Cursor c = measureBDD.getBDD().rawQuery("SELECT * FROM sensor", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        id = c.getInt(c.getColumnIndex("id"));
                        Log.e("getBddData() : id=",String.valueOf(c.getInt(c.getColumnIndex("id"))));
                        measure = c.getInt(c.getColumnIndex("measure"));
                        Log.e("getBddData() : measure=",String.valueOf(c.getInt(c.getColumnIndex("id"))));
                        list_measure.add(new Measure(id, measure));
                    } while (c.moveToNext());
                }
                c.close();
            }
            measureBDD.close();
        }catch (Exception se){
            Log.e(getClass().getSimpleName(), "Could not create or Open the database ("+se.toString()+")");
        }
    }

    private void addMeasure(){
        try {
            MeasureBDD measureBDD = new MeasureBDD(this);
            measureBDD.open();
            measureBDD.insertMeasure(new Measure(0, 45));
            measureBDD.close();
        }catch(Exception e){
            Log.e(getClass().getSimpleName(), "Could not add measure");
        }
    }
}