package com.example.ok.madicalalatheer.AddGoal.AddGoalAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ok.madicalalatheer.AddGoal.control.ControlAddGoal;
import com.example.ok.madicalalatheer.R;

import java.util.List;

/*
 * Created by ok on 06/11/2016.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<ControlAddGoal> displayList;

    public Adapter(List<ControlAddGoal> displayList) {
        this.displayList = displayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addgoalrow, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ControlAddGoal disUserControl = displayList.get(position);
        //holder.serial.setText(disUserControl.getSerial1());

    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView serial;


        public MyViewHolder(View view) {
            super(view);
         //   serial = (TextView) view.findViewById(R.id.muldep);

        }
    }


}