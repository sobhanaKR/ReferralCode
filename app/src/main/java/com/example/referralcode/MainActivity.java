package com.example.referralcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListingFragment listingFragment = new ListingFragment();
        fragmentTransaction.add(R.id.container, listingFragment, "");
        fragmentTransaction.commitAllowingStateLoss();
    }
}
