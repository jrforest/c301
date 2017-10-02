/* Jacqueline Forest
 * September 17 2017
 */

package com.example.jrforest_countbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;


/* MainList is the main activity of the app, and the controller class that manages all the other
 * activities and classes. It displays a list of Counter objects using a CounterAdapter, and offers
 * the options to edit existing counters or create new ones.
 */
public class MainList extends AppCompatActivity {

    // These Strings are the keys for the key-value pairs that are used to pass information
    // through intents. There is one for each field in a counter and one for the position of
    // a counter in the ArrayList
    public static final String COUNTER_NAME = "Name";
    public static final String COUNTER_INIT = "Initial";
    public static final String COUNTER_CURRENT = "Current";
    public static final String COUNTER_COMMENT = "Comment";
    public static final String COUNTER_DATE = "Date";
    public static final String COUNTER_POSITION = "Position";

    // The file used to save the counters so they persist between calls to MainList
    private static final String FILENAME = "jrforestCountBook.sav";

    // Request codes for EditCounter and CreateCounter
    private static final int CREATE_COUNTER_REQUEST = 0;
    private static final int EDIT_COUNTER_REQUEST = 1;

    // totalCount displays the total number of existing counters
    private TextView totalCount;

    // counterView is a ListView that displays all the counters, one per row
    private ListView counterView;

    // counters keeps track of all the Counters for the app
    private ArrayList<Counter> counters;

    // adapter displays counters in counterView
    private CounterAdapter adapter;



    // onCreate initializes the ListView and TextView in MainList as well as creating the
    // button to add new counters and setting the listener that allows users to edit
    // existing counters.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // counterView displays counters
        counterView = (ListView) findViewById(R.id.counterView);
        // totalCount displays a summary of the total number of counters
        totalCount = (TextView) findViewById(R.id.totalCounters);

        // newCounterButton, when pressed, starts CreateCounter
        FloatingActionButton newCounterButton = (FloatingActionButton) findViewById(R.id.fab);
        newCounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCounter(view);
            }
        });

        // When the user clicks on a row in counterView, this listener passes the position of
        // the counter to editCounter to start EditCounter
        counterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                editCounter(position);
            }
        });




    }

    // When the main activity starts up it loads past counters from an external file into counters and sets the adapter
    @Override
    protected void onStart(){
        super.onStart();

        counters = new ArrayList<Counter>();
        loadFromFile();
        adapter = new CounterAdapter(this, R.layout.counter_item, counters);
        counterView.setAdapter(adapter);

    }



    // When MainActivity resumes, it updates the adapter and saves the counters
    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
        updateCount();
        saveInFile();

    }

    // When MainActivity pauses, it saves the counters
    @Override
    protected void onPause(){
        super.onPause();
        saveInFile();
    }


    // When MainActivity stops, it saves the counters
    @Override
    protected void onStop(){
        super.onStop();
        saveInFile();
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

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* onActivityResult handles the information returned from CreateCounter and EditCounter when they
     * end, and takes the appropriate action depending on which buttons were pressed and what
     * values were entered in the other activities. It takes a requestCode indicating which activity
     * is returning information, a resultCode showing the result of the activity and an Intent
     * with the data from the activity attatched.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        // If returning from CreateCounter with a valid result
        if (requestCode == CREATE_COUNTER_REQUEST && resultCode == RESULT_OK){

            // Get the name, initial value and comment for the new Counter
            String name = data.getStringExtra(COUNTER_NAME);
            int initial = data.getIntExtra(COUNTER_INIT, 0);
            String comment = data.getStringExtra(COUNTER_COMMENT);

            // Create a new Counter and add it to counters
            counters.add(new Counter(name, initial, comment));

            // Update the views for the counters and the summary
            adapter.notifyDataSetChanged();
            updateCount();

            // Save the list of counters
            saveInFile();

        }

        // If returing from EditCounter and the result was an edit request (RESULT_OK)
        else if (requestCode == EDIT_COUNTER_REQUEST && resultCode == RESULT_OK){

            // Get the edited fields from the intent
            String name = data.getStringExtra(COUNTER_NAME);
            int initial = data.getIntExtra(COUNTER_INIT, 0);
            String comment = data.getStringExtra(COUNTER_COMMENT);
            int current = data.getIntExtra(COUNTER_CURRENT, 0);

            // Get the position of the right counter
            int position = data.getIntExtra(COUNTER_POSITION, 0);

            // Edit the data of the counter at that position
            counters.get(position).setName(name);
            counters.get(position).setInitial(initial);
            counters.get(position).setComment(comment);
            counters.get(position).setCurrent(current);
            counters.get(position).updateDate();

            // Update the views and save the counters
            adapter.notifyDataSetChanged();
            updateCount();
            saveInFile();

        }

        // If returning from EditCounter and the result was a delete request (REQUEST_CANCELED)
        else if (requestCode == EDIT_COUNTER_REQUEST && resultCode == RESULT_CANCELED){

            // Get the position of the counter to be removed
            int position = data.getIntExtra(COUNTER_POSITION, 0);

            // Delete the counter
            counters.remove(position);

            // Update the views and save the change
            adapter.notifyDataSetChanged();
            updateCount();
            saveInFile();

        }


    }

    // createCounter starts the CreateCounter activity to make a new activity
    public void createCounter(View view){

        // Save the current counters
        saveInFile();

        // Create a new intent and start the activity
        Intent intent = new Intent(this, CreateCounter.class);
        startActivityForResult(intent, CREATE_COUNTER_REQUEST);
    }

    // editCount starts the editCounter activity to edit an existing counter. It takes an int
    // representing the position of the counter in counters
    public void editCounter(int position){

        // Create a new intent
        Intent intent = new Intent(this, EditCounter.class);

        // Save existing counters
        saveInFile();

        // Attatch the information of the counter and its position to the intent
        intent.putExtra(COUNTER_NAME, counters.get(position).getName());
        intent.putExtra(COUNTER_INIT, counters.get(position).getInitial());
        intent.putExtra(COUNTER_CURRENT, counters.get(position).getCurrent());
        intent.putExtra(COUNTER_COMMENT, counters.get(position).getComment());
        intent.putExtra(COUNTER_DATE, counters.get(position).getDate());
        intent.putExtra(COUNTER_POSITION, position);

        // start the activity
        startActivityForResult(intent, EDIT_COUNTER_REQUEST);

    }

    // update the view showing the total number of counters
    public void updateCount(){
        String countNumber = "";

        if (counters != null) {
            // Get total number of counters in a string
            countNumber = String.valueOf(counters.size()) + " counters";
        }

        else {
            countNumber = "0 counters";
        }
        // Set the String to totalCount
        totalCount.setText(countNumber);
    }


    /* saveInFile saves the current list of counters to a Gson file that is saved in the phone's
     * memory. This code is taken from the code we as a class wrote for lonelyTwitter in the lab.
     */
    private void saveInFile() {
        try {

            // Create a new FileOutputStram to write to
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            // Create a new writer for fos
            OutputStreamWriter writer = new OutputStreamWriter(fos);

            // Turn counters into a Gson object which is then written to fos
            Gson gson = new Gson();
            gson.toJson(counters, writer);

            // Flush the Gson object to the file and close the output stream
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    /* loadFromFile loads past counters from an external file into the counters ArrayList.
     * This code is taken from the code we as a class wrote for lonelyTwitter in the lab.
     */
    private void loadFromFile() {
        try {

            // Create a new FileInputStream, reader and Gson objecy
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();

            // Read the counters from the file into counters
            counters = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counters = new ArrayList<Counter>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }





}
