package com.scoopbook.cityUpdate.Settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.scoopbook.cityUpdate.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        Preference feedback = findPreference("feedback");
        Preference rateApp = findPreference("rateApp");
        Preference shareApp = findPreference("share_app");
        shareApp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"ScoopBook");
                intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getActivity().getPackageName());
                try {
                    startActivity(Intent.createChooser(intent,"Share with"));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Unable to share app", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        Uri uri = Uri.parse("https://play.google.com/store/apps/detail?id="+getActivity().getPackageName());
        rateApp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Please try again", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        feedback.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "scoopbook52@gmail.com" });
                Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                Email.putExtra(Intent.EXTRA_TEXT,"");
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
                return true;
            }
        });
    }
}