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
        try{   //create the API call using the user input
            URL url = new URL("https://us.api.battle.net/wow/character/"+ServerInput+"/"+UserInput+"?fields=stats&locale=en_US&apikey=bmhx5s3efzdhghwvjpr778zhg4a6yhnd");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection(); //open the connection
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder()  //had to put this to allow an api call from main
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); //get the response stream

            String inputLine;
            StringBuffer response = new StringBuffer();


            while((inputLine = in.readLine()) != null){  //put the response stream into a string
                response.append(inputLine);
            }

            in.close();

            JSONObject User = new JSONObject(response.toString()); //create a new JSON object from that string it is parsed



            return User;  //return the JSON object
        }
        catch(Exception e){return null;}

    }


    public static String getLevel(JSONObject User){
        try {
            return User.get("level").toString();  //find the "level" pair name in the JSON and return it
        }
        catch(Exception e){return e.toString();}

    }

    public static String getHealth(JSONObject User){
        try {
            JSONObject Stats = new JSONObject(User.get("stats").toString());//find the JSON object with pair name "stats"
            return Stats.get("health").toString();   //return the value of health
        }
        catch(Exception e){return e.toString();}
    }
}




