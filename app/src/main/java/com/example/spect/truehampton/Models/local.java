package com.example.spect.truehampton.Models;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.spect.truehampton.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class local extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private Marker marca;
    private double lat = 0;
    private double log = 0;
    private static int peticion_permiso_localizacion = 101;
    String mensaje;
    String direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        miUbicacion();

    }

    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},peticion_permiso_localizacion );
            return;
        }
        else
        {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1200,0,locationListener);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            ActualizarUbicacion(location);
            setLocation(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {
            mensaje=("GPS Activado");
            Mensaje();

        }

        @Override
        public void onProviderDisabled(String s) {
            mensaje=("GPS Desactivado");
            LocationStart();
            Mensaje();

        }
    };

    private void LocationStart() {
    LocationManager locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    final Boolean GPACtivado= locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    if (!GPACtivado)
    {
        Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }
    }


    private void Mensaje() {
        Toast.makeText(this, "mensaje", Toast.LENGTH_SHORT).show();
    }

    private void ActualizarUbicacion(Location location)
    {
        if ( location != null)
        {
            lat = location.getLatitude();
            log = location.getLongitude();
            setAgregarMarcador(lat, log);
        }
    }
    private void setAgregarMarcador(double lati,double logi)
    {
        LatLng corde = new LatLng(lati,logi);
        CameraUpdate cami;
        cami = CameraUpdateFactory.newLatLngZoom(corde,16);
        if (marca != null) marca.remove();
        marca=mMap.addMarker(new MarkerOptions().position(corde).title("Direccion:"+direccion));
        mMap.animateCamera(cami);
    }

    public  void setLocation(Location loc)
    {
        if (loc.getLatitude() != 0.0 & loc.getLongitude() != 0.0) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> lis = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 0);
                if (lis.isEmpty()) {
                    Address dircalle = lis.get(0);
                    direccion = (dircalle.getAddressLine(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}