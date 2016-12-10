package com.example.ok.madicalalatheer.AddGoal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.Main.Login;
import com.example.ok.madicalalatheer.MainActivity;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.Reportes.MainReport;
import com.example.ok.madicalalatheer.addIdea.activity_addIdea;
import com.example.ok.madicalalatheer.procedure.activity_procedure;

import java.util.ArrayList;
import java.util.List;

public class AddGoal extends AppCompatActivity {
    View v;
    Typeface typeface;
    public int[] tabIcons = {
            R.drawable.show, R.drawable.add
    };
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView main0,main, main1, main2, main3, main4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        v=findViewById(R.id.activity_add_goal);
        viewPager = (ViewPager) findViewById(R.id.viewpagerAddDepartment);
        setupViewPager(viewPager);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/DroidKufi-Bold.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView t = (TextView) toolbar.findViewById(R.id.toolbar_title);
        // t.setTypeface(button);
        t.setText(Html.fromHtml("<strong>الاهداف </strong>"));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        main0 = (TextView) navigationView.findViewById(R.id.main0);
        main0.setTypeface(typeface);
        main0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddGoal.this, MainActivity.class);
                i.putExtra("InsertGoal","0");
                startActivity(i);
            }
        });
        main = (TextView) navigationView.findViewById(R.id.main);
        main1 = (TextView) navigationView.findViewById(R.id.main1);
        main2 = (TextView) navigationView.findViewById(R.id.main2);
        main3 = (TextView) navigationView.findViewById(R.id.main3);
        main4 = (TextView) navigationView.findViewById(R.id.main4);

        main.setTypeface(typeface);
        main1.setTypeface(typeface);
        main2.setTypeface(typeface);
        main3.setTypeface(typeface);
        main4.setTypeface(typeface);


        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddGoal.this, activity_addIdea.class);
                i.putExtra("InsertIdea", "0");
                startActivity(i);
            }
        });
        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddGoal.this, activity_procedure.class);
                i.putExtra("Insertprocedure", "0");
                startActivity(i);
            }
        });
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddGoal.this, AddGoal.class);
                i.putExtra("InsertGoal","0");
                startActivity(i);
            }
        });
        main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddGoal.this, MainReport.class);
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
                Intent i = new Intent(AddGoal.this, Login.class);
                startActivity(i);
            }
        });


        tabLayout = (TabLayout) findViewById(R.id.tabsAddDepartment);
        TypefaceUtil.overrideFonts(getBaseContext(), v);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

      /*  for(int i=0;i<2;i++){  ///Font for tab but delet icons
            TextView tv = new TextView(this);
            tv.setText(tabLayout.getTabAt(i).getText());
            tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/DroidKufi.ttf"));
            tabLayout.getTabAt(i).setText((CharSequence) tv);}
*/
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);


    }

    private void setupViewPager(ViewPager viewPager) {
        AddGoal.ViewPagerAdapter adapter = new AddGoal.ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new DisplayGoal(), "قائمة البيانات");
        adapter.addFrag(new InsertGoal(), "اضافة جديد");
        Intent i = getIntent();

        viewPager.setAdapter(adapter);
        if (i.getStringExtra("InsertGoal").equals("1")) {
            viewPager.setCurrentItem(1);
        } else {
            viewPager.setCurrentItem(0);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
