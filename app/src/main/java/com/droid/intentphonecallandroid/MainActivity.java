package com.droid.intentphonecallandroid;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent phoneIntent;
    private Snackbar snackbar;
    private AlertDialog.Builder builder;
    private View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Nous Appeler", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                snackbar=Snackbar.make(view,R.string.snackbar_text,Snackbar.LENGTH_LONG).setAction(R.string.snackbar_action,myOnClickListener);
                snackbar.setActionTextColor(Color.CYAN);
                snackbar.show();*/
                openDialog();
            }
        });

        /*myOnClickListener=new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openDialog();
            }
        };*/

    }

    public void openDialog(){
        builder=new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm_dialog_title);
        builder.setMessage(R.string.confirm_dialog_message);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //fermeture de la boite de dialog
                dialogInterface.dismiss();

                //Envoi vers appel
                Toast.makeText(MainActivity.this, "Appel", Toast.LENGTH_SHORT).show();
                phoneIntent=new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:+33600000000"));

                        /*if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }*/
                startActivity(phoneIntent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Appel non accepté
                Toast.makeText(MainActivity.this, "Appel annulé", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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
