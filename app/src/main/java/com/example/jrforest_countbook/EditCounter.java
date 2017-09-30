package com.example.jrforest_countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

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

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });







    }
}
