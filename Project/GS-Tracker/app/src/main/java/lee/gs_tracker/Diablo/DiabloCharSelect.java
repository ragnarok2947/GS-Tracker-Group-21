package lee.gs_tracker.Diablo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import lee.gs_tracker.R;

public class DiabloCharSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diablo_char_select);

        LinearLayout linear = (LinearLayout) findViewById(R.id.CharSelect);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setGravity(Gravity.CENTER);

        try
        {
            JSONArray charArray = DiabloAPIUser.getCharArray();

            LayoutParams param = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            Button[] btn = new Button[charArray.length()];

            JSONObject cur;

            for (int i = 0; i < charArray.length(); i++) {
                cur = charArray.getJSONObject(i);
                btn[i] = new Button(getApplicationContext());
                btn[i].setText(cur.get("name").toString());
                btn[i].setTextColor(Color.parseColor("#000000"));
                btn[i].setTextSize(20);
                btn[i].setHeight(10);
                btn[i].setLayoutParams(param);
                btn[i].setPadding(15, 5, 15, 5);
                //btn[i].setGravity(Gravity.CENTER_VERTICAL);

                linear.addView(btn[i]);

                btn[i].setOnClickListener(handleOnClick(btn[i], i));
            }

        }
        catch(Exception e){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diablo_char_select, menu);
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

    View.OnClickListener handleOnClick(final Button button, final int i) {

        final Intent intent = new Intent(this, DiabloStats.class);

        return new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    DiabloAPIUser.UseCharAPI(i);
                }
                catch(Exception e){

                }

                startActivity(intent);
            }
        };
    }
}

/*<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent" android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin"
    tools:context="lee.gs_tracker.DiabloCharSelect">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceMedium"
        android:gravity="center"
        android:text="Select the character you would like to view on this account:"
        android:id="@+id/CharTop"
        android:layout_alignParentTop="true"
        />

</RelativeLayout>*/
