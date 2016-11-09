package com.example.ok.madicalalatheer.AddGoal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;

public class DetalsAddGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detals_add_goal);
        View v=findViewById(R.id.detailesaddgoal);
        TypefaceUtil.overrideFonts(this,v);
    }
}
