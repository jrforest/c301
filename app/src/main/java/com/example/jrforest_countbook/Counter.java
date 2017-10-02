/* Jacqueline Forest
 * September 17 2017
*/
package com.example.jrforest_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;


/* Counter represents a counter object with five fields: date, initial value,
 * current value, name and comment
 */
public class Counter {
    private Date date;
    private int initial;
    private int current;
    private String name;
    private String comment;


    /* Since the comment is optional there are two constructors, one with a comment parameter
     * and one without. This constructor takes a name and initial value for a new Counter object.
     */
    public Counter(String name, int initial){
        date = new Date();
        this.initial = initial;
        // Whenever a new Counter is created, initial and current values are the same.
        this.current = initial;
        this.name = name;
        // Empty comment
        this.comment = "";


    }

    // This constructor takes a name, initial value and comment for a new counter object.
    public Counter(String name, int initial, String comment){
        date = new Date();
        this.initial = initial;
        this.current = initial;
        this.name = name;
        this.comment = comment;
    }

    // Sets a new current value for the counter
    public void setCurrent (int current){
        this.current = current;

    }

    // Returns the current value of the counter
    public int getCurrent(){
        return current;
    }

    // Sets a new initial value for the counter
    public void setInitial(int initial){
        this.initial = initial;
    }

    // Returns the initial value of the counter
    public int getInitial(){
        return initial;
    }

    // Sets a new name for the Counter
    public void setName(String name){
        this.name = name;
    }

    // Returns the name of the counter
    public String getName(){
        return name;
    }

    // Sets a new comment for the counter
    public void setComment(String comment){
        this.comment = comment;
    }

    // Returns the comment of the counter
    public String getComment(){
        return comment;
    }

    // Increments the current value of the counter by one
    public void increment() {current++;}

    // Decrements the current value of the counter by one
    public void decrement() {current--;}

    // Resets the counter from its current to its initial value
    public void reset() {current = initial;}

    // Sets the date of the counter to the current date and time
    public void updateDate(){
        date = new Date();
    }

    // Returns a string representation of the date in YYYY-MM-DD format
    public String getDate(){
        String simpleDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return simpleDate;
    }


}
