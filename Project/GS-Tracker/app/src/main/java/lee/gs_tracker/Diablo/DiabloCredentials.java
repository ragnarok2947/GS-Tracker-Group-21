package lee.gs_tracker.Diablo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

import lee.gs_tracker.APIConnection;
import lee.gs_tracker.R;

public class DiabloCredentials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diablo_credentials);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diablo_credentials, menu);
        return true;
    }

    public String getBattleTag(){
        EditText mEdit   = (EditText)findViewById(R.id.editText);
        String BattleTag = mEdit.getText().toString();
        return BattleTag;
    }

    public void goToCharSelect(View view) throws Exception{

        DiabloAPIUser.btag = getBattleTag();
        String InputURL = "https://us.api.battle.net/d3/profile/" + DiabloAPIUser.btag + "/?locale=en_US&apikey=dzdyu73w47us458g5h89grjqgfs4ctpw";
        org.json.JSONObject obj = APIConnection.sentGet(InputURL);
        try{  //invalid credentials catch
            obj.get("battleTag");   //if it's invalid, it won't be found
            DiabloAPIUser.UseAPI();
            sendBattleTag(); //save battleTag internally for future use
            Intent intent = new Intent(this, DiabloCharSelect.class);
            startActivity(intent);

        }
        catch(Exception e){
            errorOnUser(view); //send an alert
        }


    }

    public Intent gotToCharSelect(Context context,String btag) throws Exception{
        DiabloAPIUser.btag = btag; //this is used when data is saved from previous session
        DiabloAPIUser.UseAPI();
        Intent intent = new Intent(context, DiabloCharSelect.class);
        return intent;

    }

    public void errorOnUser(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //alerts user if entered bad credentials
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

    public File createFile(String FileName, String Data){  //save internal file

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

    public void sendBattleTag(){  //save the battletag
        JSONObject User = new JSONObject();
        User.put("BattleTag", getBattleTag());
        createFile("DiabloUser.txt", User.toJSONString());
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
