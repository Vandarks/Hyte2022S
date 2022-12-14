package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Miiko Majewski
 * Displays a list view of the past 7 days of nutritional values. The values in the list contains
 * -date
 * -calories
 * -carbs
 * -fats
 * -salts
 */
public class HistoryActivity extends AppCompatActivity {

    private ListView historyListView;

    /**
     * onCreate function for the activity
     * @param savedInstanceState Previously saved instance of the app's data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyListView = findViewById(R.id.historyListView);

        //Fill the Globalmodel List with HistoryDay objects
        GlobalModel.getInstance().fillHistoryList(getApplicationContext());
        //Sets ArrayAdapter for the historyListView
        historyListView.setAdapter(new ArrayAdapter<HistoryDay>(
                this,
                android.R.layout.simple_list_item_1,
                GlobalModel.getInstance().getHistoryDayList()
        ));
    }

    /**
     * Exits the activity and returns to the previous activity.
     * @param v Back button
     */
    public void goBack(View v){
        finish();
    }
}