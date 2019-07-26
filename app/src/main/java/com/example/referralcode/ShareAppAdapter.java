package com.example.referralcode;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by francis on 13/2/18.
 */

public class ShareAppAdapter extends RecyclerView.Adapter<ShareAppAdapter.ShareAppViewHolder> {

    private Activity activity;
    List<String> appNames = new ArrayList<String>();
    List<Drawable> appIconList = new ArrayList<Drawable>();
    Intent intent;
    List<ResolveInfo> activities;
    public ShareAppAdapter(Activity activity, String shareText) {
        this.activity = activity;
        intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT,  "Welcome to Caratlane Android App");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareText);
        activities = activity.getPackageManager().queryIntentActivities (intent, 0);
        HashMap<String, Object> topMediums = new HashMap<>();
        for (int i=0; i<activities.size(); i++) {
            ResolveInfo info = activities.get(i);
            if(info != null &&
                    info.loadLabel(activity.getPackageManager()) != null) {
                String mediumName = info.loadLabel(activity.getPackageManager()).toString();
                switch (mediumName) {
                    case "WhatsApp":
                        topMediums.put("WhatsApp", info);
                        activities.remove(i);
                        break;
                    case "Hangouts":
                        topMediums.put("Hangouts", info);
                        activities.remove(i);
                        break;
                    case "Facebook":
                        topMediums.put("Facebook", info);
                        activities.remove(i);
                        break;
                    case "Tweet":
                        topMediums.put("Tweet", info);
                        activities.remove(i);
                        break;
                }
            }
        }
        String[] mediumName = {"WhatsApp", "Hangouts", "Facebook", "Tweet"};
        int addPosition = 0;
        for(int i=0; i<mediumName.length; i++) {
            if(topMediums.get(mediumName[i]) != null){
                ResolveInfo info = (ResolveInfo) topMediums.get(mediumName[i]);
                activities.add(addPosition, info);
                addPosition++;
            }
        }
        for (ResolveInfo info : activities) {
            appNames.add(info.loadLabel(activity.getPackageManager()).toString());
            appIconList.add(info.loadIcon(activity.getPackageManager()));
        }
    }


    @Override
    public ShareAppViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View convertView = inflater.inflate(R.layout.share_item_in_adapter, null);
        return new ShareAppViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ShareAppViewHolder holder, final int position) {
        if(appIconList != null && appIconList.size() > position && appIconList.get(position) != null
                && appNames != null && appNames.size() > position && appNames.get(position) != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.shareMediumIv.setBackground(appIconList.get(position));
            }
            holder.shareMediumTv.setText(appNames.get(position));
            holder.shareMediumLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ResolveInfo info = activities.get(position);
                    intent.setPackage(info.activityInfo.packageName);
                    activity.startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(activities == null) return 0;
        return activities.size();
    }

    public class ShareAppViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout shareMediumLyt;
        ImageView shareMediumIv;
        TextView shareMediumTv;

        public ShareAppViewHolder(View itemView) {
            super(itemView);
            shareMediumLyt = (RelativeLayout) itemView.findViewById(R.id.share_medium_lyt);
            shareMediumIv = (ImageView) itemView.findViewById(R.id.share_medium_img_view);
            shareMediumTv = (TextView) itemView.findViewById(R.id.share_medium_txt_view);
        }
    }
}
