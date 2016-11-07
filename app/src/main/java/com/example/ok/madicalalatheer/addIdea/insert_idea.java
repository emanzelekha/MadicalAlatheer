package com.example.ok.madicalalatheer.addIdea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

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
    LinearLayout layout1,layout2;

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

        View v = inflater.inflate(R.layout.insert_idea, container, false);
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
        layout1=(LinearLayout)v.findViewById(R.id.layout1);
        layout2=(LinearLayout)v.findViewById(R.id.layout2);
        Button btn_add=(Button) v.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"add",Toast.LENGTH_SHORT).show();
            }
        });
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinrtitem, arraySpinner);
        s.setAdapter(adapter);
        adapter.setDropDownViewResource(R.layout.downspinner);
// Apply the adapter to the spinner
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





    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
