package com.example.michaeldonally.realityquestv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class TravelActivity extends FragmentActivity implements OnMapReadyCallback {
    MediaPlayer mediaplayer;
    private GoogleMap mMap;
    private Map map;
    private List<Map> mapList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

       //GameData game = ((GameData)getApplicationContext());

        GameData g = GameData.getInstance();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Demo Use Only").setMessage("This version of this screen is used for the code only.");
        AlertDialog dialog = builder.create();
        dialog.show();
        mapFragment.getMapAsync(this);
        mediaplayer = MediaPlayer.create(TravelActivity.this, R.raw.happytravels);
        mediaplayer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    public void onLoad(Map map) {
        mMap.clear();
        for (int i=0;map.coorList.get(i)!=null;)
        {
            LatLng latLng = map.coorList.get(i);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Event"+String.valueOf(i)));
        }
    }
    public void onSave(final Map map) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Save your map");
        alert.setMessage("Please enter a Name for your map");

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Save Map", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                map.title = value;
                mapList.add(map);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();

    }
    public void drawPts(LatLng source,LatLng dest) {


    }
    public void onSearch(View view) {
        EditText location_tf = (EditText) findViewById(R.id.addressName);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);


            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            {
                //Prompt for marker name/confirm over here please
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                map.updateList(latLng);
            }

            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            SupportMapFragment mMap = (SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.map);

            mMap.getMapAsync(this);
            // Check if we were successful in obtaining the map.
            if (this != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //mMap.setMyLocationEnabled(true);
            return;
        }
    }

    public void onNextButton(View view){
        Intent intent = new Intent(this, Event_Activity.class);
        mediaplayer.stop();
        startActivity(intent);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
