package com.example.afonso.pointsofinterestapp;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


public class MainActivity extends Activity {

    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Download OSM maps*/
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);
        initMap();
    }

    private void initMap(){
        mapView = (MapView)findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        getNewLocationZoom(39.008224, -76.8984527, 15);
    }

    private void getNewLocationZoom(double latitude, double longitude, int zoom){
        GeoPoint ll = new GeoPoint(latitude, longitude);
        mapView.getController().setZoom(zoom);
        mapView.getController().setCenter(ll);

    }
}
