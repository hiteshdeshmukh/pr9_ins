package com.example.pr9_ins.ui;

import static com.example.pr9_ins.databinding.FragmentProfileBinding.bind;
import static com.example.pr9_ins.databinding.FragmentProfileBinding.inflate;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr9_ins.Adapter.Highlightadapter;
import com.example.pr9_ins.Model.HighlightModel;
import com.example.pr9_ins.Model.UserModel;
import com.example.pr9_ins.R;
import com.example.pr9_ins.SignInPages.SignInActivity;
import com.example.pr9_ins.databinding.FragmentProfileBinding;
import com.example.pr9_ins.ui.SpecificFeatures.FollowersList;
import com.example.pr9_ins.ui.SpecificFeatures.FollowingScreenActivity;
import com.example.pr9_ins.ui.SpecificFeatures.ProfileEditActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    RecyclerView highlightRecycler;
    ArrayList<HighlightModel> highlightModelArrayList;
    private FragmentProfileBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        database.getReference().child("Users").child(auth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()){
                            UserModel user = snapshot.getValue(UserModel.class);
                            Picasso.get()
                                    .load(user.getProfilePhoto())
                                    .placeholder(R.drawable.baseline_person_24)
                                    .into(binding.ProfileImage1);

                            binding.ProfileName1.setText(user.getName());
                            binding.followersCount1.setText(user.getFollowerCount()+"");
                            binding.profileBio1.setText(user.getBio());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        binding.followers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FollowersList.class);
                startActivity(intent);
            }
        });

        binding.following1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FollowingScreenActivity.class);
                startActivity(intent);
            }
        });


        highlightRecycler = view.findViewById(R.id.highlightRecycler);

        highlightModelArrayList = new ArrayList<>();
        highlightModelArrayList.add(new HighlightModel(R.drawable.h9,"HD"));
        highlightModelArrayList.add(new HighlightModel(R.drawable.h9,"HD"));
        highlightModelArrayList.add(new HighlightModel(R.drawable.h9,"HD"));

        Highlightadapter highlightadapter = new Highlightadapter(highlightModelArrayList, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        highlightRecycler.setLayoutManager(linearLayoutManager);
        highlightRecycler.setAdapter(highlightadapter);

        binding.profileEditButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
                startActivity(intent);
            }
        });

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}