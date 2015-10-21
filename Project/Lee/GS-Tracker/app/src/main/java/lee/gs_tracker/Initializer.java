package lee.gs_tracker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.app.Activity;
import android.app.ExpandableListActivity;
import java.util.ArrayList;

public class Initializer {


    public static Parent initialize_Blizz(){
        ArrayList<String> Games = new ArrayList<String>();

        Parent BlizzardGames = new Parent();
        BlizzardGames.setTitle("Blizzard Games");
        int i;

        Games.add("World of Warcraft");
        Games.add("Diablo III");
        Games.add("Starcraft II");

        BlizzardGames.setArrayChildren(Games);

        return BlizzardGames;
    }



    public static void initialize_templates(ArrayList<Parent> Parents){




    }


}
