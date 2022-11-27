package com.example.asyncapp5lab;

import android.os.AsyncTask;

import java.io.IOException;

public class DataLoader extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... params){
        try{
            return DataReader.getValuesFromApi(params[0]);
        } catch (IOException ex) {
            return String.format("Ups.. ", ex.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}
