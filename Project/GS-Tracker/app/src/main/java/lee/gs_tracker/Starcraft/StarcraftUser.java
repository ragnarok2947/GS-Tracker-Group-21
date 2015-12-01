package lee.gs_tracker.Starcraft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import lee.gs_tracker.DownloadImageTask;
import lee.gs_tracker.R;
import lee.gs_tracker.WoW.WoWAPIUser;
import lee.gs_tracker.WoW.WoWCredentials;

public class StarcraftUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String From = intent.getStringExtra(StarcraftCredentials.EXTRA_MESSAGE);
        JSONParser Parser = new JSONParser();
        JSONObject User;




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starcraft_user);

        try {
            Object obj = Parser.parse(From);
            User = (JSONObject)(obj);   //get JSONObject for this user
            //JSONObject userStats = (JSONObject) (obj);

            setFields(User);

        }
        catch(Exception E){
            User = null;
        }

    }

    public void setFields(JSONObject User){
        JSONObject Career = StarcraftAPIUser.getCareer(User);
        ((TextView) findViewById(R.id.profileText)).setText((StarcraftAPIUser.getProfileName(User)));
        ((TextView) findViewById(R.id.textView12)).setText((StarcraftAPIUser.getPrimaryRace(Career)));
        ((TextView) findViewById(R.id.textView14)).setText((StarcraftAPIUser.getTerranWins(Career)));
        ((TextView) findViewById(R.id.textView25)).setText((StarcraftAPIUser.getprotossWins(Career)));
        ((TextView) findViewById(R.id.textView15)).setText((StarcraftAPIUser.getzergWins(Career)));
        ((TextView) findViewById(R.id.textView27)).setText((StarcraftAPIUser.getSoloRank(Career)));
        ((TextView) findViewById(R.id.textView28)).setText((StarcraftAPIUser.getTeamRank(Career)));
        ((TextView) findViewById(R.id.totalGamesEdit)).setText((StarcraftAPIUser.getTotalGames(Career)));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_starcraft_user, menu);
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
