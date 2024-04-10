package com.example.pr9_ins.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr9_ins.Model.HighlightModel;
import com.example.pr9_ins.R;

import java.util.ArrayList;

public class Highlightadapter extends RecyclerView.Adapter<Highlightadapter.viewHolder> {

    ArrayList<HighlightModel> highlightModelArrayList;
    Context context;

    public Highlightadapter(ArrayList<HighlightModel> highlightModelArrayList, Context context) {
        this.highlightModelArrayList = highlightModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_highlight_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        HighlightModel highlightModel = highlightModelArrayList.get(position);
        holder.highlightImage.setImageResource(highlightModel.getProfileHighlightImage());
        holder.highlightName.setText(highlightModel.getProfileHighlightName());
    }

    @Override
    public int getItemCount() {
        return highlightModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView highlightImage;
        TextView highlightName;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            highlightImage = itemView.findViewById(R.id.profileHighlightImage);
            highlightName = itemView.findViewById(R.id.profileHighlightName);


        }
    }
}
