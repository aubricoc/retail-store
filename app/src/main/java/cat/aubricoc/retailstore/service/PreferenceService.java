package cat.aubricoc.retailstore.service;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceService {

	private static final String PREFS_NAME = "RetailStorePrefs";
	private static final String PREF_LAUNCHED_PREV = "__$_launched_prev _$__";

	private Context context;

	public static PreferenceService newInstance(Context context) {
		return new PreferenceService(context);
	}

	private PreferenceService(Context context) {
		this.context = context;
	}

	public boolean isFirstTime() {
		return !getSharedPreferences().getBoolean(PREF_LAUNCHED_PREV, false);
	}

	public void markLaunched() {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putBoolean(PREF_LAUNCHED_PREV, true);
		editor.apply();
	}

	private SharedPreferences getSharedPreferences() {
		return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
	}
}
