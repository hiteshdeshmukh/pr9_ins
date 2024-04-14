package com.example.pr9_ins.ui.SpecificFeatures;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr9_ins.R;
import com.example.pr9_ins.databinding.FragmentCommentBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CommentFragment extends Fragment {

    FragmentCommentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCommentBinding.inflate(inflater,container,false);
        View view = binding.getRoot();





        return view;
    }
}