package com.example.hungdo.myrecipe.Dish.Content.GoogleMaps;

import com.example.hungdo.myrecipe.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;



import android.widget.Toast;



public class MapsActivity extends FragmentActivity implements LocationListener{

    private GoogleMap googleMap;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(googleMap==null){
            SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);

            // Getting Google Map
            googleMap = fragment.getMap();

            // Enabling MyLocation in Google Map
            googleMap.setMyLocationEnabled(true);
        }

        if(googleMap != null){
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        checkForLocationServices();
    }

    public void checkForLocationServices(){

        int locationMode = 0;

        // Checks if location services are enabled
        try{
            locationMode = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        // Creates dialog if location services are disabled
        if (locationMode == Settings.Secure.LOCATION_MODE_OFF) {

            new AlertDialog.Builder(this)

                    .setMessage("Location Services Not Enabled")

                    .setPositiveButton("Enable Location Services",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(
                                            Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }

                            })

                    .setNegativeButton("Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }

                            }).create().show();


        }

        updatePlaces();
    }

    private void updatePlaces() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (lastLocation != null) {
            onLocationChanged(lastLocation);
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 0, this);

        if (lastLocation != null) {
            Toast.makeText(MapsActivity.this, "Location Discovered - Finding Nearby Stores", Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Search for grocery stores nearby
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=groceries");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                    finish();
                }
            }, 3000);

        }

    }

    @Override
    public void onLocationChanged(Location location) {
        TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
    }

    @Override
    public void onResume(){
        super.onResume();

        updatePlaces();

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

}