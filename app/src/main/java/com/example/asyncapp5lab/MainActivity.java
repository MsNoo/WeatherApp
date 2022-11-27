package com.example.asyncapp5lab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvStatus;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvStatus = findViewById(R.id.tvStatus);
    }

    public void onBtnGetDataClick(View view){
        this.tvStatus.setText("loading something for you ...");
        getDataByAsyncTask();
        Toast.makeText(this, "Trust me, it will load some day", Toast.LENGTH_LONG).show();
    }

    public void getDataByAsyncTask(){
        new DataLoader(){
            @Override
            public void onPostExecute(String result){
                tvStatus.setText(result);
            }
        }.execute(Constants.METEO_URL);
    }
}