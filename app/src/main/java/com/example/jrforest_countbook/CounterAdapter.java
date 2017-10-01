package com.example.jrforest_countbook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jrforest on 9/26/17.
 */

public class CounterAdapter extends ArrayAdapter<Counter>{
    private ArrayList<Counter> counterList;
    private static LayoutInflater inflater = null;

    public CounterAdapter (Activity activity, int textViewResourceId, ArrayList<Counter> cList){
        super(activity, textViewResourceId, cList);

        try{
            this.counterList = cList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        catch (Exception e) {

        }


    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        try {
            if (convertView == null) {
                view = inflater.inflate(R.layout.counter_item, null);
            }


            final Counter counter = counterList.get(position);

            if (counter != null) {
                TextView counter_name = (TextView) view.findViewById(R.id.counterName);
                TextView counter_value = (TextView) view.findViewById(R.id.counterValue);
                TextView counter_date = (TextView) view.findViewById(R.id.counterDate);

                if (counter_name != null){
                    counter_name.setText(counter.getName());
                }

                if (counter_value != null){
                    counter_value.setText(String.valueOf(counter.getCurrent()));
                }

                if (counter_date != null){
                    counter_date.setText(counter.getDate());
                }

                Button incButton = (Button) view.findViewById(R.id.counterInc);
                Button decButton = (Button) view.findViewById(R.id.counterDec);

                incButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        counter.increment();
                        notifyDataSetChanged();
                    }
                });

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

        }
        return view;
    }
}


