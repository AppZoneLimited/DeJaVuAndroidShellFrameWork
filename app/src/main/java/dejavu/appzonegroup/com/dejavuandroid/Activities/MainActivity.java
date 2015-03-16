package dejavu.appzonegroup.com.dejavuandroid.Activities;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import dejavu.appzonegroup.com.dejavuandroid.Adapter.FunctionAdapter;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.UserInfoDBHelper;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 2/24/2015.
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        GridView gridView = (GridView) findViewById(R.id.main_function_grid);
        gridView.setAdapter(new FunctionAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.noti) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.add(R.id.content_frame, new UiControlTrier().newInstance(getString(R.string.json_test))).commitAllowingStateLoss();
        }
        return super.onOptionsItemSelected(item);
    }


}
