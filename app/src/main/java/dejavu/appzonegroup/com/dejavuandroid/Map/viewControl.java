package dejavu.appzonegroup.com.dejavuandroid.Map;

import android.view.View;

import java.util.HashMap;

/**
 * Created by CrowdStar on 3/12/2015.
 */
public class viewControl {

    static public View getViewById(HashMap<Object, View> hashMap, int id) {
        return hashMap.get(id);
    }
}
