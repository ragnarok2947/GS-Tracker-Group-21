package com.example.lee.sampleapp;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.Object;
import android.os.StrictMode;
import android.os.Bundle;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    public void get_name(View view){  //runs wutton is clicked
        EditText mEdit   = (EditText)findViewById(R.id.level);  //get the specific text box
        String person_name;
        person_name = mEdit.getText().toString();  //person name
        String Server;
        mEdit = (EditText)findViewById(R.id.health);
        Server = mEdit.getText().toString();      //get the user-inputted realm
        JSONObject User = HttpURLConnectionExample.sentGet(person_name, Server);//get the JSON object that is returned from the API call
        ((EditText)findViewById(R.id.level)).setText("Level: " + HttpURLConnectionExample.getLevel(User));
        ((TextView)findViewById(R.id.textView)).setText("Hello, " + person_name);//HttpURLConnectionExample.sentGet(person_name));
        ((EditText)findViewById(R.id.health)).setText("Health: " + HttpURLConnectionExample.getHealth(User));  //set the text box values accordingly
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //((EditText)findViewById(R.id.charname)).setText(HttpURLConnectionExample.sentGet());
        //((TextView)findViewById(R.id.textView)).setText(HttpURLConnectionExample.sentGet());


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
