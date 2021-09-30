package com.thefuturalabs.helper;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefHelper {

    Context context;

    public SharedPrefHelper() {
        this.context = VolleyAppController.getInstance();
    }



    public void saveString(String key,String value){
        SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.app_name), 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public void setUser(String value){
        saveString(context.getString(R.string.USERID),value);
    }

    public boolean isUserKnown(){
        String tag="";
        tag = readString(context.getString(R.string.USERID));
        boolean is = tag.isEmpty();
        return (!is);
    }
    public void logout(){
        SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.app_name), 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
        editor.commit();
    }

    public String readString(String key){
        SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.app_name), 0);
       return pref.getString(key, "");
    }

    public String getUser() {
        return readString(context.getString(R.string.USERID));
    }
}
