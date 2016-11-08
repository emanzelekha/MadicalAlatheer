package com.example.ok.madicalalatheer.AddGoal.AddGoalAdapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ok.madicalalatheer.AddGoal.control.ControlAddGoal;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

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
        TypefaceUtil.overrideFonts(parent.getContext(), itemView);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NumberFormat nf= NumberFormat.getInstance(new Locale("ar","EG"));//formate
        ControlAddGoal disUserControl = displayList.get(position);
        holder.Code.setText(nf.format(Integer.parseInt(disUserControl.getSerial1())));
        holder.goal.setText(disUserControl.getGoal());
        holder.to.setText(disUserControl.getTo());

        holder.serial.setText(nf.format(position+1));
        holder.swt.setChecked(disUserControl.isActive());

    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Code, goal, to,serial;
        public SwitchCompat swt;

        public MyViewHolder(View view) {
            super(view);
            serial = (TextView) view.findViewById(R.id.serial);
            Code = (TextView) view.findViewById(R.id.Code);
            goal = (TextView) view.findViewById(R.id.goal);
            to = (TextView) view.findViewById(R.id.to);
            swt = (SwitchCompat) view.findViewById(R.id.Active);
        }
    }


}