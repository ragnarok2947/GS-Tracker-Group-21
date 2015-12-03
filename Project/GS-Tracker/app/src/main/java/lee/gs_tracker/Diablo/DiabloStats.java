package lee.gs_tracker.Diablo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import lee.gs_tracker.R;

public class DiabloStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diablo_stats);

        LinearLayout linear = (LinearLayout) findViewById(R.id.DiabloStats);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setGravity(Gravity.CENTER);

        LayoutParams param = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        try {

            JSONObject cur;

            //character name
            TextView name = new TextView(this);
            name.setText(DiabloAPIUser.charObj.get("name").toString());
            name.setLayoutParams(param);
            name.setTextSize(25);
            linear.addView(name);

            //character level and class
            TextView levelclass = new TextView(this);
            String diabloclass = DiabloAPIUser.charObj.get("class").toString();
            diabloclass = diabloclass.substring(0,1).toUpperCase() + diabloclass.substring(1);
            levelclass.setText("Level " + DiabloAPIUser.charObj.get("level").toString() + " " + diabloclass);
            levelclass.setLayoutParams(param);
            levelclass.setTextSize(20);
            linear.addView(levelclass);

            //character paragon level
            TextView plevel = new TextView(this);
            plevel.setText("Paragon level: " + DiabloAPIUser.charObj.get("paragonLevel").toString());
            plevel.setLayoutParams(param);
            plevel.setTextSize(20);
            linear.addView(plevel);

            //stats start
            cur = DiabloAPIUser.charObj.getJSONObject("stats");

            //life
            TextView life = new TextView(this);
            life.setText("Life: " + cur.get("life").toString());
            life.setLayoutParams(param);
            life.setTextSize(20);
            linear.addView(life);

            //damage
            TextView damage = new TextView(this);
            damage.setText("Damage per second: " + cur.get("damage").toString());
            damage.setLayoutParams(param);
            damage.setTextSize(20);
            linear.addView(damage);

            //healing
            TextView healing = new TextView(this);
            healing.setText("Healing: " + cur.get("healing").toString());
            healing.setLayoutParams(param);
            healing.setTextSize(20);
            linear.addView(healing);

            //armor
            TextView armor = new TextView(this);
            armor.setText("Armor: " + cur.get("armor").toString());
            armor.setLayoutParams(param);
            armor.setTextSize(20);
            linear.addView(armor);

            //strength
            TextView strength = new TextView(this);
            strength.setText("Strength: " + cur.get("strength").toString());
            strength.setLayoutParams(param);
            strength.setTextSize(18);
            linear.addView(strength);

            //dexterity
            TextView dexterity = new TextView(this);
            dexterity.setText("Dexterity: " + cur.get("dexterity").toString());
            dexterity.setLayoutParams(param);
            dexterity.setTextSize(18);
            linear.addView(dexterity);

            //intelligence
            TextView intelligence = new TextView(this);
            intelligence.setText("Intelligence: " + cur.get("intelligence").toString());
            intelligence.setLayoutParams(param);
            intelligence.setTextSize(18);
            linear.addView(intelligence);

            /*TextView  = new TextView(this);
            .setText(DiabloAPIUser.charObj.get("name").toString());
            .setLayoutParams(param);
            .setTextSize(25);
            linear.addView();*/

            //Active skills
            TextView activeskills = new TextView(this);
            TextView activeskilltext = new TextView(this);
            String skillbar = "";
            JSONArray skills;
            try {
                activeskills.setText("Active Skills:");
                activeskills.setLayoutParams(param);
                activeskills.setTextSize(15);
                activeskills.setPadding(0, 5, 0, 0);

                activeskilltext.setLayoutParams(param);
                activeskilltext.setPadding(0, 5, 0, 10);
                activeskilltext.setTextSize(15);



                skills = DiabloAPIUser.getActiveSkillArray();

                cur = skills.getJSONObject(0).getJSONObject("skill");
                skillbar = skillbar.concat(cur.get("name").toString());

                for (int i = 1; i < skills.length(); i++) {
                    cur = skills.getJSONObject(i).getJSONObject("skill");
                    skillbar = skillbar.concat(" | " + cur.get("name").toString());

                }

                activeskilltext.setText(skillbar);

            }
            catch(Exception e){
                activeskilltext.setText("This character doesn't have any active skills equipped");
            }
            linear.addView(activeskills);
            linear.addView(activeskilltext);

            //Passive skills
            TextView passiveskills = new TextView(this);
            TextView passiveskilltext = new TextView(this);

            try {
                passiveskills.setText("Passive Skills:");
                passiveskills.setLayoutParams(param);
                passiveskills.setTextSize(15);
                passiveskills.setPadding(0, 5, 0, 0);

                passiveskilltext.setLayoutParams(param);
                passiveskilltext.setPadding(0, 5, 0, 10);
                passiveskilltext.setTextSize(15);



                //Passive Skills, delimited by "|"
                skills = DiabloAPIUser.getPassiveSkillArray();
                skillbar = "";//empty the skillbar
                cur = skills.getJSONObject(0).getJSONObject("skill");
                skillbar = skillbar.concat(cur.get("name").toString());

                for (int i = 1; i < skills.length(); i++) {
                    cur = skills.getJSONObject(i).getJSONObject("skill");
                    skillbar = skillbar.concat(" | " + cur.get("name").toString());

                }

                passiveskilltext.setText(skillbar);

            }
            catch(Exception e){
                passiveskilltext.setText("This character doesn't have any passive skills");
            }

            linear.addView(passiveskills);
            linear.addView(passiveskilltext);

        }

        catch(Exception e){

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diablo_stats, menu);
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
