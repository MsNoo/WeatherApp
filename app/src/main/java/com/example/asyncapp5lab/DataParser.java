package com.example.asyncapp5lab;

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
        try{
            JSONObject jData = new JSONObject(data);
            JSONObject placeNode = jData.getJSONObject("place");
            JSONObject coordinatesNode = placeNode.getJSONObject("coordinates");
            String lat = coordinatesNode.getString("latitude");
            String lon = coordinatesNode.getString("longitude");
            String administrativeDivision = placeNode.getString("administrativeDivision");
            result = String.format("location name: %s,\n lat: %s,\n lon: %s",administrativeDivision, lat, lon);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
