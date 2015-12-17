package com.example.hungdo.myrecipe.Dish.InitialLaunch;

/**
 * Created by sahithi on 10/13/2015.
 * A simple license Agreement dialouge activity which starts as soon as the splash screen activity is done.
 * Displays only one time after the user has agreed to the agreement. If the user agrees to the license agreement
 * he can proceed with the application, otherwise if the user cancels it the application closes.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import com.example.hungdo.myrecipe.R;

public class LicenseAgreement {
    private String License = "eula_";
    private Activity mActivity;

    public LicenseAgreement(Activity context) {
        mActivity = context;
    }

    private PackageInfo getPackageInfo() {
        PackageInfo pi = null;
        try {
            pi = mActivity.getPackageManager().getPackageInfo(mActivity.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi;
    }

    public void show() {
        PackageInfo versionInfo = getPackageInfo();

        // the license Key changes every time you increment the version number in the AndroidManifest.xml
        final String LicenseKey = License + versionInfo.versionCode;
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mActivity);
        boolean hasBeenShown = prefs.getBoolean(LicenseKey, false);
        if (hasBeenShown == false) {

            // Show the license agreement
            String title = "Terms and Agreements" + " v" + versionInfo.versionName;

            //Includes the updates as well so users know what changed.
            String message = mActivity.getString(R.string.updates) + "\n\n" + mActivity.getString(R.string.Terms);

            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Accept", new Dialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Mark this version as read.
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean(LicenseKey, true);
                            editor.commit();
                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("Decline", new Dialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Close the activity as they have declined the agreement
                            mActivity.finish();
                        }


                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            // Close the activity as they have declined the agreement
                            mActivity.finish();
                        }
                    });
            builder.create().show();
        }
    }


}
