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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atmlist);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.NETWORK_PROVIDER;
        mCmgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = mCmgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

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

     }


}
