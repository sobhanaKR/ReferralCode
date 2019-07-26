package com.example.referralcode;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class CartPageFragment extends Fragment {
    TextView timerTv;
    CountDownTimer countDownTimer;
    TextView shareMsg;
    CardView referCard;
    int timer = 0;
    private AlertDialog mAlert;
    Animation anim = new AlphaAnimation(0.0f, 1.0f);
    int discount;
    int diffAmount = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cart_layout, container, false);
        Bundle bundle = getArguments();
        final TextView nameTv, priceTv, reducedPrice, discountTv;
        ImageView imageView = rootView.findViewById(R.id.image_view);
        AppCompatButton placeOrderBtn = rootView.findViewById(R.id.place_order);
        timerTv = rootView.findViewById(R.id.timer_tv);
        shareMsg = rootView.findViewById(R.id.share_msg);
        referCard = rootView.findViewById(R.id.refer_card);
        nameTv = rootView.findViewById(R.id.name);
        priceTv = rootView.findViewById(R.id.price);
        reducedPrice = rootView.findViewById(R.id.reducedAmount);
        discountTv = rootView.findViewById(R.id.discount);
        final String imageUrl = bundle.getString("image");
        final String shareMsg = bundle.getString("share_msg");
        final String name = bundle.getString("name");
        final String price = bundle.getString("price");
        final String sku = bundle.getString("sku");
        discount = Integer.valueOf(price);
        nameTv.setText(name);
        priceTv.setText("Rs."+price);
        Picasso.with(getContext()).load(imageUrl).into(imageView);
        referCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discount = discount - 50;
                discountTv.setVisibility(View.VISIBLE);
                diffAmount = Integer.valueOf(price)-discount;
                discountTv.setText("You have saved Rs."+diffAmount);
                priceTv.setPaintFlags(priceTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                reducedPrice.setText("Rs."+discount);
                reducedPrice.setTextColor(Color.parseColor("#FF37B76F"));
                priceTv.setTextColor(Color.parseColor("#ffff0000"));
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.share_app_custom_lyt, null);
                dialogBuilder.setView(dialogView);
                TextView shareUsingTv = (TextView) dialogView.findViewById(R.id.share_using_title);
                RecyclerView shareMediumList = (RecyclerView) dialogView.findViewById(R.id.share_medium_list);
                shareMediumList.setHasFixedSize(true);
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                shareMediumList.setLayoutManager(llm);
                ShareAppAdapter shareAppAdapter = new ShareAppAdapter(getActivity(),shareMsg);
                shareMediumList.setAdapter(shareAppAdapter);
                mAlert = dialogBuilder.create();
                mAlert.show();            }
        });

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.fragment.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ThankYouPage thankYouPage = new ThankYouPage();
                Bundle bundle = new Bundle();
                bundle.putInt("price",diffAmount);
                thankYouPage.setArguments(bundle);
                fragmentTransaction.replace(R.id.container, thankYouPage, "");
                fragmentTransaction.replace(R.id.container, thankYouPage, "");
                fragmentTransaction.commitAllowingStateLoss();
            }
        });
        startTimer();
        return rootView;
    }

    private void startTimer() {


            countDownTimer= new CountDownTimer(60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timerTv.setText("00:" + String.valueOf(millisUntilFinished / 1000));

                    anim.setDuration(50); //You can manage the blinking time with this parameter
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    shareMsg.startAnimation(anim);
                }

                public void onFinish() {
                    timerTv.setText("00:00");
                    anim.cancel();
                    timerTv.setTextColor(Color.parseColor("#ffff0000"));
                    shareMsg.setTextColor(Color.parseColor("#ffff0000"));
                    if (timer == 0) {
                        startTimer();
                        timer++;
                    } else {
                        shareMsg.setTextColor(Color.parseColor("#191919"));
                        timerTv.setTextColor(Color.parseColor("#191919"));
                    }
                    shareMsg.setText("purchase the product with the reduced amount before this clock stops!");
                }
            }.start();
    }
}
