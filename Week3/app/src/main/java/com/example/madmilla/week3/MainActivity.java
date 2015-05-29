package com.example.madmilla.week3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//1.    Maak een verjaardagskalender.
//  a.  In een database worden naam, maand en dag onthouden.
//  b.  Een view die verjaardagen in huidige maand toont. Geordend op dagnummer.
//  c.  In het menu kan je een view oproepen waarmee je een nieuwe verjaardag kunt toevoegen.
//  d.  In  het contextmenu kan je een verjaardag verwijderen.

//  2. Met een ‘swipe’, een horizontale veegbeweging met de vinger,  kan je naar volgende of vorige maand gaan.

public class MainActivity extends ActionBarActivity {

    TextView idView;
    EditText nameBox;
    EditText dateBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = (TextView) findViewById(R.id.bdayID);
        nameBox = (EditText) findViewById(R.id.bdayName);
        dateBox = (EditText) findViewById(R.id.bdayDate);

    }

    public void newBday(View view){
        DBHandler dbHandler = new DBHandler(this,null,null,1);
        String date = dateBox.getText().toString();
        Verjaardag verjaardag = new Verjaardag(nameBox.getText().toString(), dateBox.getText().toString());

        dbHandler.addVerjaardag(verjaardag);
        nameBox.setText("");
        dateBox.setText("");
    }

    public void showBday(View view){
        DBHandler dbHandler = new DBHandler(this,null,null,1);
        Verjaardag verjaardag = dbHandler.findName(nameBox.getText().toString());

        if(verjaardag != null){
            idView.setText(String.valueOf(verjaardag.getDate()));
        }else{
            idView.setText("No matches");
        }
    }

    public void removeBday(View view){
        DBHandler dbHandler = new DBHandler(this, null,null,1);
        boolean result = dbHandler.deleteVerjaardag(nameBox.getText().toString());

        if(result){
            idView.setText("Record deleted");
            nameBox.setText("");
            dateBox.setText("");
        }else{
            idView.setText("No match");
        }
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
