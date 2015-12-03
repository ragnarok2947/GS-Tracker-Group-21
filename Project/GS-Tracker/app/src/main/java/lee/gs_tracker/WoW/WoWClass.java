package lee.gs_tracker.WoW;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Context;

import java.io.File;

import lee.gs_tracker.MainActivity;
import lee.gs_tracker.R;

public class WoWClass extends AppCompatActivity{

    public void deleteTemplate(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);                //prompt the user to delete the template, find the file and delete it internally
        builder.setTitle("Delete Saved User?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                File file = getBaseContext().getFileStreamPath("WoWUser.txt");
                file.delete();
                Intent intent = new Intent(context, MainActivity.class);
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

    public void resetTemplate(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);   //reset is the same except it goes to the WoWCredentials activity
        builder.setTitle("Reset Saved User?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                File file = getBaseContext().getFileStreamPath("WoWUser.txt");
                file.delete();
                Intent intent = new Intent(context, WoWCredentials.class);
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
