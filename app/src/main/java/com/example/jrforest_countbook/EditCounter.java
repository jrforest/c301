/* Jacqueline Forest
 * September 27, 2017
 */

package com.example.jrforest_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/* EditCounter is an Activity which takes the information from a counter, displays it and lets the
 * user edit it as they please (except the date) and then returns the edited data to the main activity
 * when they press the 'done' button. It also lets the user reset the counter to its initial value or
 * delete it altogether.
 */
public class EditCounter extends AppCompatActivity {

    // These TextViews display the five fields of a given counter
    private TextView date;
    private TextView name;
    private TextView initial;
    private TextView current;
    private TextView comment;


    /* onCreate initializes the TextViews, sets them with the counter's information and
     * initializes the three buttons that appear in the activity_edit_counter xml.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        // These variables represent the fields of the counter before editing
        String oldName;
        String oldComment;
        String oldDate;
        int oldInitial;
        int oldCurrent;


        // Initializing the TextViews
        this.name = (TextView) findViewById(R.id.editCounterName);
        this.initial = (TextView) findViewById(R.id.editCounterInitial);
        this.comment = (TextView) findViewById(R.id.editCounterComment);
        this.current = (TextView) findViewById(R.id.editCounterCurrent);
        this.date = (TextView) findViewById(R.id.editCounterDate);

        // Getting the old values of the counter from the intent
        oldName = getIntent().getStringExtra(MainList.COUNTER_NAME);
        oldComment = getIntent().getStringExtra(MainList.COUNTER_COMMENT);
        oldInitial = getIntent().getIntExtra(MainList.COUNTER_INIT, 0);
        oldCurrent = getIntent().getIntExtra(MainList.COUNTER_CURRENT, 0);
        oldDate = getIntent().getStringExtra(MainList.COUNTER_DATE);

        // Setting the appropriate text to each TextView
        name.setText(oldName);
        initial.setText(String.valueOf(oldInitial));
        comment.setText(oldComment);
        current.setText(String.valueOf(oldCurrent));
        date.setText(oldDate);

        // Instantiating and initializing the Done, Reset and Delete buttons
        Button doneButton = (Button) findViewById(R.id.editCounterDone);
        Button resetButton = (Button) findViewById(R.id.editCounterReset);
        Button deleteButton = (Button) findViewById(R.id.editCounterDelete);

        // the doneButton saves data and ends the activity
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endEditCounter();
            }
        });

        // The resetButton resets the counter to its initial value
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCounter();
            }

        });

        // The deleteButton will send a message to delete the counter and ends the activity
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCounter();
            }
        });



    }


    /* endEditCounter gets the new counter information and returns it to the main activity
     * in key-value pairs, ending the activity.
     */
    public void endEditCounter(){
        try{

            // Gets the new information from each textView
            String nameText = name.getText().toString();
            int initialInt = Integer.parseInt(initial.getText().toString());
            int currentInt = Integer.parseInt(current.getText().toString());
            String commentText = comment.getText().toString();

            // Creating a new intent
            Intent intent = new Intent();

            // Passes back all the new information and the position in the ArrayList of the counter
            // it belongs to
            intent.putExtra(MainList.COUNTER_NAME, nameText);
            intent.putExtra(MainList.COUNTER_INIT, initialInt);
            intent.putExtra(MainList.COUNTER_COMMENT, commentText);
            intent.putExtra(MainList.COUNTER_CURRENT, currentInt);
            intent.putExtra(MainList.COUNTER_POSITION, getIntent().getIntExtra(MainList.COUNTER_POSITION, 0));

            // When the doneButton is pressed, I set RESULT_OK
            setResult(RESULT_OK, intent);

            // Finishes the activity
            finish();

        }

        catch(Exception e){
            e.printStackTrace();
        }

    }

    // resets the current value to whatever value is in the 'initial' field
    public void resetCounter(){
        int initialInt = Integer.parseInt(initial.getText().toString());
        current.setText(String.valueOf(initialInt));

    }

    // Unlike endEditCounter, deleteCounter doesn't save any of the information from the TextViews.
    // It only sends a signal to the main activity that this counter is to be deleted.
    public void deleteCounter(){

        try {
            // Creates a new Intent
            Intent intent = new Intent();

            // Puts the position of the counter to be deleted.
            intent.putExtra(MainList.COUNTER_POSITION, getIntent().getIntExtra(MainList.COUNTER_POSITION, 0));

            // When deleteButton is pressed, we set the result to RESULT_CANCELED
            setResult(RESULT_CANCELED, intent);

            // Ends the activity
            finish();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }





}
