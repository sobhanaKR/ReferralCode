package com.example.referralcode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder>  {
    List<ItemsModel> items;
    AppCompatActivity appCompatActivity;
    String imageUrl, shareMsg;
    public ListingAdapter(List<ItemsModel> items, String shareMsg, AppCompatActivity appCompatActivity) {
        this.items = items;
        this.appCompatActivity = appCompatActivity;
        this.shareMsg = shareMsg;
    }

    @NonNull
    @Override
    public ListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View convertView = inflater.inflate(R.layout.listing_item_layout, parent, false);
        return new ListingAdapter.ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListingAdapter.ViewHolder holder, final int position) {
     holder.productName.setText(items.get(position).item_name);
     holder.sku.setText("Sku: "+items.get(position).sku);
     holder.price.setText("Rs."+items.get(position).price);
     holder.brand.setText(items.get(position).brand);
     imageUrl = items.get(position).image_url;
     Picasso.with(holder.imageView.getContext()).load(items.get(position).image_url).into(holder.imageView);
     holder.cardView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             androidx.fragment.app.FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             ProductDetailPage productDetailPage = new ProductDetailPage();
             Bundle bundle = new Bundle();
             bundle.putString("image",items.get(position).image_url);
             bundle.putString("price",items.get(position).price);
             bundle.putString("sku",items.get(position).sku);
             bundle.putString("name",items.get(position).item_name);
             bundle.putString("share_msg",shareMsg);
             productDetailPage.setArguments(bundle);
             fragmentTransaction.replace(R.id.container, productDetailPage, "");
             fragmentTransaction.addToBackStack(productDetailPage.getClass().getSimpleName());
             fragmentTransaction.commitAllowingStateLoss();

         }
     });
    }

    private void openNextPage() {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView productName, sku, price, brand;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            productName = itemView.findViewById(R.id.item_name);
            sku = itemView.findViewById(R.id.sku);
            price = itemView.findViewById(R.id.price);
            brand = itemView.findViewById(R.id.brand);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
