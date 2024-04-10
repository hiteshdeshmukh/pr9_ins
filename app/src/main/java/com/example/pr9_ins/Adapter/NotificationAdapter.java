package com.example.pr9_ins.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr9_ins.Model.NotificationModel;
import com.example.pr9_ins.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder> {



    ArrayList<NotificationModel> notificationModelArrayList;
    Context context;

    public NotificationAdapter(ArrayList<NotificationModel> notificationModelArrayList, Context context) {
        this.notificationModelArrayList = notificationModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notification_recycler_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        NotificationModel notificationModel = notificationModelArrayList.get(position);
        holder.profileimage1.setImageResource(notificationModel.getImage1());
        holder.postimage1.setImageResource(notificationModel.getImage1());
        holder.nameText.setText(notificationModel.getName1());
        holder.messageText.setText(notificationModel.getMessage1());
    }

    @Override
    public int getItemCount() {
        return notificationModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profileimage1, postimage1;
        TextView nameText, messageText;
        public viewHolder(@NonNull View itemView) {

            super(itemView);

            profileimage1 = itemView.findViewById(R.id.notificationImage1);
            postimage1 = itemView.findViewById(R.id.notificationImage2);
            nameText = itemView.findViewById(R.id.notificationName1);
            messageText = itemView.findViewById(R.id.notificationMessage1);

        }
    }
}
