package com.example.asyncapp5lab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        String conditionCodeStr ="";

        try{
            JSONObject jData = new JSONObject(data);
            JSONObject placeNode = jData.getJSONObject("place");
            String nameNode = placeNode.getString("name");

            //forecastTimestamps Arr
            JSONArray forecastTimestampsArr = placeNode.getJSONArray("forecastTimestamps");
            for(int i = 0; i < forecastTimestampsArr.length(); i++){
                JSONObject innerObject = forecastTimestampsArr.getJSONObject(i);
                conditionCodeStr = innerObject.getString("conditionCode");
            }
            result = String.format("Place: %s\n, Temperature: %s",nameNode, conditionCodeStr);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
