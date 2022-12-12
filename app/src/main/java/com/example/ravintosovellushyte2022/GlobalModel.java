package com.example.ravintosovellushyte2022;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    //Constructor
    private GlobalModel(){
        historyDayList = new ArrayList<>();
        calendar = Calendar.getInstance(); //Calendar object to get time
        dayMonthYearFormat = new SimpleDateFormat("dd/MM/yyyy"); //Date format to format the date
        dayFormat = new SimpleDateFormat("EEEE");
        createDone = false;
    }
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
    public List<HistoryDay> getHistoryDayList(){return historyDayList;}
    public HistoryDay getHistoryDay(int i){return historyDayList.get(i);}
}

