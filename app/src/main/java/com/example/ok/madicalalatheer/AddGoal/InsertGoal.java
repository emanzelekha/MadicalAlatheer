package com.example.ok.madicalalatheer.AddGoal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ok.madicalalatheer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsertGoal extends Fragment {


    public InsertGoal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert_goal, container, false);
    }

}
