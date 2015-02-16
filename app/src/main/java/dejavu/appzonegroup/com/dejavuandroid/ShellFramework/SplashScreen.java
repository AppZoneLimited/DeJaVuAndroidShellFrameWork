package dejavu.appzonegroup.com.dejavuandroid.ShellFramework;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.ConfigurationRequest;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.Constant.FlowConstant;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;


public class SplashScreen extends ActionBarActivity implements ConfigurationRequest.onConfigurationRequest {

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
        if (flow == FlowConstant.GENERIC_FLOW) {

        } else if (flow == FlowConstant.BANK_FLOW) {

        } else {
            // what should i do?
        }
    }

    @Override
    public void onConfigurationRequestFailed() {
        startActivity(new Intent(SplashScreen.this, GenericFlow.class));

    }
}
