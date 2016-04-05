package in.ds.alliv.control.settings;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.provider.Settings;

import in.ds.alliv.control.*;
import android.preference.*;

public class ButtonSettings extends PreferenceFragment implements OnSharedPreferenceChangeListener
{

	SwitchPreference mSwitch;


	@Override
	@SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.button_settings);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences p1, String p2)
	{
		// TODO: Implement this method
	}

}

