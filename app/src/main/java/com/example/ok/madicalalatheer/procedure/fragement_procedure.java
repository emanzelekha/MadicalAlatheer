package com.example.ok.madicalalatheer.procedure;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ok.madicalalatheer.DateFormatein.HajreDate;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.innodroid.expandablerecycler.ExpandableRecyclerAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ahmed on 11/9/2016.
 */

public class fragement_procedure extends Fragment {
    RecyclerView recycler;
    PeopleAdapter adapter;
    View v;
    String id="",empId="";
    List<PeopleAdapter.PeopleListItem> items = new ArrayList<>();
    HajreDate dateout=new HajreDate();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_procedure, container, false);
        SharedPreferences pref = getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
        id = pref.getString("member_type", "");
        empId = pref.getString("emp_id", "");
        System.out.println(dateout.toJulianDate()+"   jgbhjgfghfghf");
        TypefaceUtil.overrideFonts(getContext(), v);
        recycler = (RecyclerView) v.findViewById(R.id.main_recycler);
        try {
            RequestParams params = new RequestParams();
            params.put("request", "displayprocedure");//هتغير الاسم حسب ما يقولك وهتبعتلة ال id من الshared refrance

            Load(params);
        } catch (Exception ex) {
            Toast.makeText(getActivity().getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
        }
        adapter = new PeopleAdapter(getContext());

        adapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        return v;
    }



    public void Load(RequestParams params) throws JSONException {

        AsyncHttpClient.post("", params, new JsonHttpResponseHandler() {
            ProgressDialog progressDialog;

            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setCancelable(false);
                progressDialog.setMessage("جارى البحث...");
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                Log.e("onSuccess", response + "");
                Log.e("onSuccess", response.length() + "dghjkdhjhj");
                try {
                    String t = "";
                    int m=0;
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject out = response.getJSONObject(i);
                        if (out.getString("deleted").equals("1")) {
                        if(empId.equals("0")||empId.equals("")) {

                                if (t.equals("")) {
                                    m++;
                                    t = out.getString("goal_title");
                                    items.add(new PeopleAdapter.PeopleListItem(m, t,out.getString("date_approved")));
                                    items.add(new PeopleAdapter.PeopleListItem(out.getString("pro_title"),  dateout.Dateout( out.getString("pro_end_date")), out,out.getString("approved")));
                                } else if (!out.getString("goal_title").equals(t)) {
                                    m++;
                                    t = out.getString("goal_title");
                                    items.add(new PeopleAdapter.PeopleListItem(m, t,out.getString("date_approved")));
                                } else if (out.getString("goal_title").equals(t)) {
                                    items.add(new PeopleAdapter.PeopleListItem(out.getString("pro_title"),  dateout.Dateout( out.getString("pro_end_date")), out,out.getString("approved")));
                                }
                            }else if(empId.equals(out.getString("employee_id"))){


                            if (t.equals("")) {
                                m++;
                                t = out.getString("goal_title");
                                items.add(new PeopleAdapter.PeopleListItem(m, t,out.getString("date_approved")));
                                items.add(new PeopleAdapter.PeopleListItem(out.getString("pro_title"), dateout.Dateout( out.getString("pro_end_date")), out,out.getString("approved")));
                            } else if (!out.getString("goal_title").equals(t)) {
                                m++;
                                t = out.getString("goal_title");
                                items.add(new PeopleAdapter.PeopleListItem(m, t,out.getString("date_approved")));
                            } else if (out.getString("goal_title").equals(t)) {
                                items.add(new PeopleAdapter.PeopleListItem(out.getString("pro_title"), dateout.Dateout( out.getString("pro_end_date")), out,out.getString("approved")));
                            }

                        }

                        }





                    }

                    adapter.setItems(items);
                } catch (Exception ex) {

                    Toast.makeText(getActivity().getApplicationContext(), "اشاره النت ضغيفه", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
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