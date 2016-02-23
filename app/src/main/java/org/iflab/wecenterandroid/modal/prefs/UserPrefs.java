package org.iflab.wecenterandroid.modal.prefs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;

import org.iflab.wecenterandroid.modal.User;

/**
 * Created by Lyn on 15/11/15.
 */
public class UserPrefs {
    
    SharedPreferences prefs;
    String accessToken;
    boolean isLoggedIn = false;
    private long userId;
    private String userName;
    private String userAvatar;
    private String cookie;
    private String desc;
    public static volatile UserPrefs singleton;
    private static final String WECENTER_PREF = "WECENTER_PREF";
    private static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_USER_NAME = "KEY_USER_NAME";
    private static final String KEY_USER_AVATAR = "KEY_USER_AVATAR";
    private static final String KEY_COOKIE = "KEY_COOKIE";
    private static final String KEY_DESC = "KEY_DESC";
    
    UserPrefs(Context context){
        prefs = context.getApplicationContext().getSharedPreferences(WECENTER_PREF, Context
                .MODE_PRIVATE);
        accessToken = prefs.getString(KEY_ACCESS_TOKEN, null);
        isLoggedIn = !TextUtils.isEmpty(accessToken);
        if (isLoggedIn) {
            userId = prefs.getLong(KEY_USER_ID, 0);
            userName = prefs.getString(KEY_USER_NAME, null);
            userAvatar = prefs.getString(KEY_USER_AVATAR, null);
        }
    }

    public static UserPrefs getInstance(Context context) {
        if (singleton == null) {
            synchronized (UserPrefs.class) {
                singleton = new UserPrefs(context);
            }
        }
        return singleton;
    }

    public void setAccessToken(String accessToken) {
        if (!TextUtils.isEmpty(accessToken)) {
            this.accessToken = accessToken;
            isLoggedIn = true;
            prefs.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
        }
    }

    public void setLoggedInUser(User user) {
        if (user != null) {
            userName = user.getUser_name();
            userId = user.getUid();
            userAvatar = user.getAvatar_file();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong(KEY_USER_ID, userId);
            editor.putString(KEY_USER_NAME, userName);
            editor.putString(KEY_USER_AVATAR, userAvatar);
            editor.putString(KEY_ACCESS_TOKEN,"for_test");
            editor.apply();
        }
    }

    public long getUserId() {
        return prefs.getLong(KEY_USER_ID, 0l);
    }

    public String getUserName() {
        return prefs.getString(KEY_USER_NAME, null);
    }


    public String getUserAvatar() {
        return prefs.getString(KEY_USER_AVATAR, null);
    }

    public void logout() {
        isLoggedIn = false;
        accessToken = null;
        userId = 0;
        userName = null;
        userAvatar = null;
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public void login(Context context) {
//        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(LOGIN_URL)));
    }

    public void setCookie(String cookie){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_COOKIE, cookie);
        editor.apply();
    }

    public String getCookie(){
        return prefs.getString(KEY_COOKIE, null);
    }

    public void setDesc(String desc){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_DESC, desc);
        editor.apply();
    }

    public String getDesc() {
        return prefs.getString(KEY_DESC, null);
    }
}
