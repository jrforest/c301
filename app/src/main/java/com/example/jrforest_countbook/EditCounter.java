package com.example.jrforest_countbook;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class EditCounter extends AppCompatActivity {

    TextView name, initial, comment;

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
            this.comment = (TextView) findViewById(R.id.counterComment);

            String nameText = name.getText().toString();
            int initialInt = Integer.parseInt(initial.getText().toString());
            String commentText = comment.getText().toString();

            Intent intent = new Intent();

            intent.putExtra(MainList.COUNTER_NAME, nameText);
            intent.putExtra(MainList.COUNTER_INIT, initialInt);
            intent.putExtra(MainList.COUNTER_COMMENT, commentText);

            setResult(RESULT_OK, intent);

            finish();

        }

        catch(Exception e){
            e.printStackTrace();
        }



    }


}
