package com.candeapps.mapsexample;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap;

import android.location.Address;
import android.location.Geocoder;

public class MainActivity extends Activity {

    private GoogleMap map;
    //private static final LatLng LONDON = new LatLng(+51.5000, -0.11670);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try{
            List<Address> addresses = geocoder.getFromLocationName("Taj Mahal", 5);
            if (addresses.size() > 0) {
                LatLng tajMahal = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(tajMahal, 15));
                map.addMarker(new MarkerOptions().position(tajMahal));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
