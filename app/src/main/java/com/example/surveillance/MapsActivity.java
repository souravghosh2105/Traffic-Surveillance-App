package com.example.surveillance;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String title;
    String vnum;
    String name;
    String phno;
    String addr;
    String start_time;
    String end_time;
    TextView vio;
    TextView vnumt;
    TextView namet;
    TextView phnot;
    TextView addrt;
    TextView start_timet;
    TextView end_timet;
    Float lat;
    Float lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_card);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle b=getIntent().getExtras();
        title=b.getString("title");
        vnum=b.getString("vnum");
        name=b.getString("name");
        phno=b.getString("number");
        addr=b.getString("address");
        start_time=b.getString("start_time");
        end_time=b.getString("end_time");
        lat=b.getFloat("lat");
        lon=b.getFloat("lon");
        vio= findViewById(R.id.vio);
        vnumt= findViewById(R.id.vnum);
        namet= findViewById(R.id.name);
        phnot= findViewById(R.id.phno);
        addrt= findViewById(R.id.addr);
        start_timet= findViewById(R.id.start_time);
        end_timet= findViewById(R.id.end_time);
        vio.setText(title);
        vnumt.setText(vnum);
        namet.setText(name);
        phnot.setText(phno);
        addrt.setText(addr);
        start_timet.setText(start_time);
        end_timet.setText(end_time);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(lat, lon);
        mMap.setMinZoomPreference(15);
        mMap.addMarker(new MarkerOptions().position(location).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

    }



    @Override
    public void onBackPressed(){
        finish();
    }
}
