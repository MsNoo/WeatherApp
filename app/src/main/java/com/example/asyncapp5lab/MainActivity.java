package com.example.asyncapp5lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvView;
    private TextView tvStatus;
    private ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvView = findViewById(R.id.lvItems);
        this.tvStatus = findViewById(R.id.tvStatus);
        this.listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        this.lvView.setAdapter(this.listAdapter);
    }

    public void onBtnGetDataClick(View view){
        this.tvStatus.setText("loading");
        getDataByAsyncTask();
        Toast.makeText(this, "I am here", Toast.LENGTH_LONG).show();
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