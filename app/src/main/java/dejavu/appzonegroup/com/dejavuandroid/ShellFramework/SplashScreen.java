package dejavu.appzonegroup.com.dejavuandroid.ShellFramework;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;

import dejavu.appzonegroup.com.dejavuandroid.Fragment.DebitCard;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.FragmentChanger;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.ConfigurationRequest;
import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;


public class SplashScreen extends FragmentActivity implements ConfigurationRequest.onConfigurationRequest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new ConfigurationRequest(this, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
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

    @Override
    public void onConfigurationRequestSuccessful(int flow, boolean debit, boolean password, boolean hardToken, boolean softToken) {

    }

    @Override
    public void onConfigurationRequestFailed() {
        int flow = new Random().nextInt(2);
        if (flow == FlowConstant.GENERIC_FLOW) {
            new FragmentChanger(getSupportFragmentManager(), new DebitCard().newInstance(FlowConstant.GENERIC_FLOW));
        } else if (flow == FlowConstant.BANK_FLOW) {
            new FragmentChanger(getSupportFragmentManager(), true, false, true);
        } else {
            // what should i do?
        }
    }
}
