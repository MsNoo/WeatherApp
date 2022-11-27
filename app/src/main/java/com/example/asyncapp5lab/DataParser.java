package com.example.asyncapp5lab;

import android.os.Build;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataParser {
    public static String getVilniusWeather(InputStream stream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line = "";
        String data = "";
        while (line != null){
            line = bufferedReader.readLine();
            data = data + line;
        }

        String result = "";
        try{
            JSONObject jData = new JSONObject(data);
            JSONObject placeNode = jData.getJSONObject("place");
            String nameNode = placeNode.getString("name");
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            result = String.format("%s\nCurrent time: %s %s\n",nameNode, currentDate, currentTime);

            String time = "";
            String conditionCodeStr = "";
            String airTemperature = "";
            JSONArray forecastTimestampsArr = jData.getJSONArray("forecastTimestamps");
            for(int i = 0; i < forecastTimestampsArr.length(); i++){
                JSONObject innerObject = forecastTimestampsArr.getJSONObject(i);
                String tempStr = currentTime.substring(0,2);
                if (innerObject.getString("forecastTimeUtc").equals(currentDate + " " + tempStr + ":00:00")){
                    time = innerObject.getString("forecastTimeUtc");
                    conditionCodeStr = innerObject.getString("conditionCode");
                    airTemperature = innerObject.getString("airTemperature");
                    result += String.format("%s\n%s\n%s°C\n", time, conditionCodeStr, airTemperature);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
