package com.example.ok.madicalalatheer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.AddGoal.AddGoal;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.Main.Login;
import com.example.ok.madicalalatheer.Reportes.MainReport;
import com.example.ok.madicalalatheer.addIdea.activity_addIdea;
import com.example.ok.madicalalatheer.procedure.activity_procedure;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
View goal,idea,process,report,main;
    Typeface typeface;
    String[] MainDep, MainDepId, SubDep, SubDepId;
    String MainDep1 = "", MainDepId1 = "", SubDep1 = "", SubDepId1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/DroidKufi-Bold.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView t = (TextView) toolbar.findViewById(R.id.toolbar_title);
        // t.setTypeface(button);
        t.setText(Html.fromHtml("<strong>المركز الطبى </strong>"));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        TextView main = (TextView) navigationView.findViewById(R.id.main);
        TextView main1 = (TextView) navigationView.findViewById(R.id.main1);
        TextView main2 = (TextView) navigationView.findViewById(R.id.main2);
        TextView main3 = (TextView) navigationView.findViewById(R.id.main3);
        TextView main4 = (TextView) navigationView.findViewById(R.id.main4);
        TextView   main0 = (TextView) navigationView.findViewById(R.id.main0);
        main0.setTypeface(typeface);
        main0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        main.setTypeface(typeface);
        main1.setTypeface(typeface);
        main2.setTypeface(typeface);
        main3.setTypeface(typeface);
        main4.setTypeface(typeface);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, activity_addIdea.class);
                i.putExtra("InsertIdea", "0");
                startActivity(i);
            }
        });
        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, activity_procedure.class);
                i.putExtra("Insertprocedure", "0");
                startActivity(i);
            }
        });
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddGoal.class);
                i.putExtra("InsertGoal","0");
                startActivity(i);
            }
        });
        main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainReport.class);
                startActivity(i);
            }
        });

        main4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("Data", Context.MODE_PRIVATE);
                //
                // SharedPreferences.Editor editor = sharedPref.edit();
                sharedPref.edit().remove("UserId").commit();
                // editor.commit();e
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });
        component();
        Click();
        TypefaceUtil.overrideFonts(getBaseContext(), main);

        try {
            RequestParams params = new RequestParams();
            params.put("request", "goalsreport");

            Load(params);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
        }

    }

    public void component(){
        main=findViewById(R.id.activity_main);
        idea=findViewById(R.id.idea);
        goal=findViewById(R.id.goal);
        process=findViewById(R.id.process);
        report=findViewById(R.id.report);
    }
    public void Click(){
        idea.setOnClickListener(this);
        goal.setOnClickListener(this);
        process.setOnClickListener(this);
        report.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent i;
        switch (view.getId()) {

            case R.id.idea:
                i = new Intent(MainActivity.this, activity_addIdea.class);
                i.putExtra("InsertIdea","0");
                startActivity(i);
                break;
            case R.id.goal:
                i = new Intent(MainActivity.this, AddGoal.class);
                i.putExtra("InsertGoal","0");
                startActivity(i);
                break;

            case R.id.process:
                i = new Intent(MainActivity.this, activity_procedure.class);
                i.putExtra("Insertprocedure", "0");
                startActivity(i);
                break;
            case R.id.report:
                i = new Intent(MainActivity.this, MainReport.class);
                startActivity(i);
                break;}
    }


    public void Load(RequestParams params) throws JSONException {

        AsyncHttpClient.post("", params, new JsonHttpResponseHandler() {
            ProgressDialog progressDialog;

            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("ÌÇÑì ÇáÈÍË...");
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.e("onSuccess", response + "");
                Log.e("onSuccess", response.length() + "");
                try {
                    MainDep = new String[response.length() - 1];
                    MainDepId = new String[response.length() - 1];
                    SubDep = new String[response.length() - 1];
                    SubDepId = new String[response.length() - 1];
                    for (int i = 0; i < response.length() - 1; i++) {
                        JSONObject json_data = response.getJSONObject(i + "");
                        MainDep[i] = json_data.getString("main_dep_name");
                        MainDepId[i] = json_data.getString("id");
                        MainDepId1 += json_data.getString("id") + ",";
                        MainDep1 += json_data.getString("main_dep_name") + ",";

                    }
                    JSONArray subdebartement = response.getJSONArray("supdepartement");
                    for (int i = 0; i < response.length() - 1; i++) {
                        String set = "";
                        String set1 = "";
                        for (int j = 0; j < subdebartement.length(); j++) {
                            JSONObject json_data1 = subdebartement.getJSONObject(j);
                            if (MainDepId[i].equals(json_data1.getString("main_dep_f_id"))) {
                                set += json_data1.getString("id") + ",";
                                set1 += json_data1.getString("sub_dep_name") + ",";
                            }
                        }
                        SubDepId1 += set + "oo";
                        SubDep1 += set1 + "oo";
                    }
                //  System.out.println(SubDep1 + "                               " + SubDepId1);
                    SharedPreferences sharedPref = getBaseContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("MainDep", MainDep1);
                    editor.putString("MainDepId", MainDepId1);
                    editor.putString("SubDep", SubDep1);
                    editor.putString("SubDepId", SubDepId1);
                    editor.commit();

                } catch (Exception ex) {

                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

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




