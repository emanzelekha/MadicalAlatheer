package com.example.ok.madicalalatheer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ok.madicalalatheer.AddGoal.AddGoal;
import com.example.ok.madicalalatheer.addIdea.activity_addIdea;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Button goal,idea,process,report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component();
        Click();
    }

    public void component(){
        idea=(Button)findViewById(R.id.idea);
        goal=(Button)findViewById(R.id.goal);
        process=(Button)findViewById(R.id.process);
        report=(Button)findViewById(R.id.report);
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
                startActivity(i);
                break;
            case R.id.goal:
                i = new Intent(MainActivity.this, AddGoal.class);
                startActivity(i);
                break;

            case R.id.process:
                i = new Intent(MainActivity.this, AddGoal.class);
                startActivity(i);
                break;
            case R.id.report:
                i = new Intent(MainActivity.this, AddGoal.class);
                startActivity(i);
                break;}
    }
}
