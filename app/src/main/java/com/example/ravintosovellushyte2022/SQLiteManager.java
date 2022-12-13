package com.example.ravintosovellushyte2022;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "MealDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Ruokaa";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String CALORIES_FIELD = "calories";
    private static final String FATS_FIELD = "fats";
    private static final String CARBS_FIELD = "carbs";
    private static final String SALTS_FIELD = "salts";


    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context){
        if(sqLiteManager == null){
            sqLiteManager = new SQLiteManager(context);

        }
        return sqLiteManager;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(NAME_FIELD)
                .append(" TEXT, ")
                .append(CALORIES_FIELD)
                .append(" TEXT, ")
                .append(FATS_FIELD)
                .append(" TEXT, ")
                .append(CARBS_FIELD)
                .append(" TEXT, ")
                .append(SALTS_FIELD)
                .append(" TEXT)");

        sqLiteDatabase.execSQL(sql.toString());
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


    }

    public void addFoodToDatabase(Meal meal){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, meal.getId());
        contentValues.put(NAME_FIELD, meal.getName());
        contentValues.put(CALORIES_FIELD, meal.getCalories());
        contentValues.put(FATS_FIELD, meal.getFats());
        contentValues.put(CARBS_FIELD, meal.getCarbs());
        contentValues.put(SALTS_FIELD, meal.getSalts());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void populateMealListArray(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null)){
            if(result.getCount() !=0 ){
                while (result.moveToNext()){
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    float calories = result.getFloat(3);
                    float fats = result.getFloat(4);
                    float carbs = result.getFloat(5);
                    float salts = result.getFloat(6);
                    Meal meal = new Meal(id, name, calories, fats, carbs, salts);
                    MealList.getInstance().addMeal(meal);

                }
            }
        }
    }

    public void removeFromDB(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String idString = Integer.toString(id);

        try (Cursor result = sqLiteDatabase.rawQuery("DELETE FROM " + TABLE_NAME + " WHERE " + ID_FIELD + "=" + idString, null)){}
    }

    public void updateMealInDatabase(Meal meal){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, meal.getId());
        contentValues.put(NAME_FIELD, meal.getName());
        contentValues.put(CALORIES_FIELD, meal.getCalories());
        contentValues.put(FATS_FIELD, meal.getFats());
        contentValues.put(CARBS_FIELD, meal.getCarbs());
        contentValues.put(SALTS_FIELD, meal.getSalts());

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(meal.getId())});
    }
}
