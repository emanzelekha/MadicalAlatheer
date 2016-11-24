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

import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetalsAddGoal extends AppCompatActivity {
    CardView goal, idea;
    TextView close, egoal1, egoal2, egoal3, egoal4, egoal5, egoal6, egoal7, egoal8, egoal9, egoal10, egoal11,eidea1,eidea2,eidea3
            ,eidea4,eidea5,eidea6,eidea7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detals_add_goal);
        View v = findViewById(R.id.detailesaddgoal);
        TypefaceUtil.overrideFonts(this, v);
        component();
        Intent i = getIntent();
        if (i.getStringExtra("Find").equals("goal")) {
            goal.setVisibility(View.VISIBLE);
            try {
                RequestParams params = new RequestParams();
                params.put("request", "displaygoaldetails");
                Load(params);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
            }


        } else if (i.getStringExtra("Find").equals("idea")) {
            idea.setVisibility(View.VISIBLE);
            try {
                RequestParams params = new RequestParams();
                params.put("request", "displayideadetails");
                Load(params);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
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
                progressDialog = new ProgressDialog(getBaseContext());
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

                    Toast.makeText(getApplicationContext(), "اشاره النت ضغيفه", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(getApplicationContext(), "onFailure", Toast.LENGTH_LONG).show();
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
