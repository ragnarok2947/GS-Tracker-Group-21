package cop4331c.gscustom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RpgNotes extends AppCompatActivity
{

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.notes_activity);

      if (getIntent().getStringExtra("gamename") != null)
         setTitle(getIntent().getStringExtra("gamename"));
   }
}
