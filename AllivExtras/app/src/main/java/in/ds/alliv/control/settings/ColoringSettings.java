package in.ds.alliv.control.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.SwitchPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import net.margaritov.preference.colorpicker.ColorPickerPreference;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import in.ds.alliv.control.*;

public class ColoringSettings extends PreferenceFragment implements OnPreferenceChangeListener {

    private static final String ALLIV_ENABLE_COLOR = "alliv_enable_color";
    private static final String ALLIV_LOGO = "alliv_logo";
	private static final String ALLIV_STATUS_BAR_SIGNAL = "alliv_status_bar_signal";
    private static final String ALLIV_IN_OUT = "alliv_in_out";
    private static final String ALLIV_AIRPLANE = "alliv_airplane";
    private static final String ALLIV_WIFI_STATS = "alliv_wifi_stat";
    private static final String ALLIV_NOTIF_COLOR = "illusiondev_notif_color";
    private static final String ALLIV_BATTERY_PERCENT_TXT = "illusiondev_battery_persen_txt";
    private static final String ALLIV_TICKER_TXT = "illusiondev_ticker_txt";
    private static final String ALLIV_COLOR_RESET = "alliv_color_reset";
	private static final String ALLIV_NAV_ICON = "illusiondev_nav_icon";

        private static final int DEFAULT_COLOR = 0xffffffff;

        SwitchPreference mEnableColor;
        ColorPickerPreference mAllivLogo;
        ColorPickerPreference mAllivSignal;
        ColorPickerPreference mAllivInOut;
        ColorPickerPreference mAllivAirPlane;
        ColorPickerPreference mAllivWiFiStats;
        ColorPickerPreference mAllivNotif;
        ColorPickerPreference mAllivPercentage;
        ColorPickerPreference mAllivTicker;
		ColorPickerPreference mAllivNavIcon;
        Preference mResetColor;

