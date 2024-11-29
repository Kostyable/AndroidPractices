package ru.mirea.blinnikovkm.data.data.storage.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSharedPrefs {
    private static final String PREF_NAME = "user_prefs";
    private static final String KEY_IS_GUEST = "is_guest";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    private final SharedPreferences sharedPreferences;

    public UserSharedPrefs(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setGuestMode(boolean isGuest) {
        sharedPreferences.edit().putBoolean(KEY_IS_GUEST, isGuest).apply();
    }

    public boolean isGuestMode() {
        return sharedPreferences.getBoolean(KEY_IS_GUEST, false);
    }

    public void setLoggedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
