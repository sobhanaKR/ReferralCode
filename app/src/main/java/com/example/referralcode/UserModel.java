package com.example.referralcode;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    String name;
    String email;
    String phone;
    String referral_code;
    String used_code;
    String type;

    protected UserModel(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        referral_code = in.readString();
        used_code = in.readString();
        type = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(referral_code);
        parcel.writeString(used_code);
        parcel.writeString(type);
    }
}
