package lee.gs_tracker.WoW;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

import lee.gs_tracker.R;
import lee.gs_tracker.gsCustom.QuickQuestionDialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
public class WoWCredentials extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "WoWRogue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_wcredentials);
    }

    public String getCharName(){
        EditText mEdit   = (EditText)findViewById(R.id.editText);   //get Char name and servername from text fields
        String CharName = mEdit.getText().toString();
        return CharName;
    }

    public String getServerName(){
        EditText mEdit = (EditText)findViewById(R.id.editText2);
        String ServerName = mEdit.getText().toString();
        return ServerName;
    }


    public File createFile(String FileName, String Data){

        File file = new File(getFilesDir(),FileName);
        FileOutputStream outputStream;
        String string = "";
        try {
            outputStream = openFileOutput(FileName, Context.MODE_PRIVATE);    //save the file internally
            outputStream.write(Data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public void sendCharData(){
        JSONObject User = new JSONObject();
        User.put("Server", getServerName());
        User.put("CharName", getCharName());
        createFile("WoWUser.txt", User.toJSONString());  //this will create a JSON Object and save the JSON String to a file
    }



    public void goToTemplate(View view){
        String InputURL = "https://us.api.battle.net/wow/character/" + getServerName() + "/" + getCharName() + "?fields=stats&locale=en_US&apikey=bmhx5s3efzdhghwvjpr778zhg4a6yhnd";
        org.json.JSONObject Obj = null;

            Obj = WoWAPIUser.sentGet(InputURL);  //go to template based on credentials data


        if(Obj == null){
            //invalid credentials
            errorOnUser(view);

        }
        else {
            sendCharData(); //save the credential data
            String Class = WoWAPIUser.getCharClass(Obj);
            if (Class.equals("Rogue")) {                         //------------Depending on what class, go to the template
                Intent intent = new Intent(this, WoWRogue.class);
                intent.putExtra(EXTRA_MESSAGE, Obj.toString());
                startActivity(intent);
            } else if (Class.equals("Hunter")) {
                Intent intent = new Intent(this, WoWHunter.class);
                intent.putExtra(EXTRA_MESSAGE, Obj.toString());
                startActivity(intent);

            } else if (Class.equals("Paladin")) {
                Intent intent = new Intent(this, WoWPaladin.class);
                intent.putExtra(EXTRA_MESSAGE, Obj.toString());
                startActivity(intent);
            } else if (Class.equals("Warrior")) {
                Intent intent = new Intent(this, WoWWarrior.class);
                intent.putExtra(EXTRA_MESSAGE, Obj.toString());
                startActivity(intent);

            } else if (Class.equals("Mage")) {
                Intent intent = new Intent(this, WoWMage.class);
                intent.putExtra(EXTRA_MESSAGE, Obj.toString());
                startActivity(intent);

            } else if (Class.equals("Shaman")) {
                Intent intent = new Intent(this, WoWShaman.class);
                intent.putExtra(EXTRA_MESSAGE, Obj.toString());
                startActivity(intent);

            } else if (Class.equals("Warlock")) {
                Intent intent = new Intent(this, WoWWarlock.class);
                intent.putExtra(EXTRA_MESSAGE, Obj.toString());
                startActivity(intent);

            } else if (Class.equals("Druid")) {
                Intent intent = new Intent(this, WoWDruid.class);
                intent.putExtra(EXTRA_MESSAGE, Obj.toString());
                startActivity(intent);

            } else { //let the user know this class isn't supported
                notSupported(view);
            }
        }
    }

    public void notSupported(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Class is Currently not Supported");

        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public void errorOnUser(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error: Please input valid credentials");

        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public Intent goToTemplate(Context context, String ServerName, String CharName){          //goes to template based on already internally saved data
        String InputURL = "https://us.api.battle.net/wow/character/" + ServerName + "/" + CharName + "?fields=stats&locale=en_US&apikey=bmhx5s3efzdhghwvjpr778zhg4a6yhnd";
        org.json.JSONObject Obj = WoWAPIUser.sentGet(InputURL);

        String Class = WoWAPIUser.getCharClass(Obj);
        if(Class.equals("Rogue")){
            Intent intent = new Intent(context, WoWRogue.class);
            intent.putExtra(EXTRA_MESSAGE, Obj.toString());
            return intent;
            //startActivity(intent);
        }
        else if(Class.equals("Hunter")){
            Intent intent = new Intent(context, WoWHunter.class);
            intent.putExtra(EXTRA_MESSAGE, Obj.toString());
            return intent;
        }
        else if(Class.equals("Warrior")){
            Intent intent = new Intent(context, WoWWarrior.class);
            intent.putExtra(EXTRA_MESSAGE, Obj.toString());
            return intent;
        }
        else if(Class.equals("Mage")){
            Intent intent = new Intent(context, WoWMage.class);
            intent.putExtra(EXTRA_MESSAGE, Obj.toString());
            return intent;

        }
        else if(Class.equals("Paladin")){
            Intent intent = new Intent(context, WoWPaladin.class);
            intent.putExtra(EXTRA_MESSAGE, Obj.toString());
            return intent;
        }
        else if(Class.equals("Warlock")){
            Intent intent = new Intent(context, WoWWarlock.class);
            intent.putExtra(EXTRA_MESSAGE, Obj.toString());
            return intent;

        }
        else if(Class.equals("Shaman")){
            Intent intent = new Intent(context, WoWShaman.class);
            intent.putExtra(EXTRA_MESSAGE, Obj.toString());
            return intent;

        }
        else if(Class.equals("Druid")){
            Intent intent = new Intent(context, WoWDruid.class);
            intent.putExtra(EXTRA_MESSAGE, Obj.toString());
            return intent;

        }


        return null;
    }



}
