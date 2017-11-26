package xyz.imxqd.mediacontroller.ui.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;

import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

import xyz.imxqd.mediacontroller.R;
import xyz.imxqd.mediacontroller.ui.BaseActivity;

public class SettingsFragment extends PreferenceFragmentCompat {

    private static volatile SettingsFragment mInstance;

    private Handler mHandler = new Handler();

    public SettingsFragment() {
        // Required empty public constructor
    }


    public static SettingsFragment getInstance() {
        if (mInstance == null ) {
            synchronized (SettingsFragment.class) {
                mInstance = new SettingsFragment();
            }
        }
        return mInstance;
    }


    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settgings_screen);
        initPrefs();
    }

    private void initPrefs() {
        SwitchPreference appSwitch = (SwitchPreference) findPreference(getString(R.string.pref_key_app_switch));
        if (appSwitch.isChecked()) {
            BaseActivity activity = (BaseActivity)getActivity();
            if (!activity.isAccessibilitySettingsOn()) {
                appSwitch.setChecked(false);
            }
        }

        boolean debug = getPreferenceManager()
                .getSharedPreferences()
                .getBoolean(getString(R.string.pref_key_app_debug), false);
        if (debug) {
            addDebugSettings();
        }
    }

    private void addDebugSettings() {
        if (findPreference(getString(R.string.pref_key_app_debug)) == null) {
            addPreferencesFromResource(R.xml.settings_debug);
        }
    }

    private int mClickCount = 0;

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {

        if (getString(R.string.pref_key_version).equals(preference.getKey())) {
            mClickCount++;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mClickCount = 0;
                }
            }, 1500);
            if (mClickCount == 7) {
                addDebugSettings();
            }
        } else if (getString(R.string.pref_key_app_switch).equals(preference.getKey())) {
            SwitchPreference p = (SwitchPreference) preference;
            if (p.isChecked()) {
                BaseActivity activity = (BaseActivity)getActivity();
                if (!activity.isAccessibilitySettingsOn()) {
                    activity.startAccessibilitySettings();
                    p.setChecked(false);
                }
            }
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mInstance = null;
    }
}
