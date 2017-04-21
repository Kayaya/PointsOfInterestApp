package com.example.afonso.pointsofinterestapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


public class MainActivity extends Activity implements LocationListener{

    MapView mapView;
    private Button button;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private TextView coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Download OSM maps*/
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        mapView = (MapView)findViewById(R.id.mapView);

        mapView.getController().setZoom(15);
        mapView.setBuiltInZoomControls(true);
        mapView.getController().setCenter(new GeoPoint(50.9319, -1.4011));

        //initMap();

        /*button = (Button) findViewById(R.id.buttonCurrentLocation);
        coordinates = (TextView) findViewById(R.id.tv);*/

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        catch (SecurityException se){

        }

    }/*end of onCreate*/

    private void initMap (){
        mapView.setBuiltInZoomControls(true);
        getNewLocationZoom(39.008224, -76.8984527, 15);
    }

    private void getNewLocationZoom(double latitude, double longitude, int zoom){
        GeoPoint ll = new GeoPoint(latitude, longitude);
        mapView.getController().setZoom(zoom);
        mapView.getController().setCenter(ll);
    }


    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "Location=" +
                location.getLatitude() + " " +
                location.getLongitude(), Toast.LENGTH_LONG).show();
        mapView.getController().setCenter(new GeoPoint(location.getLatitude(), location.getLongitude()));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(this, "Status changed: " + status, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Provider" + provider + "enabled", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Provider" + provider + "disabled", Toast.LENGTH_LONG).show();

    }
}
