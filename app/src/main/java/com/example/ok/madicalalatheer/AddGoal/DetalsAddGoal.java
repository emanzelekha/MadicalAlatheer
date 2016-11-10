package com.example.ok.madicalalatheer.AddGoal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;

public class DetalsAddGoal extends AppCompatActivity {
    CardView goal, idea;
    TextView close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detals_add_goal);
        View v = findViewById(R.id.detailesaddgoal);
        TypefaceUtil.overrideFonts(this, v);

        goal = (CardView) findViewById(R.id.goalApper);
        idea = (CardView) findViewById(R.id.ideaapper);
        Intent i = getIntent();
        if (i.getStringExtra("Find").equals("goal")) {
            goal.setVisibility(View.VISIBLE);
        } else if (i.getStringExtra("Find").equals("idea")) {
            idea.setVisibility(View.VISIBLE);
        }
        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
