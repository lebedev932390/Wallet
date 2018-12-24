package com.evgeny.lebed.wallet.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.evgeny.lebed.wallet.Class.Payment;
import com.evgeny.lebed.wallet.Interface.ContractModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper implements ContractModel {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "paymentsDB";


    private static final String TABLE_PAYMENTS = "payments";

    private static final String KEY_ID = "_id";
    private static final String KEY_DATE = "date";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_NOTE = "note";
    private static final String KEY_BUDGET = "budget";

    private static final String TABLE_SETTINGS = "settings";
    private static final String KEY_OPTION = "option";
    private static final String KEY_VALUE = "value";
    private static final String INSTALLATION_DATE = "installation_date";
    private static final String INITIAL_SETUP = "initial_setup";
    private static final String LANGUAGE = "language";
    private static final String CURRENCY = "currency";
    private static final String BUDGET = "budget";
    private static final String START_DATE = "start_date";

    private static final String TABLE_NOTES = "notes";
    private static final String KEY_NOTE_NAME = "noteName";



    private SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PAYMENTS + "(" + KEY_ID + " integer primary key," + KEY_DATE + " text," + KEY_AMOUNT + " text," + KEY_NOTE + " text," + KEY_BUDGET + " text" + ")");
        db.execSQL("create table " + TABLE_SETTINGS + "(" + KEY_ID + " integer primary key," + KEY_OPTION + " text," + KEY_VALUE + " text" + ")");
        db.execSQL("create table " + TABLE_NOTES + "(" + KEY_ID + " integer primary key," + KEY_NOTE_NAME + " text" + ")");
        Calendar calendar = Calendar.getInstance();
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_OPTION, INSTALLATION_DATE);
        contentValues.put(KEY_VALUE, calendar.getTimeInMillis());
        contentValues.put(KEY_OPTION, INITIAL_SETUP);
        contentValues.put(KEY_VALUE, 0);
        db.insert(TABLE_SETTINGS, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PAYMENTS);
        db.execSQL("drop table if exists " + TABLE_SETTINGS);
        db.execSQL("drop table if exists " + TABLE_NOTES);

        onCreate(db);
    }


    @Override
    public void insertPayment(Long date, Double amount, String note) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_AMOUNT, amount);
        contentValues.put(KEY_NOTE, note);
        contentValues.put(KEY_BUDGET, "main");

        sqLiteDatabase.insert(TABLE_PAYMENTS, null, contentValues);
    }

    @Override
    public void deletePayment(int id) {
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_PAYMENTS + " WHERE " + KEY_ID + "='" + id + "'");

    }

    @Override
    public List<String> getNotes() {
        List<String> notes = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NOTES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int noteNameIndex = cursor.getColumnIndex(KEY_NOTE_NAME);
            do {
                notes.add(cursor.getString(noteNameIndex));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return notes;

    }

    @Override
    public void insertNote(String note) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NOTE_NAME, note);
        sqLiteDatabase.insert(TABLE_NOTES, null, contentValues);

    }


    @Override
    public void deleteNote(String note) {

        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NOTES + " WHERE " + KEY_NOTE_NAME + "='" + note + "'");


    }

    @Override
    public boolean noteExists(String note) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NOTES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int noteNameIndex = cursor.getColumnIndex(KEY_NOTE_NAME);
            do {
                if (cursor.getString(noteNameIndex).equals(note)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }

        cursor.close();

        return false;
    }


    @Override
    public List<String> getPayments() {
        List<String> paymentsInJson = new ArrayList<>();
        Gson gson = new Gson();
        Cursor cursor = sqLiteDatabase.query(TABLE_PAYMENTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int dateIndex = cursor.getColumnIndex(KEY_DATE);
            int amountIndex = cursor.getColumnIndex(KEY_AMOUNT);
            int noteIndex = cursor.getColumnIndex(KEY_NOTE);
            do {
                paymentsInJson.add(gson.toJson(new Payment(cursor.getInt(idIndex), cursor.getLong(dateIndex), cursor.getDouble(amountIndex), cursor.getString(noteIndex))));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return paymentsInJson;

    }


    @Override
    public Map getOptions() {
        return null;
    }


    @Override
    public boolean getInitialSetup() {

        String query = "SELECT " + KEY_VALUE + " FROM " + TABLE_SETTINGS + " WHERE " + KEY_OPTION + " = '" + INITIAL_SETUP + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            if (cursor.getInt(0) == 0) {
                return false;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public void updateInitialSetup(boolean initialSetup) {
        int value;
        if (initialSetup) {
            value = 1;
        } else {
            value = 0;
        }
        String query = ("UPDATE " + TABLE_SETTINGS + " SET " + KEY_VALUE + " = '" + value + "' WHERE " + KEY_OPTION + " = '" + INITIAL_SETUP + "'");
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void setStartDate(Integer startDate) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_OPTION, START_DATE);
        contentValues.put(KEY_VALUE, startDate);
        sqLiteDatabase.insert(TABLE_SETTINGS, null, contentValues);
    }

    @Override
    public Integer getStartDate() {
        String query = "SELECT " + KEY_VALUE + " FROM " + TABLE_SETTINGS + " WHERE " + KEY_OPTION + " = '" + START_DATE + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Log.e("dbSD", Integer.toString(cursor.getInt(0)));
            return cursor.getInt(0);
        }

        return -1;
    }

    @Override
    public void updateStartDate(Integer startDate) {
        Log.e("dbHelperUpdate", Integer.toString(startDate));
        String query = ("UPDATE " + TABLE_SETTINGS + " SET " + KEY_VALUE + " = '" + startDate + "' WHERE " + KEY_OPTION + " = '" + START_DATE + "'");
        sqLiteDatabase.execSQL(query);

    }

    ///

    @Override
    public void setLanguage(String language) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_OPTION, LANGUAGE);
        contentValues.put(KEY_VALUE, language);
        sqLiteDatabase.insert(TABLE_SETTINGS, null, contentValues);


    }

    @Override
    public String getLanguage() {
        String query = "SELECT " + KEY_VALUE + " FROM " + TABLE_SETTINGS + " WHERE " + KEY_OPTION + " = '" + LANGUAGE + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            return cursor.getString(0);
        }

        return "Упс!";
    }

    @Override
    public void updateLanguage(String language) {

        String query = ("UPDATE " + TABLE_SETTINGS + " SET " + KEY_VALUE + " = '" + language + "' WHERE " + KEY_OPTION + " = '" + LANGUAGE + "'");
        sqLiteDatabase.execSQL(query);
    }

    ///

    @Override
    public void setCurrency(String currency) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_OPTION, CURRENCY);
        contentValues.put(KEY_VALUE, currency);
        sqLiteDatabase.insert(TABLE_SETTINGS, null, contentValues);


    }

    @Override
    public String getCurrency() {
        String query = "SELECT " + KEY_VALUE + " FROM " + TABLE_SETTINGS + " WHERE " + KEY_OPTION + " = '" + CURRENCY + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            return cursor.getString(0);
        }

        return "Упс! ";
    }

    @Override
    public void updateCurrency(String currency) {

        String query = ("UPDATE " + TABLE_SETTINGS + " SET " + KEY_VALUE + " = '" + currency + "' WHERE " + KEY_OPTION + " = '" + CURRENCY + "'");
        sqLiteDatabase.execSQL(query);
    }

    ///

    @Override
    public void setBudget(Double budget) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_OPTION, BUDGET);
        contentValues.put(KEY_VALUE, budget);
        sqLiteDatabase.insert(TABLE_SETTINGS, null, contentValues);
    }

    @Override
    public Double getBudget() {

        String query = "SELECT " + KEY_VALUE + " FROM " + TABLE_SETTINGS + " WHERE " + KEY_OPTION + " = '" + BUDGET + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            return cursor.getDouble(0);
        }

        return -1.0;


    }

    @Override
    public void updateBudget(Double budget) {

        String query = ("UPDATE " + TABLE_SETTINGS + " SET " + KEY_VALUE + " = '" + budget + "' WHERE " + KEY_OPTION + " = '" + BUDGET + "'");
        sqLiteDatabase.execSQL(query);
    }


}
