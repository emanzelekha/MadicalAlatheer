package com.example.ok.madicalalatheer.AddGoal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.DateFormatein.HajreDate;
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

public class DetalsAddGoal extends AppCompatActivity {
    CardView goal, idea;
    TextView close, egoal1, egoal2, egoal3, egoal4, egoal5, egoal6, egoal7, egoal8, egoal9, egoal10, egoal11,eidea1,eidea2,eidea3
            ,eidea4,eidea5,eidea6,eidea7;
    JSONObject Data;
    List<String> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detals_add_goal);
        View v = findViewById(R.id.detailesaddgoal);
        TypefaceUtil.overrideFonts(this, v);

        component();
        Intent i = getIntent();
        HajreDate formate=new HajreDate();
        if (i.getStringExtra("Find").equals("goal")) {

            try {
                Data = new JSONObject(i.getStringExtra("goalData"));
                System.out.println(Data.getString("goal_title")+" "+Data + "hbyugft");
                egoal1.setText(Data.getString("goal_title"));
                if (Data.getString("goal_type").equals("1")) {
                    egoal4.setText("عام");
                } else {
                    egoal4.setText("خاص");
                }
                if(Data.getString("goal_important").equals("1")){
                    egoal5.setText("A");
                }else if(Data.getString("goal_important").equals("1")){
                    egoal5.setText("B");
                }else{
                    egoal5.setText("C");
                }
                if(i.getStringExtra("to").isEmpty()){
                    egoal3.setText("عام");
                }else {
                egoal3.setText(i.getStringExtra("to"));}



                egoal6.setText(formate.Dateout(Data.getString("goal_date_from")));
                egoal7.setText(formate.Dateout(Data.getString("goal_date_to")));
                egoal8.setText(Data.getString("goal_measurment"));
                egoal9.setText(Data.getString("goal_apprev"));
                egoal10.setText(Data.getString("goal_idea"));
                egoal11.setText(Data.getString("publisher"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                RequestParams params = new RequestParams();
                params.put("request", "displayprocedure");//هتغير الاسم حسب ما يقولك وهتبعتلة ال id من الshared refrance
                Load(params);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
            }
            goal.setVisibility(View.VISIBLE);
           /* try {
                RequestParams params = new RequestParams();
                params.put("request", "displaygoaldetails");
                Load(params);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
            }*/


        } else if (i.getStringExtra("Find").equals("idea")) {
            idea.setVisibility(View.VISIBLE);
            try {
                JSONObject Data = new JSONObject(i.getStringExtra("IdeaData"));
                eidea2.setText(Data.getString("goal_id"));
                eidea1.setText(Data.getString("idea_content"));
                eidea3.setText(Data.getString("publisher"));
                eidea4.setText(Data.getString("idea_emp_id"));
                eidea5.setText(Data.getString("idea_appre"));
                eidea6.setText(Data.getString("date"));
                eidea7.setText(Data.getString("publisher"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void component() {
        goal = (CardView) findViewById(R.id.goalApper);
        idea = (CardView) findViewById(R.id.ideaapper);
        egoal1 = (TextView) findViewById(R.id.egoal1);
        egoal2 = (TextView) findViewById(R.id.egoal2);
        egoal3 = (TextView) findViewById(R.id.egoal3);
        egoal4 = (TextView) findViewById(R.id.egoal4);
        egoal5 = (TextView) findViewById(R.id.egoal5);
        egoal6 = (TextView) findViewById(R.id.egoal6);
        egoal7 = (TextView) findViewById(R.id.egoal7);
        egoal8 = (TextView) findViewById(R.id.egoal8);
        egoal9 = (TextView) findViewById(R.id.egoal9);
        egoal10 = (TextView) findViewById(R.id.egoal10);
        egoal11 = (TextView) findViewById(R.id.egoal11);
        eidea1 = (TextView) findViewById(R.id.eidea1);
        eidea2 = (TextView) findViewById(R.id.eidea2);
        eidea3 = (TextView) findViewById(R.id.eidea3);
        eidea4 = (TextView) findViewById(R.id.eidea4);
        eidea5 = (TextView) findViewById(R.id.eidea5);
        eidea6 = (TextView) findViewById(R.id.eidea6);
        eidea7 = (TextView) findViewById(R.id.eidea7);

    }


    public void Load(RequestParams params) throws JSONException {

        AsyncHttpClient.post("", params, new JsonHttpResponseHandler() {
            ProgressDialog progressDialog;

            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(DetalsAddGoal.this);
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

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject out = response.getJSONObject(i);
                        if (out.getString("deleted").equals("1")) {

                            if (out.getString("goal_id").equals(Data.getString("id"))) {
                                if (t.equals("")) {
                                    t += out.getString("pro_title");
                                } else {
                                    t += "\n" + out.getString("pro_title");
                                }
                            } else {
                                break;
                            }
                        }
                    }

                    egoal2.setText(t);      /* if (t.equals("")) {

                                t = out.getString("goal_title");

                               // items.add(out.getString("pro_title"));
                            } else if (!out.getString("goal_title").equals(t)) {

                                t = out.getString("goal_title");
                                items.add(out.getString("pro_title"));
                               // items.add(new PeopleAdapter.PeopleListItem(m, t, out.getString("date_approved")));
                            } else if (out.getString("goal_title").equals(t)) {
                                //items.add(out.getString("pro_title"));
                            }

                        }

                    }*/
                    System.out.println(items.size() + "gjhhtfhftg");

                } catch (Exception ex) {

                    Toast.makeText(getApplicationContext(), "اشاره النت ضغيفه", Toast.LENGTH_LONG).show();

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
