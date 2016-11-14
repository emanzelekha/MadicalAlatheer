package com.example.ok.madicalalatheer.procedure;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.ok.madicalalatheer.Fonts.MySpinnerAdapter;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.procedure.Modle.Controlprocedure;
import com.example.ok.madicalalatheer.procedure.ProcedureAdapter.AdapterProceduer;

import net.alhazmy13.hijridatepicker.HijriCalendarView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsertProcedure extends Fragment implements View.OnClickListener, HijriCalendarView.OnDateSetListener, AdapterView.OnItemSelectedListener {

    String[] a1, a2, a3, a4, procdure, code;
    View v;
    Spinner s1, s2;
    private List<Controlprocedure> disList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterProceduer mAdapter;

    public InsertProcedure() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_insert_procedure, container, false);
        TypefaceUtil.overrideFonts(getContext(), v);
        commponent();
        Click();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_procedure);
        a1 = new String[]{"*اختر عدد الاجراءات"};

        a2 = new String[]{"1", "2", "3", "4", "5"};
        a3 = new String[]{"*قم باختيار الهدف"};

        a4 = new String[]{"1", "2", "3", "4", "5"};
        SpinnerDate(a1, a2, s2);
        SpinnerDate(a3, a4, s1);
        mAdapter = new AdapterProceduer(disList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        return v;
    }

    public void commponent() {
        s1 = (Spinner) v.findViewById(R.id.s1P);
        s2 = (Spinner) v.findViewById(R.id.s2P);
    }

    public void Click() {
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
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

    private void prepareMovieData(String code[], String pro[]) {

        mAdapter.remove();
        for (int x = 0; x < code.length; x++) {
            Controlprocedure disUserControl = new Controlprocedure(code[x], pro[x]);
            disList.add(disUserControl);
            mAdapter.notifyDataSetChanged();


        }
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDateSet(int year, int month, int day) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.s2P:
                System.out.println(parent.getSelectedItem().toString() + "lll");
                procdure = new String[parent.getSelectedItemPosition()];
                code = new String[parent.getSelectedItemPosition()];
                NumberFormat nf = NumberFormat.getInstance(new Locale("ar", "EG"));//formate
                for (int i = 0; i < parent.getSelectedItemPosition(); i++) {
                    procdure[i] = "الاجراء " + String.valueOf(nf.format(i + 1));
                    code[i] = String.valueOf(nf.format(i + 1));
                }

                prepareMovieData(code, procdure);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
