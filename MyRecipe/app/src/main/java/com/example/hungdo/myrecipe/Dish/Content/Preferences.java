
package com.example.hungdo.myrecipe.Dish.Content;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hungdo.myrecipe.Dish.MainActivity;
import com.example.hungdo.myrecipe.R;

/**
 * Created by Jay on 10/6/2015.
 */
public class Preferences extends PreferenceActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MyPreferenceFragment())
                .commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            // Resets all settings1 to default
            Preference resetPref = findPreference("reset_default");
            resetPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {

                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
                    SharedPreferences.Editor editor = pref.edit();
                    editor.clear();
                    editor.commit();

                    // Refreshes activity
                    Intent intent = getActivity().getIntent();
                    getActivity().finish();
                    startActivity(intent);

                    return false;
                }
            });

            Preference locationPref = findPreference("location_settings");
            locationPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);

                    return false;
                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.action_about:
                Intent aIntent = new Intent(this, About.class);
                startActivity(aIntent);
                return true;
            case R.id.action_home:
                Intent hIntent = new Intent(this, MainActivity.class);
                startActivity(hIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


