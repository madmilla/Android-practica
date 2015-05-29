package com.example.madmilla.week2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {
    private EditText edt;
    private EditText ndt;
    private EditText pdt;
    private RadioButton mrb, vrb;
    private RadioGroup rg;
    private DatePicker dp;
    private boolean failure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = (EditText) findViewById(R.id.emailEditText);
        ndt = (EditText) findViewById(R.id.nameEditText);
        pdt = (EditText) findViewById(R.id.phoneEditText);
        mrb = (RadioButton) findViewById(R.id.radio_man);
        vrb = (RadioButton) findViewById(R.id.radio_vrouw);
        dp = (DatePicker) findViewById(R.id.datePicker);
        rg = (RadioGroup) findViewById(R.id.gender);

        findViewById(R.id.OKbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                failure = false;
                final String email = edt.getText().toString();
                if(!isValidEmail(email)){
                    edt.setError("Invalid email");
                    failure = true;
                }else{
                    edt.setError(null);
                }

                final String name = ndt.getText().toString();
                if(!isFilled(name)){
                    ndt.setError("Naam mag niet leeg zijn");
                    failure = true;
                }else{
                    ndt.setError(null);
                }

                final String phone = pdt.getText().toString();
                if(!isPhoneNumber(phone)){
                    pdt.setError("Telefoon nummer moet 10 nummers zijn");
                    failure = true;
                }else{
                    pdt.setError(null);
                }

                if(rg.getCheckedRadioButtonId()== -1){
                     //   mrb.isChecked() OR vrb.isChecked()){
                    vrb.setError("Je moet een geslacht kiezen.");
                    failure = true;
                }else{
                    vrb.setError(null);
                }

                if(failure == false){
                    Log.w("email", email);
                    Log.w("name", name);
                    Log.w("phone", phone);
                    if(mrb.isChecked()){
                        Log.w("geslacht", "man");
                    }else{
                        Log.w("geslacht", "vrouw");
                    }
                    Log.w("date-of-birth:", dp.getDayOfMonth() + "-" + dp.getMonth() + "-" + dp.getYear());
                }

            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_man:
                if (checked)
                    // iets?
                    break;
            case R.id.radio_vrouw:
                if (checked)
                    // iets
                    break;
        }
    }


    // Validate email - van de developer guide overgenomen
    private boolean isValidEmail(String email){
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isFilled(String value){
        if (value != null && value.length() > 2) {
            return true;
        }
        return false;
    }

    private boolean isPhoneNumber(String value){
        if (value != null && value.length() >= 10 && isNumeric(value)) {
            return true;
        }
        return false;
    }

    // ook uit de dev guide geplukt.
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
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
