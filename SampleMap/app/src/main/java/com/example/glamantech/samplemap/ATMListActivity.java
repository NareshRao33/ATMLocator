package com.example.glamantech.samplemap;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ATMListActivity extends AppCompatActivity implements ServerResponse {

    ListView atmList;
    String requestUrl;
    Utils utils;
    JsonParser parser;
    ATMLocationService mService;
    List<ATMLocationDetails> atmDataList;
    String lat, lng;
    Location location;
    String provider;
    ConnectivityManager mCmgr;
    NetworkInfo netInfo;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atmlist);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.NETWORK_PROVIDER;
        mCmgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = mCmgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(provider);
        utils = new Utils();
        atmList = (ListView)findViewById(R.id.atmlist);

        lat = "?lat="+location.getLatitude();
        lng = "&lng="+location.getLongitude();

        locationServiceCall();

        Toast.makeText(this,""+location.getLatitude()+location.getLongitude(),Toast.LENGTH_LONG).show();

    }

    public void locationServiceCall(){
        if(netInfo.isConnected()){
            requestUrl = utils.chaseApi +lat+lng;
            mService = new ATMLocationService(this,(com.example.glamantech.samplemap.ServerResponse)ATMListActivity.this);
            mService.execute(requestUrl);
        }else{
            Toast.makeText(this, getResources().getString(R.string.netinfo),
                    Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void setData(String data) {

        parser = new JsonParser(data,this);
    }
     public void setList(List<ATMLocationDetails> list){
         atmDataList = list;
         ATMListAdapter adapter = new ATMListAdapter(this,list);
         atmList.setAdapter(adapter);
        atmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(ATMListActivity.this,MapsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelable("Location",atmDataList.get(position));
               // Toast.makeText(ATMListActivity.this,atmDataList.get(position).getAddress(),Toast.LENGTH_LONG).show();
                mIntent.putExtras(mBundle);
                startActivity(mIntent);
            }
        });
     }


}
