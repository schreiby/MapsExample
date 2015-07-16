package com.candeapps.mapsexample;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap;
import android.widget.Toast;

public class MainActivity extends Activity {

    private GoogleMap map;
    private static final LatLng LONDON = new LatLng(+51.5000, -0.11670);

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LONDON, 15));
        map.addMarker(new MarkerOptions().position(LONDON));

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new MyLocationListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Toast.makeText(getBaseContext(), "Location changed: " + location.toString(), Toast.LENGTH_SHORT).show();

                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                map.addMarker(new MarkerOptions().position(latLng));
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            //
        }

        @Override
        public void onProviderEnabled(String provider) {
            //
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //
        }
    }
}
