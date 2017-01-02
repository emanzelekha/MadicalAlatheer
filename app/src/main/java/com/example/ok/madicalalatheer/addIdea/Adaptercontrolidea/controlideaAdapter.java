package com.example.ok.madicalalatheer.addIdea.Adaptercontrolidea;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.addIdea.activity_addIdea;
import com.example.ok.madicalalatheer.addIdea.controlidea.cotroldisidea;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

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

        final cotroldisidea disUserControl = displayList.get(position);
        holder.Code.setText(disUserControl.getSerial1());
        holder.goal.setText(disUserControl.getGoal());
        holder.suggest.setText(disUserControl.getSuggest());
        holder.serial.setText((position + 1) + "");
        holder.swt.setChecked(disUserControl.isActive());
        holder.job.setText(disUserControl.getJob());
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(context, DetalsAddGoal.class);
                i.putExtra("Find","idea");
                i.putExtra("IdeaData", disUserControl.getData() + "");
                context.startActivity(i);

            }
        });


            holder.edit.setVisibility(View.VISIBLE);
            holder.delet.setVisibility(View.VISIBLE);
         holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i;
                    i = new Intent(context, activity_addIdea.class);
                    i.putExtra("InsertIdea", "1");
                    i.putExtra("IdeaData", disUserControl.getData() + " ");
                    i.putExtra("Ideaid", disUserControl.getIdeaid());
                   // i.putExtra("Maindep", Depout);
                   // i.putExtra("outcheck",outcheck);
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
                    params.put("request", "deletIdea");
                    params.put("ideaid", disUserControl.getIdeaid());
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
        switch (view.getId()) {

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Code, goal, suggest, job, serial;
        View edit,delet;
        View  details;
        public SwitchCompat swt;

        public MyViewHolder(View view) {
            super(view);
            serial = (TextView) view.findViewById(R.id.serial);
            Code = (TextView) view.findViewById(R.id.Code);
            goal = (TextView) view.findViewById(R.id.goal);
            suggest = (TextView) view.findViewById(R.id.to);
            swt = (SwitchCompat) view.findViewById(R.id.Active);
            job = (TextView) view.findViewById(R.id.job);
            details = view.findViewById(R.id.ideadetails);
            edit=view.findViewById(R.id.edit);
            delet=view.findViewById(R.id.delet);
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
                        Intent i = new Intent(context, activity_addIdea.class);
                        i.putExtra("InsertIdea", "0");
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