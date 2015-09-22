package com.example.glamantech.samplemap;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Naresh on 9/17/2015.
 */
public class ATMLocationService extends AsyncTask<String, Void, String> {

    private InputStream is;
    ProgressDialog progress;

    StringBuilder sb;
    Context mContext;
    ServerResponse mResponse;

    public ATMLocationService(ATMListActivity listActivity, ServerResponse serverResponse) {
        // TODO Auto-generated constructor stub
        mContext = listActivity;
        mResponse = serverResponse;
    }

    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        progress = new ProgressDialog(mContext);
        progress.setMessage("Please wait while data is loading...");
        progress.show();
    }

    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub

        try {
            URL obj = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            is = con.getInputStream();

        } catch (IOException setter) {
            // TODO Auto-generated catch block
            setter.printStackTrace();

        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            sb = new StringBuilder();
            String line = null;
         while ((line = br.readLine()) != null) {

                sb.append(line + "\n");
            }
        } catch (Exception setter) {
            // TODO Auto-generated catch block
            setter.printStackTrace();
        }

        return sb.toString();
    }

    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        if (progress != null) {

            progress.cancel();
        }
        if(result != null) {
            Log.d("Server String", result);
            mResponse.setData(result);
        }

    }

}