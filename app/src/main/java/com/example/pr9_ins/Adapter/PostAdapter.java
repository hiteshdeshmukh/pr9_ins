package com.example.pr9_ins.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Gainmap;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr9_ins.Model.PostModel;
import com.example.pr9_ins.Model.UserModel;
import com.example.pr9_ins.R;
import com.example.pr9_ins.databinding.HomeDashboardSampleBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewHolder>{
    ArrayList<PostModel> list;
    Context context;

    public PostAdapter(ArrayList<PostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_dashboard_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PostModel postModel = list.get(position);
        Picasso.get()
                .load(postModel.getPostImage())
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(holder.binding.homePost0);

//        holder.binding.homePostLikeText.setText(postModel.getPostLike()+"");

        String description = postModel.getPostDescription();
        if (description.equals("")){
            holder.binding.homePostCaption0.setVisibility(View.GONE);
        }else {
            holder.binding.homePostCaption0.setText(postModel.getPostDescription());
            holder.binding.homePostCaption0.setVisibility(View.VISIBLE);
        }

        holder.binding.homePostComment0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Comment",Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(v.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fragment_comment);

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation1;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

            }
        });

        FirebaseDatabase.getInstance().getReference().child("Users")
                .child(postModel.getPostedBy()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel user = snapshot.getValue(UserModel.class);
                        Picasso.get()
                                .load(user.getProfilePhoto())
                                .placeholder(R.drawable.baseline_person_24)
                                .into(holder.binding.homeProfileImage0);
                        holder.binding.homeProfileName0.setText(user.getName());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        FirebaseDatabase.getInstance().getReference().child("posts").child(postModel.getPostId())
                        .child("likes").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            holder.binding.homePostLike0.setImageResource(R.drawable.baseline_favorite_24);
                        }else {
                            holder.binding.homePostLike0.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    FirebaseDatabase.getInstance().getReference()
                                            .child("posts").child(postModel.getPostId())
                                            .child("likes")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    FirebaseDatabase.getInstance().getReference()
                                                            .child("posts").child(postModel.getPostId())
                                                            .child("postLike")
                                                            .setValue(postModel.getPostLike() + 1 ).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    holder.binding.homePostLike0.setImageResource(R.drawable.baseline_favorite_24);
                                                                }
                                                            });
                                                }
                                            });
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        HomeDashboardSampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HomeDashboardSampleBinding.bind(itemView);
        }
    }
}
