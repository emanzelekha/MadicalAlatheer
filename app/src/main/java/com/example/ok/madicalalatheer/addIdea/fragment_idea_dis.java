package com.example.ok.madicalalatheer.addIdea;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.addIdea.Adaptercontrolidea.controlideaAdapter;
import com.example.ok.madicalalatheer.addIdea.controlidea.cotroldisidea;
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
 * Created by ahmed on 11/7/2016.
 */

public class fragment_idea_dis extends Fragment {
String []code,from,content,job,Ideaid;
    Boolean []active,buttons;
    JSONObject Data[];
    TextView found;
    View v;
    private List<cotroldisidea> disList = new ArrayList<>();
    private RecyclerView recyclerView;
    private controlideaAdapter mAdapter;

    public fragment_idea_dis() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_display_goal, container, false);
        TypefaceUtil.overrideFonts(getContext(), v);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_viewGoal);
        mAdapter = new controlideaAdapter(disList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        try {
            RequestParams params = new RequestParams();
            params.put("request","displayidea");

            Load(params);
        } catch (Exception ex) {
            Toast.makeText(getActivity().getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
        }
      //  prepareMovieData();
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
              //  out = response;
                Log.e("onSuccess", response + "");
                Log.e("onSuccess", response.length() + "");
                try {

                    if(response.length()==0){
                        found=(TextView)v.findViewById(R.id.found);
                        found.setVisibility(View.VISIBLE);
                        found.setText("لا يوجد افكار");
                    }
                    int m = 0;
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        if (data.getString("deleted").equals("1")) {
                            m++;
                        }
                    }
                    Data = new JSONObject[m];
                    Ideaid = new String[m];
                    buttons = new Boolean[m];
                    active = new Boolean[m];
                    code = new String[m];
                    from = new String[m];
                    content = new String[m];
                    job = new String[response.length()];
                    int n = 0;
                    for(int i=0;i<response.length();i++){
                        JSONObject data=response.getJSONObject(i);
                        if (data.getString("deleted").equals("1")) {
                            Data[n] = data;
                        System.out.println(data);
                            Ideaid[n] = data.getString("id");
                            content[n] = data.getString("idea_content");
                            code[n] = data.getString("idea_code");
                            from[n] = data.getString("idea_appre");
                            if (data.getString("suspend").equals("0")) {
                                active[n] = false;
                        }else {
                                active[n] = true;
                        }
                            if (data.getString("suspend").equals("0")) {
                                buttons[n] = false;
                        }else {
                                buttons[n] = true;
                        }
                            n++;
                        }
                    }
                  //   goalid[1]="9";  goals[1]="hjgjg" ; code[1]="0" ;to[1]="7" ;active[1]=true; buttons[1]=false;
                 /*goalid=new String[]{"4","5"};
                    goals=new String[]{"4","9"};
                    code=new String[]{"4","yg"};
                    to=new String[]{"4","fgtf"};
                    active=new Boolean[]{true,false};
                    buttons=new Boolean[]{true,false};*/
                    for (int x = 0; x < content.length; x++) {

                        cotroldisidea disUserControl = new cotroldisidea(Data[x],Ideaid[x],code[x], content[x], from[x], job[x], active[x], buttons[x]);
                        disList.add(disUserControl);
                        mAdapter.notifyDataSetChanged();}
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
