/* Jacqueline Forest
* September 20 2017
*/

package com.example.jrforest_countbook;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/* CreateCounter is an Activity which collects all the information necessary to make a
 * new Counter object and sends it to the main activity.
 */
public class CreateCounter extends AppCompatActivity {

    // It has three views in which the user can enter a name, initial value and comment for the new counter.
    TextView name;
    TextView initial;
    TextView comment;

    //onCreate initializes the TextViews and the button indicating the user is done creating the counter.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);

        // Gets the TextViews from the xml file
        this.name = (TextView) findViewById(R.id.counterName);
        this.initial = (TextView) findViewById(R.id.counterInitial);
        this.comment = (TextView) findViewById(R.id.counterComment);

        /* When the user clicks the doneButton, it checks for a valid name and initial value
        * in the appropriate fields. It doesn't check for a comment since that's not mandatory.
        */
        Button doneButton = (Button) findViewById(R.id.counterEditDone);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Makes sure name isn't empty
                if (name.getText().toString().isEmpty()){
                    name.setError("Counter name required");
                }

                // Makes sure there is an initial value.
                else if (initial.getText().toString().isEmpty()){
                    initial.setError("Counter value required");
                }

                /* If there is a name and initial value, it's okay to end the activity and
                 * return the values to the main activity.
                 */
                else {
                    endCreateCounter();
                }
            }
            });
        }


    /* endCreateCounter gets the values that the user entered in the TextViews, attaches them to a
     * new intent and finishes the activity, returning the intent to the main activity.
     */
    protected void endCreateCounter(){
        try{

            // Gets the name, text and comment from the TextViews
            String nameText = name.getText().toString();
            int initialInt = Integer.parseInt(initial.getText().toString());
            String commentText = comment.getText().toString();

            // Creates a new intent
            Intent intent = new Intent();

            // Attaches the information to the intent in key-value pairs, using the
            // final Strings from MainList
            intent.putExtra(MainList.COUNTER_NAME, nameText);
            intent.putExtra(MainList.COUNTER_INIT, initialInt);
            intent.putExtra(MainList.COUNTER_COMMENT, commentText);

            // Sets the result as OK
            setResult(RESULT_OK, intent);

            // Finishes the activity
            finish();

        }

        catch(Exception e){
            e.printStackTrace();
        }



    }


}