	private ContentResolver mResolver;
	private int intColor;
	private String hexColor;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            refreshSettings();
        }

        public void refreshSettings() {
            PreferenceScreen prefs = getPreferenceScreen();
            if (prefs != null) {
                prefs.removeAll();
            }
            addPreferencesFromResource(R.xml.illusiondev_worstcolor);
			
            mResolver = getActivity().getContentResolver();
			
            boolean enableColor = Settings.System.getInt(mResolver,"alliv_enable_color", 0) == 1;
			
            
            mEnableColor = (SwitchPreference) findPreference(ALLIV_ENABLE_COLOR);
            mEnableColor.setChecked(enableColor);
            mEnableColor.setOnPreferenceChangeListener(this);

            mAllivLogo = (ColorPickerPreference) findPreference(ALLIV_LOGO);
            intColor = Settings.System.getInt(mResolver, "alliv_logo", DEFAULT_COLOR);
			hexColor = String.format("#%08x", (0xffffffff & intColor));
            mAllivLogo.setNewPreviewColor(intColor);
            mAllivLogo.setSummary(hexColor);
            mAllivLogo.setOnPreferenceChangeListener(this);
            mAllivLogo.setAlphaSliderEnabled(true);

            mAllivSignal = (ColorPickerPreference) findPreference(ALLIV_STATUS_BAR_SIGNAL);
            intColor = Settings.System.getInt(mResolver, "alliv_status_bar_signal", DEFAULT_COLOR);
			hexColor = String.format("#%08x", (0xffffffff & intColor));
            mAllivSignal.setNewPreviewColor(intColor);
            mAllivSignal.setSummary(hexColor);
            mAllivSignal.setOnPreferenceChangeListener(this);
            mAllivSignal.setAlphaSliderEnabled(true);

            mAllivInOut = (ColorPickerPreference) findPreference(ALLIV_IN_OUT);
            intColor = Settings.System.getInt(mResolver, "alliv_in_out", DEFAULT_COLOR);
            mAllivInOut.setNewPreviewColor(intColor);
            hexColor = String.format("#%08x", (0xffffffff & intColor));
            mAllivInOut.setSummary(hexColor);
            mAllivInOut.setOnPreferenceChangeListener(this);
            mAllivInOut.setAlphaSliderEnabled(true);

            mAllivAirPlane = (ColorPickerPreference) findPreference(ALLIV_AIRPLANE);
            intColor = Settings.System.getInt(mResolver, "alliv_airplane", DEFAULT_COLOR);
            hexColor = String.format("#%08x", (0xffffffff & intColor));
            mAllivAirPlane.setNewPreviewColor(intColor);
            mAllivAirPlane.setSummary(hexColor);
            mAllivAirPlane.setOnPreferenceChangeListener(this);
            mAllivAirPlane.setAlphaSliderEnabled(true);

            mAllivWiFiStats = (ColorPickerPreference) findPreference(ALLIV_WIFI_STATS);
            intColor = Settings.System.getInt(mResolver, "alliv_wifi_stat", DEFAULT_COLOR);
            hexColor = String.format("#%08x", (0xffffffff & intColor));
            mAllivWiFiStats.setNewPreviewColor(intColor);
			mAllivWiFiStats.setSummary(hexColor);
            mAllivWiFiStats.setOnPreferenceChangeListener(this);
            mAllivWiFiStats.setAlphaSliderEnabled(true);

            mAllivNotif = (ColorPickerPreference) findPreference(ALLIV_NOTIF_COLOR);
            intColor = Settings.System.getInt(mResolver, "illusiondev_notif_color", DEFAULT_COLOR);
            hexColor = String.format("#%08x", (0xffffffff & intColor));
            mAllivNotif.setNewPreviewColor(intColor);
            mAllivNotif.setSummary(hexColor);
            mAllivNotif.setOnPreferenceChangeListener(this);
            mAllivNotif.setAlphaSliderEnabled(true);

            mAllivPercentage = (ColorPickerPreference) findPreference(ALLIV_BATTERY_PERCENT_TXT);
            intColor = Settings.System.getInt(mResolver, "illusiondev_battery_persen_txt", DEFAULT_COLOR);
            hexColor = String.format("#%08x", (0xffffffff & intColor));
            mAllivPercentage.setNewPreviewColor(intColor);
            mAllivPercentage.setSummary(hexColor);
            mAllivPercentage.setOnPreferenceChangeListener(this);
            mAllivPercentage.setAlphaSliderEnabled(true);

            mAllivTicker = (ColorPickerPreference) findPreference(ALLIV_TICKER_TXT);
            intColor = Settings.System.getInt(mResolver, "illusiondev_ticker_txt", DEFAULT_COLOR);
            hexColor = String.format("#%08x", (0xffffffff & intColor));
            mAllivTicker.setNewPreviewColor(intColor);
            mAllivTicker.setSummary(hexColor);
            mAllivTicker.setOnPreferenceChangeListener(this);
            mAllivTicker.setAlphaSliderEnabled(true);
			
			mAllivNavIcon = (ColorPickerPreference) findPreference(ALLIV_NAV_ICON);
            intColor = Settings.System.getInt(mResolver, "illusiondev_nav_icon", DEFAULT_COLOR);
            hexColor = String.format("#%08x", (0xffffffff & intColor));
            mAllivNavIcon.setNewPreviewColor(intColor);
            mAllivNavIcon.setSummary(hexColor);
            mAllivNavIcon.setOnPreferenceChangeListener(this);
            mAllivNavIcon.setAlphaSliderEnabled(true);

            mResetColor = findPreference(ALLIV_COLOR_RESET);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            boolean value;

            if (preference == mEnableColor) {
                value = (Boolean) newValue;
                Settings.System.putInt(mResolver,
                    "alliv_enable_color",
                    value ? 1 : 0);
                return true;
            } else if (preference == mAllivLogo) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                        .valueOf(newValue)));
                preference.setSummary(hex);
                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
                        "alliv_logo", intHex);
                return true;
            } else if (preference == mAllivSignal) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                        .valueOf(newValue)));
                preference.setSummary(hex);
                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
									   "alliv_status_bar_signal", intHex);
                return true;
            } else if (preference == mAllivInOut) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                        .valueOf(newValue)));
                preference.setSummary(hex);
                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
                        "alliv_in_out", intHex);
                return true;
            } else if (preference == mAllivAirPlane) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                        .valueOf(newValue)));
                preference.setSummary(hex);
                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
                        "alliv_airplane", intHex);
                return true;
            } else if (preference == mAllivWiFiStats) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                        .valueOf(newValue)));
                preference.setSummary(hex);
                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
                        "alliv_wifi_stat", intHex);
                return true;
            } else if (preference == mAllivNotif) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                        .valueOf(newValue)));
                preference.setSummary(hex);
                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
                        "illusiondev_notif_color", intHex);
                return true;
            } else if (preference == mAllivPercentage) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                        .valueOf(newValue)));
                preference.setSummary(hex);
                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
                        "illusiondev_battery_persen_txt", intHex);
                return true;
            } else if (preference == mAllivTicker) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                .valueOf(newValue)));
                preference.setSummary(hex);
                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
                "illusiondev_ticker_txt", intHex);
                return true;
            } else if (preference == mAllivNavIcon) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
				.valueOf(newValue)));
                preference.setSummary(hex);
                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
				"illusiondev_nav_icon", intHex);
                return true;
				}
            return false;
        }

        @Override
        public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
            if (preference == mResetColor) {
                showDialogInner(0);
            } else {
                return super.onPreferenceTreeClick(preferenceScreen, preference);
            }
            return false;
        }

        private void showDialogInner(int id) {
            DialogFragment newFragment = MyAlertDialogFragment.newInstance(id);
            newFragment.setTargetFragment(this, 0);
            newFragment.show(getFragmentManager(), "dialog " + id);
        }

        public static class MyAlertDialogFragment extends DialogFragment {

            public static MyAlertDialogFragment newInstance(int id) {
                MyAlertDialogFragment frag = new MyAlertDialogFragment();
                Bundle args = new Bundle();
                args.putInt("id", id);
                frag.setArguments(args);
                return frag;
            }

		ColoringSettings getOwner() {
			return (ColoringSettings) getTargetFragment();
		}

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                int id = getArguments().getInt("id");
                switch (id) {
                    case 0:
                        return new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.reset)
                        .setMessage(R.string.reset_message)
                        .setNegativeButton(R.string.cancel, null)
                        .setNeutralButton(R.string.reset_android,
                            new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Settings.System.putInt(getOwner().mResolver, "alliv_enable_color", 1);
                                Settings.System.putInt(getOwner().mResolver, "alliv_logo", DEFAULT_COLOR);
                                Settings.System.putInt(getOwner().mResolver,"alliv_status_bar_signal", DEFAULT_COLOR);
                                Settings.System.putInt(getOwner().mResolver,
                                        "alliv_in_out",
                                        DEFAULT_COLOR);
                                Settings.System.putInt(getOwner().mResolver,
                                        "alliv_airplane",
                                        DEFAULT_COLOR);
                                Settings.System.putInt(getOwner().mResolver,
                                        "alliv_wifi_stat",
                                        DEFAULT_COLOR);
                                Settings.System.putInt(getOwner().mResolver,
                                        "illusiondev_notif_color",
                                        DEFAULT_COLOR);
                                Settings.System.putInt(getOwner().mResolver,
                                        "illusiondev_battery_persen_txt",
                                        DEFAULT_COLOR);
                                Settings.System.putInt(getOwner().mResolver,
                                        "illusiondev_ticker_txt",
                                        DEFAULT_COLOR);
								Settings.System.putInt(getOwner().mResolver,
										"illusiondev_nav_icon",
										DEFAULT_COLOR);
                                getOwner().refreshSettings();
                            }
                        })
                        .setPositiveButton(R.string.reset_android,
                            new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Settings.System.putInt(getOwner().mResolver,
                                        "alliv_enable_color", 1);
                                Settings.System.putInt(getOwner().mResolver,
                                        "alliv_logo",
                                        0x04000000);
                                Settings.System.putInt(getOwner().mResolver,
										"alliv_status_bar_signal",
                                        0x623367e5);
                                Settings.System.putInt(getOwner().mResolver,
                                        "alliv_in_out",
                                        0xff00ff00);
                                Settings.System.putInt(getOwner().mResolver,
                                        "alliv_airplane",
                                        0xff1976D2);
                                Settings.System.putInt(getOwner().mResolver,
                                        "alliv_wifi_stat",
                                        0xffff0000);
                                Settings.System.putInt(getOwner().mResolver,
                                        "illusiondev_notif_color",
                                        0xff00ff00);
                                Settings.System.putInt(getOwner().mResolver,
                                        "illusiondev_battery_persen_txt",
                                        0xffffffff);
                                Settings.System.putInt(getOwner().mResolver,
                                        "illusiondev_ticker_txt",
                                        0xffff0000);
								Settings.System.putInt(getOwner().mResolver,
										"illusiondev_nav_icon",
										0xffff0000);
                                getOwner().refreshSettings();
                            }
                        })
                        .create();
                }
                throw new IllegalArgumentException("unknown id " + id);
            }

            @Override
            public void onCancel(DialogInterface dialog) {

            }
        }
    }
