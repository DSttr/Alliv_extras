package in.ds.alliv.control.settings;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
//import android.os.SystemProperties;
import android.os.UserHandle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import net.margaritov.preference.colorpicker.ColorPickerPreference;
import android.support.v4.app.Fragment;
import in.ds.alliv.control.*;
import android.preference.*;
import android.content.*;

@SuppressWarnings("deprecation")
public class StatusBarSettings extends PreferenceFragment 
implements OnPreferenceChangeListener
{
	
	public StatusBarSettings() {
	}
	
	@Override
	@SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.status_bar_settings);
		
		
		
		
	}
	
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		
		return false;
	}

	private ContentResolver getContentResolver()
	{
		// TODO: Implement this method
		return null;
	}
	
	@Override
	public void onResume() {
        super.onResume();         

        // register callback
        getPreferenceScreen().getSharedPreferences()/*.registerOnSharedPreferenceChangeListener(this)*/;

    }

    @Override
	public void onPause() {
        super.onPause();

        // unregister callback
        getPreferenceScreen().getSharedPreferences()/*.unregisterOnSharedPreferenceChangeListener(this)*/;

    }
}
