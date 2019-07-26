package com.example.referralcode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

public class ProductDetailPage extends Fragment {
    CardView termsAndConditions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_detail_layout, container, false);
        Bundle bundle = getArguments();
        ImageView imageView = rootView.findViewById(R.id.image_view);
        AppCompatButton buyNow = rootView.findViewById(R.id.buy_now);
        final ScrollView scrollView = rootView.findViewById(R.id.scroll_view);
        TextView skuTv = rootView.findViewById(R.id.sku);
        TextView priceTv = rootView.findViewById(R.id.price);
        TextView nameTv = rootView.findViewById(R.id.name);
        termsAndConditions = rootView.findViewById(R.id.t_and_c);
        final String imageUrl = bundle.getString("image");
        final String shareMsg = bundle.getString("share_msg");
        final String name = bundle.getString("name");
        final String price = bundle.getString("price");
        final String sku = bundle.getString("sku");
        final TextView point1, point2, point3, point4;
        final ImageView arrow = rootView.findViewById(R.id.arrow);
        point1 = rootView.findViewById(R.id.point1);
        point2 = rootView.findViewById(R.id.point2);
        point3 = rootView.findViewById(R.id.point3);
        point4 = rootView.findViewById(R.id.point4);
        nameTv.setText(name);
        skuTv.setText(sku);
        priceTv.setText("Rs."+price);
        Picasso.with(getContext()).load(imageUrl).into(imageView);
        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (point1.getVisibility() == View.GONE) {
                    arrow.setRotation(180);
                    point2.setVisibility(View.VISIBLE);
                    point3.setVisibility(View.VISIBLE);
                    point4.setVisibility(View.VISIBLE);
                    point1.setVisibility(View.VISIBLE);
                } else {
                    arrow.setRotation(0);
                    point1.setVisibility(View.GONE);
                    point2.setVisibility(View.GONE);
                    point3.setVisibility(View.GONE);
                    point4.setVisibility(View.GONE);
                }
            }
        });
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.fragment.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CartPageFragment cartPageFragment = new CartPageFragment();
                Bundle bundle = new Bundle();
                bundle.putString("image",imageUrl);
                bundle.putString("price",price);
                bundle.putString("sku",sku);
                bundle.putString("name",name);
                bundle.putString("share_msg",shareMsg);
                cartPageFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container, cartPageFragment, "");
                fragmentTransaction.addToBackStack(cartPageFragment.getClass().getSimpleName());
                fragmentTransaction.commitAllowingStateLoss();
            }
        });

        return rootView;
    }
}
