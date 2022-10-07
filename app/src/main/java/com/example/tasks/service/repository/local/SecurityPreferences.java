package com.example.tasks.service.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences
 */
public class SecurityPreferences {

    private SharedPreferences mSharedPreferences;

    public SecurityPreferences(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("TaksShared",Context.MODE_PRIVATE);
    }

    public void storedString(String key,String value) {
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getStoredString (String key) {
        return this.mSharedPreferences.getString(key,"");
    }

    public void remove(String key) {
        this.mSharedPreferences.edit().remove(key).apply();
    }
}
