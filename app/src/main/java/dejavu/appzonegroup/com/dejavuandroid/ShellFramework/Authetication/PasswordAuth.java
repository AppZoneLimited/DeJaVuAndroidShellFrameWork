package dejavu.appzonegroup.com.dejavuandroid.ShellFramework.Authetication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

public class PasswordAuth extends ActionBarActivity {
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_auth);
        passwordEditText = (EditText) findViewById(R.id.password_field);
    }

    public void verify() {
        if (passwordEditText.getText().toString().equalsIgnoreCase(new UserDetailsSharePreferences(this).getPassword())) {

        } else {
            new ShowMessage(this, "Invalid password", Toast.LENGTH_LONG);
        }
    }
}
