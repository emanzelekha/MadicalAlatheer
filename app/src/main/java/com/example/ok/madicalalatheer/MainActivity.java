package com.example.ok.madicalalatheer;

import android.app.Activity;
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
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.AddGoal.AddGoal;

import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.Main.Login;
import com.example.ok.madicalalatheer.Reportes.MainReport;
import com.example.ok.madicalalatheer.addIdea.activity_addIdea;
import com.example.ok.madicalalatheer.procedure.activity_procedure;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    View goal, idea, process, report, padding, activity_main;
    Typeface typeface;
    float width = 0, height = 0;
    double diagonalInches = 0;
    String id="",empId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component();
        Click();
        TypefaceUtil.overrideFonts(this, activity_main);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.heightPixels / metrics.ydpi;
        height = metrics.widthPixels / metrics.xdpi;
        diagonalInches = Math.sqrt(height * height + width * width);
        if (diagonalInches>=6.5) {
        padding.setPadding(70, 120, 70, 120);}

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
        SharedPreferences pref = getBaseContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
         id = pref.getString("member_type", "");
        empId = pref.getString("emp_id", "");
       if(!id.equals("1")){
         goal.setEnabled(false);
       }


    }

    public void component(){
        padding = findViewById(R.id.padding);
        idea=findViewById(R.id.idea);
        goal=findViewById(R.id.goal);
        process=findViewById(R.id.process);
        report=findViewById(R.id.report);
        activity_main = findViewById(R.id.activity_main);
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
                if(id.equals("1")){
                i = new Intent(MainActivity.this, AddGoal.class);
                i.putExtra("InsertGoal","0");
                startActivity(i);}else{
                    Toast.makeText(MainActivity.this,"لا يوجد لديك صلاحية",Toast.LENGTH_LONG).show();
                }
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




}




