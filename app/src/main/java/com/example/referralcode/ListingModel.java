package com.example.referralcode;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ListingModel implements Parcelable {
    List<UserModel> user;
    List<ItemsModel> items;
    String share_message;

    public String getShare_message() {
        return share_message;
    }

    public void setShare_message(String share_message) {
        this.share_message = share_message;
    }

    public List<UserModel> getUser() {
        return user;
    }

    public void setUser(List<UserModel> user) {
        this.user = user;
    }

    public List<ItemsModel> getItems() {
        return items;
    }

    public void setItems(List<ItemsModel> items) {
        this.items = items;
    }

    public static Creator<ListingModel> getCREATOR() {
        return CREATOR;
    }

    protected ListingModel(Parcel in) {
        user = in.createTypedArrayList(UserModel.CREATOR);
        items = in.createTypedArrayList(ItemsModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(user);
        dest.writeTypedList(items);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListingModel> CREATOR = new Creator<ListingModel>() {
        @Override
        public ListingModel createFromParcel(Parcel in) {
            return new ListingModel(in);
        }

        @Override
        public ListingModel[] newArray(int size) {
            return new ListingModel[size];
        }
    };
}
