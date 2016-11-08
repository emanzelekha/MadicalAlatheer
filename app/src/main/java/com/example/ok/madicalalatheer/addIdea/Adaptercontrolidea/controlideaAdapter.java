package com.example.ok.madicalalatheer.addIdea.Adaptercontrolidea;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ok.madicalalatheer.AddGoal.control.ControlAddGoal;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.addIdea.controlidea.cotroldisidea;

import java.util.List;

/*
 * Created by ok on 06/11/2016.
 */

public class controlideaAdapter extends RecyclerView.Adapter<controlideaAdapter.MyViewHolder> {

    private List<cotroldisidea> displayList;

    public controlideaAdapter(List<cotroldisidea> displayList) {
        this.displayList = displayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowidea, parent, false);
        TypefaceUtil.overrideFonts(parent.getContext(), itemView);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        cotroldisidea disUserControl = displayList.get(position);
        holder.Code.setText(disUserControl.getSerial1());
        holder.goal.setText(disUserControl.getGoal());
        holder.suggest.setText(disUserControl.getSuggest());
        holder.serial.setText((position+1)+"");
        holder.swt.setChecked(disUserControl.isActive());
        holder.job.setText(disUserControl.getJob());

    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Code, goal, suggest,job,serial;
        public SwitchCompat swt;

        public MyViewHolder(View view) {
            super(view);
            serial = (TextView) view.findViewById(R.id.serial);
            Code = (TextView) view.findViewById(R.id.Code);
            goal = (TextView) view.findViewById(R.id.goal);
            suggest = (TextView) view.findViewById(R.id.to);
            swt = (SwitchCompat) view.findViewById(R.id.Active);
            job=(TextView)view.findViewById(R.id.job);
        }
    }


}