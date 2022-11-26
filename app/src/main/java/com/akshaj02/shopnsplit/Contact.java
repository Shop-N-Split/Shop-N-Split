package com.akshaj02.shopnsplit;

import android.os.Parcel;
import android.os.Parcelable;
//import android.support.annotation.NonNull;

import androidx.annotation.NonNull;


public class Contact implements Parcelable {

    public String id,name,phone,label;

    Contact(String id, String name,String phone,String label){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.label=label;
    }

    protected Contact(Parcel in) {
        id = in.readString();
        name = in.readString();
        phone = in.readString();
        label = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @NonNull
    @Override
    public String toString()
    {
        return name+" | "+label+" : "+phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(label);
    }
}