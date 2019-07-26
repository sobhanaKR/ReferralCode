package com.example.referralcode;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemsModel implements Parcelable {
    String item_name;
    String sku;
    String image_url;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static Creator<ItemsModel> getCREATOR() {
        return CREATOR;
    }

    String brand;
    String price;

    protected ItemsModel(Parcel in) {
        item_name = in.readString();
        sku = in.readString();
        image_url = in.readString();
        brand = in.readString();
        price = in.readString();
    }

    public static final Creator<ItemsModel> CREATOR = new Creator<ItemsModel>() {
        @Override
        public ItemsModel createFromParcel(Parcel in) {
            return new ItemsModel(in);
        }

        @Override
        public ItemsModel[] newArray(int size) {
            return new ItemsModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(item_name);
        parcel.writeString(sku);
        parcel.writeString(image_url);
        parcel.writeString(brand);
        parcel.writeString(price);
    }
}
