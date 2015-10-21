package lee.gs_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.app.Activity;
import android.app.ExpandableListActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView mExpandableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        mExpandableList = (ExpandableListView)findViewById(R.id.gameStatContent);

        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        ArrayList<String> arrayChildren = new ArrayList<String>();


       int i;
        arrayParents.add(Initializer.initialize_Blizz());


        Parent RPG = new Parent();
        RPG.setTitle("FPS");
        ArrayList<String> RPGChildren = new ArrayList<>();

        for(i=0;i<3;i++){
            RPGChildren.add("Template" + (i+1));
        }

        RPG.setArrayChildren(RPGChildren);
        arrayParents.add(RPG);

        //here we set the parents and the children
        /*for (int i = 0; i < 10; i++){
            //for each "i" create a new Parent object to set the title and the children
            Parent parent = new Parent();
            parent.setTitle("Parent " + i);

            arrayChildren = new ArrayList<String>();
            for (int j = 0; j < 10; j++) {
                arrayChildren.add("Child " + j);
            }
            parent.setArrayChildren(arrayChildren);

            //in this array we add the Parent object. We will use the arrayParents at the setAdapter
            arrayParents.add(parent);
        }*/

        //sets the adapter that provides data to the list.
        mExpandableList.setAdapter(new MyCustomAdapter(MainActivity.this, arrayParents));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

