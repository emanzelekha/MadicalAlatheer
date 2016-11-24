package com.example.ok.madicalalatheer.AddGoal;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ok.madicalalatheer.AddGoal.AddGoalAdapter.Adapter;
import com.example.ok.madicalalatheer.AddGoal.control.ControlAddGoal;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayGoal extends Fragment {

    private List<ControlAddGoal> disList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter mAdapter;
    Boolean[] buttons, active;
    String[] goals, code, to,goalid;
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
        try {
            RequestParams params = new RequestParams();
            params.put("request","displaygoal");

            Load(params);
        } catch (Exception ex) {
            Toast.makeText(getActivity().getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
        }
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
                Log.e("onSuccess", response.length() + "");
                try {
                    goalid = new String[response.length()];
                    buttons = new Boolean[response.length()];
                    active = new Boolean[response.length()];
                    code = new String[response.length()];
                    to = new String[response.length()];
                    goals = new String[response.length()];
                    for(int i=0;i<response.length();i++){
                       JSONObject data=response.getJSONObject(i);
                        System.out.println(data);
                        goalid[i]=data.getString("id");
                        goals[i]=data.getString("id");
                        code[i]=data.getString("goal_code");
                        to[i]=data.getString("goal_code");
                                if(data.getString("suspend").equals(0)){
                                    active[i]=false;
                                }else {
                                    active[i]=true;
                                }
                        if(data.getString("suspend").equals(0)){
                            buttons[i]=false;
                        }else {
                            buttons[i]=true;
                        }

                    }

                  for (int i = 0; i < goals.length; i++) {
                   ControlAddGoal disUserControl = new ControlAddGoal(code[i], goals[i], to[i], active[i], buttons[i]);
                        disList.add(disUserControl);
                        mAdapter.notifyDataSetChanged();
                       }
                } catch (Exception ex) {

                    Toast.makeText(getActivity().getApplicationContext(), "اشاره النت ضغيفه", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(getActivity().getApplicationContext(), "onFailure", Toast.LENGTH_LONG).show();
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
