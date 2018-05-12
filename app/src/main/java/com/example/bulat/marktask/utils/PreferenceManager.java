package com.example.bulat.marktask.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.bulat.marktask.models.User;

public class PreferenceManager {

  private static final String USER_ID = "user_id";
  private static final String USER_NAME = "fio";
  private static final String USER_EMAIL = "user_role_id";
  private static final String PREF_SETTINGS = "settings";

  static PreferenceManager sPreferenceManager;
  static SharedPreferences sPreferences;
  private static Context sContext;


  private PreferenceManager() {
  }

  public static void saveUserValues(User user) {
    if (sPreferences == null) {
      sPreferences = sContext.getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE);
    }
    SharedPreferences.Editor editor = sPreferences.edit();
    editor.putString(USER_ID, user.getId());
    editor.putString(USER_EMAIL, user.getEmail());
    editor.putString(USER_NAME, user.getName());
    editor.apply();
  }

  public static String getUserId(Context context) {
    if (sPreferences == null) {
      createPreferenceManager(context);
    }
    return sPreferences.getString(USER_ID, "");
  }

  public static String getUserEmail(Context context) {
    if (sPreferences == null) {
      createPreferenceManager(context);
    }
    return sPreferences.getString(USER_EMAIL, "");
  }

  public static String getUserName(Context context) {
    if (sPreferences == null) {
      createPreferenceManager(context);
    }
    return sPreferences.getString(USER_NAME, "");
  }

  public static PreferenceManager createPreferenceManager(Context context) {
    sContext = context;
    if (sPreferenceManager == null) {
      sPreferenceManager = new PreferenceManager();
      sPreferences = sContext.getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE);
    }
    return sPreferenceManager;
  }
}
