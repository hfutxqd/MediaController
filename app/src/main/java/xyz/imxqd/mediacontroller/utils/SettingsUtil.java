package xyz.imxqd.mediacontroller.utils;

import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import xyz.imxqd.mediacontroller.App;
import xyz.imxqd.mediacontroller.R;

/**
 * Created by imxqd on 2017/11/26.
 */

public class SettingsUtil {
    public static boolean displayDebug() {
        SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(App.get());
        return shp.getBoolean(ResUtil.getString(R.string.pref_key_app_debug), false);
    }

    public static boolean isServiceOn() {
        SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(App.get());
        return shp.getBoolean(ResUtil.getString(R.string.pref_key_app_switch), true);
    }
}