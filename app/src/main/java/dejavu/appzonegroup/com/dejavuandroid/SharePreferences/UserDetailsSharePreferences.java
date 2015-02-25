package dejavu.appzonegroup.com.dejavuandroid.SharePreferences;

import android.content.Context;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class UserDetailsSharePreferences {

    private Context mContext;

    public UserDetailsSharePreferences(Context context) {
        mContext = context;
    }

    public void setPhoneNumber(String number) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".phoneNumber", number).clear();
    }

    public String getUserPhoneNumber() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".phoneNumber", "");
    }

    public void setLastName(String lastName) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".lastName", lastName).clear();

    }

    public String getLastName() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".lastName", "");
    }

    public void setOtherName(String otherName) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".otherName", otherName).clear();

    }

    public String getOtherName() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".otherName", "");
    }

    public void setGenderValue(int genderValue) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putInt(getClass().getSimpleName() + ".genderValue", genderValue).clear();

    }

    public int getGenderValue() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getInt(getClass().getSimpleName() + ".genderValue", -1);
    }


    public void setDate(long dateTime) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putLong(getClass().getSimpleName() + ".date", dateTime).clear();

    }

    public long getDate() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getLong(getClass().getSimpleName() + ".date", -1l);
    }


    public void setEmail(String email) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".email", email).clear();

    }

    public String getEmail() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".email", "");
    }

    public void setPassword(String password) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".password", password).clear();

    }

    public String getPassword() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".password", "");
    }

    public void setPin(String pin) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putString(getClass().getSimpleName() + ".pin", pin).clear();

    }

    public String getPin() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getString(getClass().getSimpleName() + ".pin", "");
    }

    public void setRegister(boolean status) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putBoolean(getClass().getSimpleName() + ".isReg", status).clear();

    }

    public boolean isRegistered() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getBoolean(getClass().getSimpleName() + ".isReg", false);
    }

    public void setFullyAuth(boolean status) {
        mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).edit().putBoolean(getClass().getSimpleName() + ".auth", status).clear();

    }

    public boolean isFullyAuth() {
        return mContext.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE).getBoolean(getClass().getSimpleName() + ".auth", false);
    }

}
