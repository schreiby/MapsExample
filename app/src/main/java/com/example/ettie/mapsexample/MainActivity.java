package com.example.ettie.mapsexample;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import android.location.Address;
import android.location.Geocoder;

public class MainActivity extends Activity {

    private GoogleMap map;
    private static final LatLng LONDON = new LatLng(+51.5000, -0.11670);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LONDON, 15));
        map.addMarker(new MarkerOptions().position(LONDON));

       map.setOnMapClickListener(new OnMapClickListener() {
           @Override
           public void onMapClick(LatLng latLng) {
              /* Toast.makeText(MainActivity.this, "Location: " + latLng, Toast.LENGTH_SHORT).show();*/

               Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
               try {
                   List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

                   String addStr = "";
                   if (addresses.size() > 0) {
                       for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++) {
                           addStr += addresses.get(0).getAddressLine(i) + "\n";
                       }
                   }
                   Toast.makeText(getBaseContext(), addStr, Toast.LENGTH_SHORT).show();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });
    }


}
