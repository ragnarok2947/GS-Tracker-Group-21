package lee.gs_tracker.Starcraft;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

import lee.gs_tracker.R;
import lee.gs_tracker.WoW.WoWAPIUser;

public class StarcraftCredentials extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "StarCraft";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starcraft_credentials);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_starcraft_credentials, menu);
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


    public String getProfileName(){
        EditText mEdit   = (EditText)findViewById(R.id.editText);
        String profileName = mEdit.getText().toString();
        return profileName;
    }

    public String getID(){
        EditText mEdit = (EditText)findViewById(R.id.editText2);
        String ID = mEdit.getText().toString();
        return ID;
    }


    public File createFile(String FileName, String Data){

        File file = new File(getFilesDir(),FileName);
        FileOutputStream outputStream;
        String string = "";
        try {
            outputStream = openFileOutput(FileName, Context.MODE_PRIVATE);
            outputStream.write(Data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public void sendCharData(){
        JSONObject User = new JSONObject();
        User.put("ProfileName", getProfileName());
        User.put("ID", getID());
        createFile("StarCraftUser.txt", User.toJSONString());
        //new MainActivity().writeToFile("WoWUser.txt", User.toJSONString());
    }



    public void goToTemplate(View view) {
        String InputURL = "https://us.api.battle.net/sc2/profile/" + getID() + "/1/" + getProfileName() + "/?locale=en_US&apikey=bmhx5s3efzdhghwvjpr778zhg4a6yhnd";
        org.json.JSONObject Obj = null;

        Obj = WoWAPIUser.sentGet(InputURL);


        if (Obj == null) {
            //invalid credentials --

           /* new QuickQuestionDialog(this, null, null, "Please input valid credentials for your WoW Character", "Error",
                    new int[]{0});*/
            errorOnUser(view);

        }

        else{ //found user
            Intent intent = new Intent(this, StarcraftUser.class);
            intent.putExtra(EXTRA_MESSAGE, Obj.toString());
            startActivity(intent);
        }
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
}
