package lee.gs_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import java.io.File;
import java.io.FileOutputStream;
import android.content.Context;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.Scanner;

import java.util.ArrayList;

import lee.gs_tracker.Diablo.DiabloCredentials;
import lee.gs_tracker.Starcraft.StarcraftCredentials;
import lee.gs_tracker.WoW.WoWCredentials;
import lee.gs_tracker.gsCustom.GSCustom;

public class MainActivity extends AppCompatActivity implements
      ExpandableListView.OnChildClickListener {
    private ExpandableListView mExpandableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mExpandableList = (ExpandableListView) findViewById(R.id.gameStatContent);  //Create the DropDown Menus

        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        ArrayList<Child> customChildren = new ArrayList<Child>();


        arrayParents.add(Initializer.initialize_Blizz());  //Initialize the Blizzard Menus

        Parent Custom = new Parent();
        Custom.setTitle("Custom");    //Set Custom Templates Menu
        Child CustomChild = new Child();
        CustomChild.ChildName = "Custom";
        CustomChild.Title = "Custom Templates";

        customChildren.add(CustomChild);

        Custom.setArrayChildren(customChildren);

        arrayParents.add(Custom);

        //sets the adapter that provides data to the list.
        mExpandableList.setAdapter(new MyCustomAdapter(MainActivity.this, arrayParents));
        mExpandableList.setOnChildClickListener(this);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id)  //sense which template to go to
    {
        String TagName = view.getTag().toString();

        if(TagName == "WoW") { //World of Warcraft
            File file = getBaseContext().getFileStreamPath("WoWUser.txt");
            if(file.exists()){       //if a WoW is is already in, grab it

                try {
                    String data = new Scanner(file).next();
                    JSONObject User = (JSONObject)JSONValue.parse(data);
                    startActivity(new WoWCredentials().goToTemplate(this, User.get("Server").toString(), User.get("CharName").toString()));  //try to get the Server and Charname from the file and go to the template

                }
                catch(Exception E){
                    file.delete(); //bad user
                    E.printStackTrace();
                }

            }
            else{
                Intent intent = new Intent(this, WoWCredentials.class);  //else prompt for credentials
                startActivity(intent);
            }

        }
        else if(TagName == "StarcraftII"){
            File file = getBaseContext().getFileStreamPath("StarCraftUser.txt");  //same with StarCraft
            if(file.exists()){
                try{
                    String data = new Scanner(file).next();
                    JSONObject User = (JSONObject)JSONValue.parse(data);
                    startActivity(new StarcraftCredentials().goToTemplate(this, User.get("ID").toString(), User.get("ProfileName").toString()));
                }
                catch(Exception E){
                    file.delete();
                    E.printStackTrace();
                }
            }
            else {
                Intent intent = new Intent(this, StarcraftCredentials.class);
                startActivity(intent);
            }
        }
        if(TagName == "DiabloIII"){
            File file = getBaseContext().getFileStreamPath("DiabloUser.txt");   //and Diablo
            if(file.exists()){
                try{
                    String data = new Scanner(file).next();
                    JSONObject User = (JSONObject)JSONValue.parse(data);
                    startActivity(new DiabloCredentials().gotToCharSelect(this, User.get("BattleTag").toString()));
                }
                catch(Exception E){
                    file.delete();
                    E.printStackTrace();
                }
            }
            else {
                Intent intent = new Intent(this, DiabloCredentials.class);
                startActivity(intent);
            }
        }

        else if(TagName == "Custom"){
            Intent intent = new Intent(this, GSCustom.class);  //For Custom Templates, just go to the Custom Menu
            startActivity(intent);
        }
        return true;
    }

}

