package com.example.michaeldonally.realityquestv3;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import android.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.*;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class createMapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private User user;
    private Marker marker;

    private GoogleMap mMap;
    private GoogleApiClient client_Geodata, client_Place;

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    protected Location mLastLocation;

    private List<Marker> markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);

        //client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();

        //Makes the global user the user here
        Globals globals = ((Globals)getApplicationContext());
        user = globals.getUser();

        //Initializes the map
        LatLng userLoc = getCurrentPos();
        marker = user.character.currMarker;
        LatLng markerLoc = new LatLng(marker.markerLoc.latitude, marker.markerLoc.longitude);
        drawPts(userLoc, markerLoc);
    }

    public void onSubmit(View view) {
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
            }

            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }
    }

    //Function to save a marker whos location is in the input address box
    public void onSave(View view) {
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

            //Prompts user to name the marker then creates a new marker with that name
            final Coor markerLoc = new Coor(address.getLatitude(), address.getLongitude());

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Marker Name:");

            final EditText input = new EditText(this);
            builder.setView(input);

            builder.setPositiveButton("Confirm?", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                    Marker newMarker = new Marker(input.getText().toString(), markerLoc);

                    //Add newMarker to our marker list
                    markers.add(newMarker);

                    TextView markerListGUI = (TextView)findViewById(R.id.markerList);
                    markerListGUI.append("\n" + newMarker.getName());

                    //Check if there are ten markers
                    if(markers.size() >= 10) {
                        finishMap();
                    }
                }
            });
            builder.setNegativeButton("Cancel", null);
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }

    //Called when there are ten markers in the markers list
    public void finishMap() {
        EditText mapName = (EditText)findViewById(R.id.mapName);

        Map newMap = new Map(mapName.getText().toString(), markers);

        //Sends new map to the server
        //user.createMap(newMap);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your new map has been saved on the server!");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(createMapActivity.this, selectContentToCreateAcitivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public LatLng getCurrentPos() {
        LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        return (latLng);
    }

    //Draws the map line between two given points and updates the map
    public void drawPts(LatLng userLoc, LatLng markerLoc) {
        PolylineOptions Routepoints = new PolylineOptions().add(userLoc).add(markerLoc);
        Polyline Route = mMap.addPolyline(Routepoints);
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Travel Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    private synchronized void buildGoogleApiClient_GeoData() {
        client_Geodata = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }


    private synchronized void buildGoogleApiClient_Place_Detect() {
        client_Place = new GoogleApiClient
                .Builder(this)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }
    @Override
    public void onStart() {
        super.onStart();
        buildGoogleApiClient_GeoData();
        //buildGoogleApiClient_Place_Detect();
        //client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client_Geodata.connect();
        //client_Place.connect();
        //AppIndex.AppIndexApi.start(client_Geodata, getIndexApiAction());
        //AppIndex.AppIndexApi.start(client_Place, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //AppIndex.AppIndexApi.end(client_Geodata, getIndexApiAction());
        //AppIndex.AppIndexApi.end(client_Place, getIndexApiAction());
        client_Geodata.disconnect();
        //client_Place.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(client_Geodata);
        if (mLastLocation != null) {
            //mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            //mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
