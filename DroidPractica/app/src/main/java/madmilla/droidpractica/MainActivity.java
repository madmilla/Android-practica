package madmilla.droidpractica;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            counter = savedInstanceState.getInt("cnt");
            TextView tv = (TextView)findViewById(R.id.textview1);
            tv.setText("" + counter);
        }
    }

    protected void onSaveInstanceState(Bundle savedInstanceState){
        // Called to retrieve per-instance state from an activity before being killed so that the state can be restored in onCreate(Bundle)
        // or onRestoreInstanceState(Bundle) (the Bundle populated by this method will be passed to both).
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("cnt", counter);
        Log.i("Droidpractica", "MainActivity.onSaveInstanceState()");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        // This method is called after onStart() when the activity is being re-initialized from a previously saved state,
        // given here in savedInstanceState. Most implementations will simply use onCreate(Bundle) to restore their state,
        // but it is sometimes convenient to do it here after all of the initialization has been done or to allow
        // subclasses to decide whether to use your default implementation. The default implementation of this method
        // performs a restore of any view state that had previously been frozen by onSaveInstanceState(Bundle
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.putInt("cnt", counter);
        Log.i("Droidpractica", "MainActivity.onRestoreInstanceState()");
    }

    public void updateTextview1(View v, int counter){
        TextView tv = (TextView)findViewById(R.id.textview1);
        tv.setText(""+ counter);

    }

    public void plusEen(View v){
        counter++;
        TextView tv = (TextView)findViewById(R.id.textview1);
        tv.setText(""+ counter);
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
