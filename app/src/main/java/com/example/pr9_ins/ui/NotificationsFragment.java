package com.example.pr9_ins.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr9_ins.Adapter.NotificationAdapter;
import com.example.pr9_ins.Model.NotificationModel;
import com.example.pr9_ins.R;
import com.example.pr9_ins.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<NotificationModel> notificationModelArrayList;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        recyclerView = view.findViewById(R.id.notificationRecycler);

        notificationModelArrayList = new ArrayList<>();
        notificationModelArrayList.add(new NotificationModel(R.drawable.h9,R.drawable.h10,"hitesh_deshmukh","liked your photo."));
        notificationModelArrayList.add(new NotificationModel(R.drawable.h9,R.drawable.h10,"hitesh_deshmukh","liked your photo."));

        NotificationAdapter notificationAdapter = new NotificationAdapter(notificationModelArrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(notificationAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}