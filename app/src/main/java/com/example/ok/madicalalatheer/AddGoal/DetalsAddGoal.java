package com.example.ok.madicalalatheer.AddGoal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;

public class DetalsAddGoal extends AppCompatActivity {
CardView goal,idea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detals_add_goal);
        View v=findViewById(R.id.detailesaddgoal);
        TypefaceUtil.overrideFonts(this,v);
        Intent i=getIntent();
        goal=(CardView)findViewById(R.id.goalApper);
        idea=(CardView) findViewById(R.id.ideaapper);
       if( i.getStringExtra("Find").equals("goal")){
           goal.setVisibility(View.VISIBLE);
       }else if(i.getStringExtra("Find").equals("idea")){
           idea.setVisibility(View.VISIBLE);
       }
    }
}
