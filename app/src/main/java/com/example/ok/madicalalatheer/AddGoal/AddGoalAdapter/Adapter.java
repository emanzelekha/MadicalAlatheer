package com.example.ok.madicalalatheer.AddGoal.AddGoalAdapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.AddGoal.DetalsAddGoal;
import com.example.ok.madicalalatheer.AddGoal.control.ControlAddGoal;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

/*
 * Created by ok on 06/11/2016.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements View.OnClickListener{

    private List<ControlAddGoal> displayList;
    Context context;
    public Adapter(List<ControlAddGoal> displayList) {
        this.displayList = displayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addgoalrow, parent, false);
        context=itemView.getContext();
        TypefaceUtil.overrideFonts(parent.getContext(), itemView);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("ar", "EG"));//formate
        ControlAddGoal disUserControl = displayList.get(position);

        holder.Code.setText(nf.format(Integer.parseInt(disUserControl.getSerial1())));
        holder.goal.setText(disUserControl.getGoal());
        holder.to.setText(disUserControl.getTo());
        holder.serial.setText(nf.format(position + 1));
        holder.swt.setChecked(disUserControl.isActive());
        holder.GoalDetails.setOnClickListener(this);
        holder.edit.setOnClickListener(this);
        holder.delet.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.GoalDetails:
                i=new Intent(context, DetalsAddGoal.class);
                i.putExtra("Find","goal");
                context.startActivity(i);
                break;
            case R.id.editgoal:
                i=new Intent(context, DetalsAddGoal.class);
                i.putExtra("InsertGoal","1");
                context.startActivity(i);
                break;
            case R.id.deletegoal:
                try {
                    RequestParams params = new RequestParams();
                    params.put("request","deletgoal");
                    params.put("goalid","1");
                    Delet(params);
                } catch (Exception ex) {
                    Toast.makeText(context, "Exception" + ex, Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Code, goal, to, serial,details,delet,edit;
        public View GoalDetails;
        public SwitchCompat swt;


        public MyViewHolder(View view) {
            super(view);
            details = (TextView) view.findViewById(R.id.detailsgoal);
            delet = (TextView) view.findViewById(R.id.deletegoal);
            edit = (TextView) view.findViewById(R.id.editgoal);
            serial = (TextView) view.findViewById(R.id.serial);
            Code = (TextView) view.findViewById(R.id.Code);
            goal = (TextView) view.findViewById(R.id.goal);
            to = (TextView) view.findViewById(R.id.to);
            swt = (SwitchCompat) view.findViewById(R.id.Active);
            GoalDetails = view.findViewById(R.id.GoalDetails);
        }
    }

    public void Delet(RequestParams params) throws JSONException {

        AsyncHttpClient.post("", params, new JsonHttpResponseHandler() {
            ProgressDialog progressDialog;

            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(context);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("جارى البحث...");
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.e("onSuccess", response + "");
                Log.e("onSuccess", response.length() + "");
                try {


                } catch (Exception ex) {

                    Toast.makeText(context, "اشاره النت ضغيفه", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(context, "onFailure", Toast.LENGTH_LONG).show();
                Log.e("onFailure", "----------" + responseString);

            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressDialog.dismiss();
            }
        });


    }
}