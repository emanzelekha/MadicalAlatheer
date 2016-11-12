package com.example.ok.madicalalatheer.addIdea;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.Fonts.MySpinnerAdapter;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ahmed on 11/6/2016.
 */

public class insert_idea extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner spinnermanges;
    Spinner spinneremployer;
    Spinner sp_depart;
    LinearLayout layout1, layout2;
    CardView Main;
    TextInputLayout input1, input2;
    View v;

    public insert_idea() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String choose[] = {"اختر الهدف"};
        String choosetarget[] = {"الهدف 1", "الهدف 2"};
        String choosemang[] = {"اختر الادارة التابع لها"};
        String manages[] = {"الادراة 1", "الادراة 2", "الادراة 3"};
        String chooseemployer[] = {"اختر الموظف"};
        String employer[] = {"الموظف 1", "الموظف 2"};
        String choose_depart[] = {"اختر القسم"};
        String depart[] = {"القسم 2", "القسم 1"};

        v = inflater.inflate(R.layout.insert_idea, container, false);
        TypefaceUtil.overrideFonts(getContext(), v);
        commponent();
        addFont();
        Spinner spinner1 = (Spinner) v.findViewById(R.id.sp_choosetarget);
        spinnermanges = (Spinner) v.findViewById(R.id.sp_managment);
        spinneremployer = (Spinner) v.findViewById(R.id.sp_employer);
        sp_depart = (Spinner) v.findViewById(R.id.sp_depart);
        SpinnerDate(choose, choosetarget, spinner1);
        SpinnerDate(choosemang, manages, spinnermanges);
        SpinnerDate(chooseemployer, employer, spinneremployer);
        SpinnerDate(choose_depart, depart, sp_depart);
        spinnermanges.setOnItemSelectedListener(this);
        sp_depart.setOnItemSelectedListener(this);
        layout1 = (LinearLayout) v.findViewById(R.id.layout1);
        layout2 = (LinearLayout) v.findViewById(R.id.layout2);
        TextView btn_add = (TextView) v.findViewById(R.id.add_btn);
        btn_add.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi.ttf"));
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "add", Toast.LENGTH_SHORT).show();
            }
        });
        Main = (CardView) v.findViewById(R.id.InsertIdea);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated
        Main.setMinimumHeight(height - 310);

        return v;
    }

    public void SpinnerDate(String[] array1, String[] array2, Spinner s) {
        String[] arraySpinner = null;
        if (array2 != null) {
            List<String> list = new ArrayList<String>(Arrays.asList(array1));
            list.addAll(Arrays.asList(array2));
            Object[] c = list.toArray();
            arraySpinner = Arrays.copyOf(c, c.length, String[].class);
        } else {
            arraySpinner = Arrays.copyOf(array1, array1.length);
        }

        MySpinnerAdapter adapter = new MySpinnerAdapter(
                getContext(),
                R.layout.spinrtitem,
                Arrays.asList(arraySpinner)
        );
        adapter.setDropDownViewResource(R.layout.downspinner);
        s.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {
            case R.id.sp_managment:
                int item = (int) adapterView.getItemIdAtPosition(i);
                if (item != 0) {
                    layout1.setVisibility(View.VISIBLE);
                } else {
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                }
                break;

            case R.id.sp_depart:
                int item1 = (int) adapterView.getItemIdAtPosition(i);
                if (item1 != 0) {
                    layout2.setVisibility(View.VISIBLE);
                } else {
                    layout2.setVisibility(View.GONE);
                }
                break;
        }
    }

    public void commponent() {
        input1 = (TextInputLayout) v.findViewById(R.id.inputText1);
        input2 = (TextInputLayout) v.findViewById(R.id.inputText2);
    }
    public void addFont() {
        input1.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
        input2.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
