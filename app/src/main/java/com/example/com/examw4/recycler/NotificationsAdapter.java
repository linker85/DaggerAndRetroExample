package com.example.com.examw4.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.com.examw4.R;
import com.example.com.examw4.model2.SearchResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raul on 09/11/2016.
 */
public class NotificationsAdapter extends RecyclerView.Adapter <NotificationsAdapter.ViewHolder> {

    // List of data
    private List<SearchResult> notificationsArrayList;
    private static final String TAG = "NotifAdapterTAG_";

    public NotificationsAdapter(List<SearchResult> notificationsArrayList) {
        this.notificationsArrayList = notificationsArrayList;
    }

    public NotificationsAdapter() {}

    public List<SearchResult> getNotificationsArrayList() {
        return notificationsArrayList;
    }

    public void setNotificationsArrayList(List<SearchResult> notificationsArrayList) {
        this.notificationsArrayList = notificationsArrayList;
    }

    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate parent layout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate item
        View termView = inflater.inflate(R.layout.notifications_item, parent, false);
        // Return itself
        return new NotificationsAdapter.ViewHolder(termView);
    }

    @Override
    public void onBindViewHolder(NotificationsAdapter.ViewHolder holder, int position) {
        // Get data based on position
        SearchResult notification = notificationsArrayList.get(position);

        Log.d(TAG, "onBindViewHolder: " + notification.toString());

        TextView textViewFullName = holder.textViewFullName;
        try {
            textViewFullName.setText("" + notification.getListing().getCity());
        } catch (Exception e) {
            textViewFullName.setText("isAvailable");
        }

        TextView textViewName = holder.textViewName;
        try {
            textViewName.setText("" + notification.getListing().getName());
        } catch (Exception e) {
            textViewName.setText("Pricing.rate.amount");
        }

        holder.myNotification = notification;
    }

    @Override
    public int getItemCount() {
        return this.notificationsArrayList.size();
    }

    // View holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        public TextView textViewFullName;
        @BindView(R.id.fullName)
        public TextView textViewName;
        // Data
        public SearchResult myNotification;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder: ");
            // New instance of components inside the row
            //textViewName     = (TextView) itemView.findViewById(R.id.name);
            //textViewFullName = (TextView) itemView.findViewById(R.id.fullName);
            ButterKnife.bind(this, itemView);
            // Click individual items
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: ");
                }
            });
        }
    }
}