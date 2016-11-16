package com.example.ok.madicalalatheer.AddGoal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ok.madicalalatheer.AddGoal.AddGoalAdapter.Adapter;
import com.example.ok.madicalalatheer.AddGoal.control.ControlAddGoal;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayGoal extends Fragment {

    private List<ControlAddGoal> disList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter mAdapter;

    public DisplayGoal() {
        // Required empty public constructor
    }

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_display_goal, container, false);
        TypefaceUtil.overrideFonts(getContext(), v);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_viewGoal);
        mAdapter = new Adapter(disList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
        return v;
    }

    private void prepareMovieData() {
        Boolean[] buttons = {true, true, false, false, true};
        Boolean[] active = {true, true, false, false, true};
        String[] code = {"1", "2", "3", "4", "5"};
        String[] to = {"عن", "عن", "عن", "عن", "عن"};
        String[] goals = {"احمد عبدالحميد شعبان السلامونى", "احمد عبدالحميد شعبان السلامونى", "احمد عبدالحميد شعبان السلامونى"
                , "احمد عبدالحميد شعبان السلامونى", "احمد عبدالحميد شعبان السلامونى"};


        for (int x = 0; x < goals.length; x++) {
            ControlAddGoal disUserControl = new ControlAddGoal(code[x], goals[x], to[x], active[x], buttons[x]);
            disList.add(disUserControl);
            mAdapter.notifyDataSetChanged();


        }
    }
}
