package com.example.pr9_ins.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr9_ins.Model.CommentModel;
import com.example.pr9_ins.Model.UserModel;
import com.example.pr9_ins.R;
import com.example.pr9_ins.databinding.CommentRecyclerSampleBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.viewHolder> {

    Context context;
    ArrayList<CommentModel> commentModelArrayList;

    public CommentAdapter(Context context, ArrayList<CommentModel> commentModelArrayList) {
        this.context = context;
        this.commentModelArrayList = commentModelArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_recycler_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        CommentModel commentModel = commentModelArrayList.get(position);
        holder.binding.commentSampleText1.setText(commentModel.getCommentBody());
        holder.binding.commentSampleTime1.setText(commentModel.getCommentedAt()+"");

        FirebaseDatabase.getInstance().getReference().child("Users").child(commentModel.getCommentedBy())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        Picasso.get()
                                .load(userModel.getProfilePhoto())
                                .placeholder(R.drawable.baseline_person_outline_24)
                                .into(holder.binding.commentSampleImage1);
                        holder.binding.commentSampleText1.setText(userModel.getName());
                        holder.binding.commentSampleText2.setText(commentModel.getCommentBody());
                        //holder.binding.commentSampleText1.setText(Html.fromHtml("<b>" +userModel.getName()+ "</b>" + ": "+ commentModel.getCommentBody()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }

    @Override
    public int getItemCount() {
        return commentModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        CommentRecyclerSampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CommentRecyclerSampleBinding.bind(itemView);
        }
    }
}
