package com.example.asyncapp5lab;

import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
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
            String airTemperatureStr = "";
            String conditionCodeStr = "";
            Integer airTemperature = null;

            //forecastTimestamps Arr
            JSONArray forecastTimestampsArr = jData.getJSONArray("forecastTimestamps");
            for(int i = 0; i < forecastTimestampsArr.length(); i++){
                JSONObject innerObject = forecastTimestampsArr.getJSONObject(i);
                conditionCodeStr = innerObject.getString("conditionCode");
                airTemperature = innerObject.getInt("airTemperature");
                airTemperatureStr = String.valueOf(airTemperature);
            }
            result = String.format("Place: %s\nCondition: %s\nTemperature: %s", nameNode, conditionCodeStr,airTemperatureStr);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
