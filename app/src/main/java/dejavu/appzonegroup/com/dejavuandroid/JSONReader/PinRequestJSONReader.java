package dejavu.appzonegroup.com.dejavuandroid.JSONReader;

import org.json.JSONArray;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.onPinRequest;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.PinRequest;
import dejavu.appzonegroup.com.dejavuandroid.Constant.ServerResponseCodes;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class PinRequestJSONReader {


    public PinRequestJSONReader(String result, onPinRequest pinRequest) {
        try {
            JSONArray pinRequestJsonString = new JSONArray(result);
            int serverResponseCode = pinRequestJsonString.getJSONObject(0).getInt("response");
            switch (serverResponseCode) {
                case ServerResponseCodes.SUCCESS:
                    pinRequest.onPinRequested();
                    break;
                case ServerResponseCodes.DENY_REQUEST:
                    pinRequest.onPinRequestDenied();
                    break;
                default:
                    pinRequest.onRequestFailed();
            }
        } catch (Exception e) {
            pinRequest.onRequestFailed();
        }
    }


}
