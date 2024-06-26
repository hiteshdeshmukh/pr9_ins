package com.example.pr9_ins.ui.SpecificFeatures;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pr9_ins.Adapter.FollowersAdapter;
import com.example.pr9_ins.Adapter.SearchUsersAdapter;
import com.example.pr9_ins.Model.FollowModel;
import com.example.pr9_ins.Model.UserModel;
import com.example.pr9_ins.databinding.ActivityFollowersListBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FollowersList extends AppCompatActivity {

    ActivityFollowersListBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ArrayList<FollowModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFollowersListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        FollowersAdapter searchUsersAdapter = new FollowersAdapter(list, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.followersRecycler1.setLayoutManager(layoutManager);
        binding.followersRecycler1.setAdapter(searchUsersAdapter);

        database.getReference().child("Users")
                .child(auth.getUid())
                .child("followers").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            list.clear();
                            FollowModel followers = dataSnapshot.getValue(FollowModel.class);
                            if (!dataSnapshot.getKey().equals(FirebaseAuth.getInstance().getUid())) {
                                list.add(followers);
                            }
                        }
                        searchUsersAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}