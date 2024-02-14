package com.mart.cart_activity.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mart.cart_Activity.R;

import java.util.ArrayList;
import java.util.List;
public class
NotificationActivity extends AppCompatActivity {

    private RecyclerView notificationRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationRecyclerView = findViewById(R.id.notificationRecyclerView);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data for notifications
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("Welcome", "Thank you for part of our group.", "2 hours ago"));



        NotificationAdapter notificationAdapter = new NotificationAdapter(notifications);
        notificationRecyclerView.setAdapter(notificationAdapter);
    }

    private class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

        private List<Notification> notifications;

        public NotificationAdapter(List<Notification> notifications) {
            this.notifications = notifications;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Notification notification = notifications.get(position);
            holder.notificationTitle.setText(notification.getTitle());
            holder.notificationMessage.setText(notification.getMessage());
            holder.notificationTimestamp.setText(notification.getTimestamp());

            // Set click listener for each notification item
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle notification click, for example, start a new activity
                    Intent intent = new Intent(NotificationActivity.this, NotificationDetailsActivity.class);
                    intent.putExtra("title", notification.getTitle());
                    intent.putExtra("message", notification.getMessage());
                    intent.putExtra("timestamp", notification.getTimestamp());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return notifications.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView notificationTitle;
            TextView notificationMessage;
            TextView notificationTimestamp;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                notificationTitle = itemView.findViewById(R.id.notificationTitle);
                notificationMessage = itemView.findViewById(R.id.notificationMessage);
                notificationTimestamp = itemView.findViewById(R.id.notificationTimestamp);
            }
        }
    }

    private static class Notification {
        private String title;
        private String message;
        private String timestamp;

        public Notification(String title, String message, String timestamp) {
            this.title = title;
            this.message = message;
            this.timestamp = timestamp;
        }

        public String getTitle() {
            return title;
        }

        public String getMessage() {
            return message;
        }

        public String getTimestamp() {
            return timestamp;
        }
    }
}
