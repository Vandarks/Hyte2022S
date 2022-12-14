package com.example.ravintosovellushyte2022;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Miiko Majewski
 * Singleton object for storing history day objects.
 */
public class GlobalModel {
    private List<HistoryDay> historyDayList;
    private static final GlobalModel ourInstance = new GlobalModel();

    private Calendar calendar;
    private SimpleDateFormat dayMonthYearFormat;
    private SimpleDateFormat dayFormat;
    private String dateName;
    private String date;

    boolean createDone;

    private SharedPreferences sharedPref;

    public static GlobalModel getInstance() {return ourInstance;}

    /**
     * Constructor for the global mode singleton
     */
    private GlobalModel(){
        historyDayList = new ArrayList<>();
        calendar = Calendar.getInstance(); //Calendar object to get time
        dayMonthYearFormat = new SimpleDateFormat("dd/MM/yyyy"); //Date format to format the date
        dayFormat = new SimpleDateFormat("EEEE");
        createDone = false;
    }

    /**
     * Fills the object array list with the previous 7 days of nutritional values.
     * @param context Context where the function is called from
     */
    public void fillHistoryList(Context context){
        if(createDone == false) {
            historyDayList.clear();
            for (int i = -1; i > -8; i--) {
                calendar.add(Calendar.DATE, -1);
                date = dayMonthYearFormat.format(calendar.getTime());
                dateName = dayFormat.format(calendar.getTime());
                historyDayList.add(new HistoryDay(context, date, dateName));
            }
            createDone = true;
        }
    }

    /**
     * Gets the list of days from the object array list
     * @return List of history day objects
     */
    public List<HistoryDay> getHistoryDayList(){return historyDayList;}

    /**
     * Gets a specific day from the object array list
     * @param i # of the day in the object array list
     * @return Specific history day object from the object array list
     */
    public HistoryDay getHistoryDay(int i){return historyDayList.get(i);}
}

