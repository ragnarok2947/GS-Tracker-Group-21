package com.example.lee.sampleapp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;

import android.os.StrictMode;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.Object;



import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

    public static void main() throws Exception {
        HttpURLConnectionExample http = new HttpURLConnectionExample();

        //http.sentGet();


    }


    public static JSONObject sentGet(String UserInput, String ServerInput) {
        try{
            URL url = new URL("https://us.api.battle.net/wow/character/"+ServerInput+"/"+UserInput+"?fields=stats&locale=en_US&apikey=bmhx5s3efzdhghwvjpr778zhg4a6yhnd");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();


            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            in.close();

            JSONObject User = new JSONObject(response.toString());



            return User;//User.get("realm").toString();
        }
        catch(Exception e){return null;}

    }


    public static String getLevel(JSONObject User){
        try {
            return User.get("level").toString();
        }
        catch(Exception e){return e.toString();}

    }

    public static String getHealth(JSONObject User){
        try {
            JSONObject Stats = new JSONObject(User.get("stats").toString());
            return Stats.get("health").toString();
        }
        catch(Exception e){return e.toString();}
    }
}




