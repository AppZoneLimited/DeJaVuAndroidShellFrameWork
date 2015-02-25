package dejavu.appzonegroup.com.dejavuandroid.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.View;

import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/24/2015.
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
}
