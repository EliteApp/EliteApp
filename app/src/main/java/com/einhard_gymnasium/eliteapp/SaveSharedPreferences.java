package com.einhard_gymnasium.eliteapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreferences {

    private static final String USER_NAME = "userName";
    private static final String NAME = "name";
    private static final String CLASS = "class";
    private static final String PASSWORD = "password";

    private static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(USER_NAME, userName);
        editor.clear().commit();
    }

    public static void setPassword(Context ctx, String password){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PASSWORD, password);
        editor.clear().commit();
    }

    public static void setName(Context ctx, String name){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(NAME, name);
        editor.clear().commit();
    }

    public static void setClass(Context ctx, int pClass){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(CLASS, pClass);
        editor.clear().commit();
    }

    public static String getUserName(Context ctx){
        return getSharedPreferences(ctx).getString(USER_NAME, "");
    }

    public static String getName(Context ctx) {
        return getSharedPreferences(ctx).getString(NAME, "");
    }

    public static int getClass(Context ctx){
        return getSharedPreferences(ctx).getInt(CLASS,0);
    }

}
