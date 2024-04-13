package com.example.pr9_ins.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr9_ins.Adapter.PostAdapter;
import com.example.pr9_ins.Adapter.StoryAdapter;
import com.example.pr9_ins.Model.PostModel;
import com.example.pr9_ins.Model.StoryModel;
import com.example.pr9_ins.Model.UserModel;
import com.example.pr9_ins.R;
import com.example.pr9_ins.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView storyRecycler, dashboardRecycler;
    ArrayList<StoryModel> storyModelArrayList;
    ArrayList<PostModel> postModelArrayList;
    private FragmentHomeBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        database.getReference().child("Users").child(auth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            UserModel user = snapshot.getValue(UserModel.class);

                            Picasso.get()
                                    .load(user.getProfilePhoto())
                                    .placeholder(R.drawable.baseline_person_outline_24)
                                    .into(binding.homeStoryImage1);
                            binding.homeStoryName0.setText(user.getName());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        storyRecycler = view.findViewById(R.id.storyRecycler);

        storyModelArrayList = new ArrayList<>();
        storyModelArrayList.add(new StoryModel(R.drawable.h10,"Hitesh D"));
        storyModelArrayList.add(new StoryModel(R.drawable.h10,"Hitesh D"));
        storyModelArrayList.add(new StoryModel(R.drawable.h9,"Hitesh D"));
        storyModelArrayList.add(new StoryModel(R.drawable.h10,"Hitesh D"));
        storyModelArrayList.add(new StoryModel(R.drawable.h9,"Hitesh D"));

        StoryAdapter adapter = new StoryAdapter(storyModelArrayList, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false);
        storyRecycler.setLayoutManager(linearLayoutManager);
        storyRecycler.setAdapter(adapter);



        dashboardRecycler = view.findViewById(R.id.dashboardRecycler);
        postModelArrayList = new ArrayList<>();

        PostAdapter postAdapter = new PostAdapter(postModelArrayList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        dashboardRecycler.setLayoutManager(layoutManager);
        dashboardRecycler.setAdapter(postAdapter);

        database.getReference().child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postModelArrayList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    PostModel post = dataSnapshot.getValue(PostModel.class);
                    post.setPostId(dataSnapshot.getKey());
                    postModelArrayList.add(post);
                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}