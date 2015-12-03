package lee.gs_tracker.WoW;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;

import lee.gs_tracker.DownloadImageTask;
import lee.gs_tracker.MainActivity;
import lee.gs_tracker.R;

public class WoWWarlock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String From = intent.getStringExtra(WoWCredentials.EXTRA_MESSAGE);
        JSONParser Parser = new JSONParser();
        JSONObject User;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_wwarlock);

        try {
            Object obj = Parser.parse(From);
            User = (JSONObject)(obj);   //get JSONObject for this user
            obj = Parser.parse(WoWAPIUser.getStats(User).toString());
            JSONObject userStats = (JSONObject) (obj);

            new DownloadImageTask((ImageView) findViewById(R.id.imageView)) //set thumbnail and fields
                    .execute(WoWAPIUser.getCharPic(User));
            setFields(User, userStats);


        }
        catch(Exception E){
            User = null;
        }
    }




    public void setFields(JSONObject User, JSONObject userStats){
        Object UserStats = WoWAPIUser.getStats(User);

        ((TextView) findViewById(R.id.nameText)).setText(WoWAPIUser.getCharName(User));
        ((TextView)findViewById(R.id.serverText)).setText(WoWAPIUser.getServer(User));


        ((TextView)findViewById(R.id.levelEdit)).setText(WoWAPIUser.getLevel(User));
        ((TextView)findViewById(R.id.healthEdit)).setText(WoWAPIUser.getHealth(userStats));
        ((TextView)findViewById(R.id.strengthEdit)).setText(WoWAPIUser.getStrength(userStats));
        ((TextView)findViewById(R.id.agilityEdit)).setText(WoWAPIUser.getAgility(userStats));
        ((TextView)findViewById(R.id.intellectEdit)).setText(WoWAPIUser.getIntellect(userStats));
        ((TextView)findViewById(R.id.staminaEdit)).setText(WoWAPIUser.getStamina(userStats));
        ((TextView)findViewById(R.id.manaEdit)).setText(WoWAPIUser.getClassPower(userStats));
        ((TextView)findViewById(R.id.spellCritEdit)).setText(WoWAPIUser.getSpellCrit(userStats));
        ((TextView)findViewById(R.id.specEdit)).setText(WoWAPIUser.getSpec(User));

        Spinner dropdown = (Spinner)findViewById(R.id.armorList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, WoWAPIUser.generateItemList(User));   //for armor and talents
        dropdown.setAdapter(adapter);


        Spinner dd = (Spinner)findViewById(R.id.talentList);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, WoWAPIUser.generateTalentList(User));
        dd.setAdapter(adapt);



    }

    public void deleteTemplate(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Saved User?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                File file = getBaseContext().getFileStreamPath("WoWUser.txt");   //delete the file and return to the main menu
                file.delete();
                Intent intent = new Intent(WoWWarlock.this, MainActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void resetTemplate(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reset Saved User?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                File file = getBaseContext().getFileStreamPath("WoWUser.txt");  //will delete the file but return the credentials page
                file.delete();
                Intent intent = new Intent(WoWWarlock.this, WoWCredentials.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
