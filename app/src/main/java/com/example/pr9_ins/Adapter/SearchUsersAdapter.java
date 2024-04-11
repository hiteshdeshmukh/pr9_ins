package com.example.pr9_ins.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr9_ins.Model.FollowModel;
import com.example.pr9_ins.Model.UserModel;
import com.example.pr9_ins.R;
import com.example.pr9_ins.databinding.SearchAllUsersSampleBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class SearchUsersAdapter extends RecyclerView.Adapter<SearchUsersAdapter.viewHolder>{

    Context context;
    ArrayList<UserModel> list;

    public SearchUsersAdapter(Context context, ArrayList<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.search_all_users_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        UserModel user = list.get(position);
        Picasso.get()
                .load(user.getProfilePhoto())
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(holder.binding.searchImage1);
        holder.binding.searchName1.setText(user.getName());

        FirebaseDatabase.getInstance().getReference()
                .child("Users").child(user.getUserId()).child("followers")
                .child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            holder.binding.searchFollowButton.setBackgroundColor(Color.WHITE);
                            holder.binding.searchFollowButton.setText("Following");
                            holder.binding.searchFollowButton.setEnabled(false);
                        }else{
                            holder.binding.searchFollowButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    FollowModel follow = new FollowModel();
                                    follow.setFollowedBy(FirebaseAuth.getInstance().getUid());
                                    follow.setFollowedAt(new Date().getTime());

                                    FirebaseDatabase.getInstance().getReference()
                                            .child("Users")
                                            .child(user.getUserId())
                                            .child("followers")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .setValue(follow).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    FirebaseDatabase.getInstance().getReference()
                                                            .child("Users")
                                                            .child(user.getUserId())
                                                            .child("followerCount")
                                                            .setValue(user.getFollowerCount() + 1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    holder.binding.searchFollowButton.setBackgroundColor(Color.WHITE);
                                                                    holder.binding.searchFollowButton.setText("Following");
                                                                    holder.binding.searchFollowButton.setEnabled(false);
                                                                    Toast.makeText(v.getContext(), "You Followed "+user.getName(), Toast.LENGTH_SHORT).show();
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

    public static class viewHolder extends RecyclerView.ViewHolder{
        SearchAllUsersSampleBinding binding;
        public viewHolder (@NonNull View itemView){
            super(itemView);
            binding = SearchAllUsersSampleBinding.bind(itemView);
        }
    }
}
