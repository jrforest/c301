package com.example.jrforest_countbook;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class EditCounter extends AppCompatActivity {

    TextView name, initial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);
        Button button = (Button) findViewById(R.id.counterEditDone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endEditCounter();
            }
            });
        }


    public void endEditCounter(){
        try{
            this.name = (TextView) findViewById(R.id.counterName);
            this.initial = (TextView) findViewById(R.id.counterInitial);
            String nameText = name.getText().toString();
            int initialInt = Integer.parseInt(initial.getText().toString());




        }

        catch(Exception e){
            e.printStackTrace();
        }



    }


}
