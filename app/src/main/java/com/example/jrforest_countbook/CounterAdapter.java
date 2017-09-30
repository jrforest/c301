package com.example.jrforest_countbook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jrforest on 9/26/17.
 */

public class CounterAdapter extends ArrayAdapter<Counter>{
    private ArrayList<Counter> cList;
    private static LayoutInflater inflater = null;

    public CounterAdapter (Activity activity, int textViewResourceId, ArrayList<Counter> cList){
        super(activity, textViewResourceId, cList);

        try{
            this.cList = cList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        catch (Exception e) {

        }


    }

   /* public int getPosition(){
        return po
    }
    */


    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        try {
            if (convertView == null) {
                v = inflater.inflate(R.layout.counter_item, null);
            }


            final Counter c = cList.get(position);

            if (c != null) {
                TextView counter_name = (TextView) v.findViewById(R.id.counterName);
                TextView counter_value = (TextView) v.findViewById(R.id.counterValue);

                if (counter_name != null){
                    counter_name.setText(c.getName());
                }

                if (counter_value != null){
                    counter_value.setText(String.valueOf(c.getCurrent()));
                }

                Button incButton = (Button) v.findViewById(R.id.counterInc);
                Button decButton = (Button) v.findViewById(R.id.counterDec);

                incButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        c.increment();
                        notifyDataSetChanged();
                    }
                });

                decButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        c.decrement();
                        notifyDataSetChanged();
                    }
                });






            }



        }
        catch (Exception e) {

        }
        return v;
    }
}


