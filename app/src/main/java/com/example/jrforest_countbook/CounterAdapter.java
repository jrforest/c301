/* Jacqueline Forest
 * September 26 2017
 */

package com.example.jrforest_countbook;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/* CounterAdapter is a custom ArrayAdapter for the Counter class. Its primary function is to inflate
 * and manage the views for a list of Counter objects. It inherits from ArrayAdapter.
 */
public class CounterAdapter extends ArrayAdapter<Counter>{
    // counterList is the ArrayList of counters a particular adapter is managing
    private ArrayList<Counter> counterList;

    /* inflater is the LayoutInflater used to create the views for each row of the ListView in which
    * the counters are displayed. It is initialized to null when the CounterAdapter is created
    */
    private static LayoutInflater inflater = null;

    /* Constructor for the CounterAdapter. It takes as parameters the Activity from which is was called,
     * a textViewResourceId, and the ArrayList of Counters to be displayed.
     */
    public CounterAdapter (Activity activity, int textViewResourceId, ArrayList<Counter> cList){
        super(activity, textViewResourceId, cList);

        // Saves the counter list to a class attribute and tries inflating the layout.
        try{
            this.counterList = cList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        catch (Exception e) {
            e.printStackTrace();
        }


    }

    /* getView overrides an ArrayAdapter method to initialize and set all the buttons and
     * TextViews for a single custom row item. position is the position of the counter
     * in the ArrayList of counters that it's currently inflating and initializing.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        try {
            if (convertView == null) {
                // If the row is not inflated, this will inflate it
                view = inflater.inflate(R.layout.counter_item, null);
            }

            // counter is the Counter object to be set in this particular row item.
            final Counter counter = counterList.get(position);

            if (counter != null) {
                // Row items have three TextViews showing the current value, name and date of a counter
                TextView counter_name = (TextView) view.findViewById(R.id.counterName);
                TextView counter_value = (TextView) view.findViewById(R.id.counterValue);
                TextView counter_date = (TextView) view.findViewById(R.id.counterDate);

                // Set the counter's name to the name TextView
                if (counter_name != null){
                    counter_name.setText(counter.getName());
                }

                // Set the counter's value to the value TextView
                if (counter_value != null){
                    counter_value.setText(String.valueOf(counter.getCurrent()));
                }

                // Set the counter's date to the date TextView
                if (counter_date != null){
                    counter_date.setText(counter.getDate());
                }

                /* Each row has two buttons, one to increment the counter and one to decrement it
                 */
                Button incButton = (Button) view.findViewById(R.id.counterInc);
                Button decButton = (Button) view.findViewById(R.id.counterDec);

                // incButton increments the counter
                incButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        counter.increment();
                        notifyDataSetChanged();
                    }
                });

                // decButton decrements the counter
                decButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        counter.decrement();
                        notifyDataSetChanged();
                    }
                });


            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}


