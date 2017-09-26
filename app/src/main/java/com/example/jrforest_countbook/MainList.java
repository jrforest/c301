package com.example.jrforest_countbook;

import com.example.jrforest_countbook.Counter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainList extends AppCompatActivity {

    public static final String COUNTER_NAME = "Name";
    public static final String COUNTER_INIT = "Initial";
    public static final String COUNTER_COMMENT = "Comment";

    static final int EDIT_COUNTER_REQUEST = 0;

    private ListView counterView;

    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        counterView = (ListView) findViewById(R.id.counterView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCounter(view);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter = new ArrayAdapter<Counter>(this, R.layout.counter_item, counters);

        counterView.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createCounter(View view){

        Intent intent = new Intent(this, EditCounter.class);
        startActivityForResult(intent, EDIT_COUNTER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == EDIT_COUNTER_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(COUNTER_NAME);
            int initial = data.getIntExtra(COUNTER_INIT, 0);
            String comment = data.getStringExtra(COUNTER_COMMENT);

            counters.add(new Counter(name, initial, comment));

            adapter.notifyDataSetChanged();

        }



    }


}
