package com.example.ok.madicalalatheer.AddGoal.AddGoalAdapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.AddGoal.AddGoal;
import com.example.ok.madicalalatheer.AddGoal.DetalsAddGoal;
import com.example.ok.madicalalatheer.AddGoal.control.ControlAddGoal;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.pherialize.MixedArray;
import com.example.ok.madicalalatheer.pherialize.Pherialize;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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
        final ControlAddGoal disUserControl = displayList.get(position);

        holder.Code.setText(disUserControl.getSerial1());
        holder.goal.setText(disUserControl.getGoal());
        MixedArray list = Pherialize.unserialize(disUserControl.getTo()).toArray();
        System.out.println(list + "Onsycess");
        SharedPreferences pref = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        String cheackId[] = pref.getString("cheackId1", "").split(",");
        String cheackName[] = pref.getString("cheackName1", "").split(",");
        String maindep = pref.getString("maindep", "");
      //  List<String> cheackIdl = new ArrayList<>(cheackId);
        //List<String> cheackNamel = new ArrayList<>(cheackName);
        String[] maindepl = maindep.split(" ");


        System.out.println(pref.getString("cheackName1", "")+"gdyhgtdfyg");
        String out = "";
        String Dep = "";
        for (int i = 0; i < list.size(); i++) {
            for (int j=0;j<cheackId.length;j++){
                if(list.get(i).equals(cheackId[j])){
                    if(out.isEmpty()){
                    out +=cheackName[j];}else{
                        out +=" , "+cheackName[j];
                    }
                    Dep =maindepl[j+1];
                }
         // out += cheackName[cheackId.length-cheackId.indexOf(list.get(i))] + " , ";
          //  Dep = maindepl[(cheackIdl.indexOf(list.get(i))) ]+ "";
            //out+=;
        }}
        final String outcheck=out;
        final String Depout = Dep;
        System.out.println(pref.getString("maindep", "")+"gdyhgtdfyg");
        System.out.println( Dep+"gdyhgtdfyg");
        holder.to.setText(out + "");
        holder.serial.setText(position + 1 + "");
        holder.swt.setChecked(disUserControl.isActive());
        holder.GoalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(context, DetalsAddGoal.class);
                i.putExtra("Find","goal");
                i.putExtra("goalData", disUserControl.getData() + "");
                context.startActivity(i);

            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i;
                    i = new Intent(context, AddGoal.class);
                    i.putExtra("InsertGoal", "1");
                    i.putExtra("goalData", disUserControl.getData() + " ");
                    i.putExtra("goalid", disUserControl.getGoalid());
                    i.putExtra("Maindep", Depout);
                    i.putExtra("outcheck",outcheck);
                    context.startActivity(i);


                } catch (Exception ex) {
                    Toast.makeText(context, "Exception" + ex, Toast.LENGTH_LONG).show();
                }

            }
        });
        holder.delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    RequestParams params = new RequestParams();
                    params.put("request", "deletgoal");
                    params.put("goalid", disUserControl.getGoalid());
                    Delet(params);
                } catch (Exception ex) {
                    Toast.makeText(context, "Exception" + ex, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){

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
                progressDialog.setMessage("جارى المسح...");
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.e("onSuccess", response + "");
                Log.e("onSuccess", response.length() + "");
                try {
                    JSONObject r = response.getJSONObject("respond");
                    if (r.getInt("message") == 1) {
                        Toast.makeText(context, "تم مسح الاهدف بنجاح", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(context, AddGoal.class);
                        i.putExtra("InsertGoal", "0");
                        context.startActivity(i);

                    } else {

                        Toast.makeText(context, "حاول مرة اخرى", Toast.LENGTH_LONG).show();

                    }

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