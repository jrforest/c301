package com.example.jrforest_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EditCounter extends AppCompatActivity {

    TextView date;
    TextView name;
    TextView initial;
    TextView current;
    TextView comment;

    String oldName;
    String oldComment;
    String oldDate;
    int oldInitial;
    int oldCurrent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        this.name = (TextView) findViewById(R.id.editCounterName);
        this.initial = (TextView) findViewById(R.id.editCounterInitial);
        this.comment = (TextView) findViewById(R.id.editCounterComment);
        this.current = (TextView) findViewById(R.id.editCounterCurrent);
        this.date = (TextView) findViewById(R.id.editCounterDate);

        oldName = getIntent().getStringExtra(MainList.COUNTER_NAME);
        oldComment = getIntent().getStringExtra(MainList.COUNTER_COMMENT);
        oldInitial = getIntent().getIntExtra(MainList.COUNTER_INIT, 0);
        oldCurrent = getIntent().getIntExtra(MainList.COUNTER_CURRENT, 0);
        oldDate = getIntent().getStringExtra(MainList.COUNTER_DATE);


        name.setText(oldName);
        initial.setText(String.valueOf(oldInitial));
        comment.setText(oldComment);
        current.setText(String.valueOf(oldCurrent));
        date.setText(oldDate);


        Button doneButton = (Button) findViewById(R.id.editCounterDone);
        Button resetButton = (Button) findViewById(R.id.editCounterReset);
        Button deleteButton = (Button) findViewById(R.id.editCounterDelete);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endEditCounter();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int initialint = Integer.parseInt(initial.getText().toString());
                current.setText(String.valueOf(initialint));

            }

        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCounter();
            }
        });



    }





    public void endEditCounter(){
        try{

            String nameText = name.getText().toString();
            int initialInt = Integer.parseInt(initial.getText().toString());
            int currentInt = Integer.parseInt(current.getText().toString());
            String commentText = comment.getText().toString();


            Intent intent = new Intent();

            intent.putExtra(MainList.COUNTER_NAME, nameText);
            intent.putExtra(MainList.COUNTER_INIT, initialInt);
            intent.putExtra(MainList.COUNTER_COMMENT, commentText);
            intent.putExtra(MainList.COUNTER_CURRENT, currentInt);
            intent.putExtra(MainList.COUNTER_POSITION, getIntent().getIntExtra(MainList.COUNTER_POSITION, 0));

            setResult(RESULT_OK, intent);

            finish();

        }

        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void deleteCounter(){

        try {
            Intent intent = new Intent();

            intent.putExtra(MainList.COUNTER_POSITION, getIntent().getIntExtra(MainList.COUNTER_POSITION, 0));

            setResult(RESULT_CANCELED, intent);

            finish();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }





}
