package com.example.ok.madicalalatheer.addIdea.Adaptercontrolidea;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ok.madicalalatheer.AddGoal.DetalsAddGoal;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.addIdea.controlidea.cotroldisidea;

import java.util.List;

/*
 * Created by ok on 06/11/2016.
 */

public class controlideaAdapter extends RecyclerView.Adapter<controlideaAdapter.MyViewHolder> implements View.OnClickListener {

    private List<cotroldisidea> displayList;
    Context context;

    public controlideaAdapter(List<cotroldisidea> displayList) {
        this.displayList = displayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowidea, parent, false);
        context = itemView.getContext();
        TypefaceUtil.overrideFonts(parent.getContext(), itemView);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        cotroldisidea disUserControl = displayList.get(position);
        holder.Code.setText(disUserControl.getSerial1());
        holder.goal.setText(disUserControl.getGoal());
        holder.suggest.setText(disUserControl.getSuggest());
        holder.serial.setText((position + 1) + "");
        holder.swt.setChecked(disUserControl.isActive());
        holder.job.setText(disUserControl.getJob());
        holder.details.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.ideadetails:
                i = new Intent(context, DetalsAddGoal.class);
                i.putExtra("Find", "idea");
                context.startActivity(i);
                break;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Code, goal, suggest, job, serial, details;
        public SwitchCompat swt;

        public MyViewHolder(View view) {
            super(view);
            serial = (TextView) view.findViewById(R.id.serial);
            Code = (TextView) view.findViewById(R.id.Code);
            goal = (TextView) view.findViewById(R.id.goal);
            suggest = (TextView) view.findViewById(R.id.to);
            swt = (SwitchCompat) view.findViewById(R.id.Active);
            job = (TextView) view.findViewById(R.id.job);
            details = (TextView) view.findViewById(R.id.ideadetails);
        }
    }


}