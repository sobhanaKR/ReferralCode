package com.example.referralcode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThankYouPage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.thankyou_page_lyt, container, false);
        Bundle bundle = getArguments();
        TextView saveText = rootView.findViewById(R.id.save);
        saveText.setText("You have saved Rs."+bundle.getInt("price"));
        return rootView;
    }
}
