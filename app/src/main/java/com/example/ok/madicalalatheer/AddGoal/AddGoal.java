package com.example.ok.madicalalatheer.AddGoal;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;


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
        t.setText(Html.fromHtml("<strong>عقـــــاري </strong>"));
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

        main.setTypeface(typeface);
        main1.setTypeface(typeface);
        main2.setTypeface(typeface);
        main3.setTypeface(typeface);
        main4.setTypeface(typeface);

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
        viewPager.setAdapter(adapter);
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
