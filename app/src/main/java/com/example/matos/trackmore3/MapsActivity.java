package com.example.matos.trackmore3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    // google map
    private GoogleMap mMap;
    private float zoom = 17;

    // Location markers
    private  LatLng CurrentPosition, markerPosition,startingPosition;

    LocationManager lm;
    Location location;
    LatLng latLng;

    // updates
    static JSONObject GetJson;
    static boolean update;

    // HashMap
    static HashMap<String, Marker> MarkerMap = new HashMap<String, Marker>();
    public static ArrayList<String> ID = new ArrayList<String>();
    public static ArrayList<String> PIN = new ArrayList<>();
    public static ArrayList<String> Name = new ArrayList<>();
    // handler

    Handler h = new Handler();
    int delay = 30000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        ArrayList<JSONObject> JsonObjects;
        JsonObjects = Services.load(this);
        System.out.println(JsonObjects);


        int i = 0;
        for (JSONObject j : JsonObjects) {
            try {
                ID.add(j.getString("code"));
                PIN.add(j.getString("pincode"));
                Name.add(j.getString("name"));
                System.out.println(ID.get(i));
                System.out.println(PIN.get(i));
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        startingPosition = new LatLng(12.55,122.2);


        h.postDelayed(new Runnable(){
            @SuppressLint("MissingPermission")
            public void run() {
                try {
                    h.postDelayed(this, delay);

                    if (update) {
                        try {
                            UpdateMarkers();
                            System.out.println("update");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        update = false;
                    }


                    System.out.println("redraw");
                    mMap.setMyLocationEnabled(true);
                    lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    CurrentPosition = latLng;
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }, delay);


    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("EXIT")
                .setMessage("Exit will delete data and end connection")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        com.example.matos.trackmore3.MapsActivity.super.onBackPressed();

                    }
                }).create().show();

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


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            return;
        }
        mMap.setMyLocationEnabled(true);
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //latLng = new LatLng(location.getLatitude(), location.getLongitude());
        //CurrentPosition = latLng;

        try{
            mMap.setMyLocationEnabled(true);
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            CurrentPosition = latLng;
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        initMarkers();
        new AsyncGET().execute();
    }



    // method to init number of markers from ID array, and tie Marker with ID
    public void initMarkers(){
        System.out.println("inside init");
        System.out.println(ID.size());
        for(int i = 0; i < ID.size(); i++){
            System.out.println("inside init loop");
            String txt = ID.get(i);
            Marker newMarker;
            newMarker = mMap.addMarker(new MarkerOptions().position(startingPosition).title(txt).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            MarkerMap.put(txt,newMarker);
            System.out.println(txt);
        }
    }

    public void UpdateMarkers() throws JSONException {

        for(int i = 0; i < ID.size(); i++){

            String data = GetJson.getString(Name.get(i));
            JSONObject JsonData = new JSONObject(data);
            System.out.println(JsonData);

            double latitude = Double.parseDouble(JsonData.getString("Latitude"));
            double longitude = Double.parseDouble(JsonData.getString("Longitude"));
            System.out.println(latitude + "this is data " + longitude);
            markerPosition = new LatLng(latitude,longitude);
            MarkerMap.get(ID.get(i)).remove();

            MarkerMap.remove(ID.get(i));
            String name = Name.get(i);
            String txt = ID.get(i);
            Marker newMarker = mMap.addMarker(new MarkerOptions().position(markerPosition).title(name).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            System.out.println(txt);
            MarkerMap.put(txt,newMarker);

            double distance = SphericalUtil.computeDistanceBetween(CurrentPosition, markerPosition);
            if(distance > 100){
                // make some warning
            }

        }

    }

    public static void updateJson(JSONObject Json){

    GetJson = Json;
    update = true;
        System.out.println(Json);

    }





}
