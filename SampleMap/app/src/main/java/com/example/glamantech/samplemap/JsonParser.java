package com.example.glamantech.samplemap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 9/17/2015.
 */
public class JsonParser {

    List<ATMLocationDetails> dataList;
    ATMListActivity activity;
    ATMLocationDetails locationDetails;

    public JsonParser(String data, ATMListActivity listActivity) {

        activity = listActivity;
        dataList = new ArrayList<>();

        try {
            JSONObject dataObject = new JSONObject(data);
            JSONArray locations = dataObject.getJSONArray("locations");
            for (int i = 0; i < locations.length(); i++) {
                JSONObject singleLocationObject = locations.getJSONObject(i);
                locationDetails = new ATMLocationDetails();
                locationDetails.setName(singleLocationObject.getString(Utils.NAME));
                locationDetails.setState(singleLocationObject.getString(Utils.STATE));
                locationDetails.setLabel(singleLocationObject.getString(Utils.LABEL));
                locationDetails.setAddress(singleLocationObject.getString(Utils.ADDRESS));
                locationDetails.setZip(singleLocationObject.getString(Utils.ZIP));
                locationDetails.setLat(singleLocationObject.getString(Utils.LAT));
                locationDetails.setLng(singleLocationObject.getString(Utils.LNG));
                locationDetails.setDistance(Float.valueOf(singleLocationObject.getString(Utils.DISTANCE)));
                locationDetails.setLocationType(singleLocationObject.getString(Utils.LOCATIONTYPE));
                dataList.add(locationDetails);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        activity.setList(dataList);
    }
}
