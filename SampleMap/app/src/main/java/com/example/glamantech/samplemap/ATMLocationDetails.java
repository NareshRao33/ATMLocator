package com.example.glamantech.samplemap;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Naresh on 9/17/2015.
 */
public class ATMLocationDetails implements Parcelable {

    String state,label,address,city,zip,name,lat,lng,bank,locationType;
    float distance;

    protected ATMLocationDetails(Parcel in) {
        name = in.readString();
        state = in.readString();
        label = in.readString();
        address = in.readString();
        zip = in.readString();
        lat = in.readString();
        lng = in.readString();
        distance = in.readFloat();
        locationType = in.readString();


    }

    public static final Parcelable.Creator<ATMLocationDetails> CREATOR = new Creator<ATMLocationDetails>() {
        @Override
        public ATMLocationDetails createFromParcel(Parcel in) {
            return new ATMLocationDetails(in);
        }

        @Override
        public ATMLocationDetails[] newArray(int size) {
            return new ATMLocationDetails[size];
        }
    };

    public ATMLocationDetails() {

    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public static final  Parcelable.Creator<ATMLocationDetails> Creator = new Creator<ATMLocationDetails>() {
        @Override
        public ATMLocationDetails createFromParcel(Parcel source) {

            ATMLocationDetails details = new ATMLocationDetails(source);

            return new ATMLocationDetails(source);
        }

        @Override
        public ATMLocationDetails[] newArray(int size) {

            return new ATMLocationDetails[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(state);
        parcel.writeString(label);
        parcel.writeString(address);
        parcel.writeString(zip);
        parcel.writeString(lat);
        parcel.writeString(lng);
        parcel.writeFloat(distance);
        parcel.writeString(locationType);
    }

}
