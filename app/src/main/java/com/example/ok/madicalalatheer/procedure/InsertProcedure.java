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
import android.widget.CheckBox;
import android.widget.LinearLayout;
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
    View v,v2,cardviewd,cardviewd2,cardviewd3,cardviewd4,cardviewd5;
    LinearLayout Scrol;
    View[]valesview=new View[]{cardviewd,cardviewd2,cardviewd3,cardviewd4,cardviewd5};
    int vales[]=new int[]{R.id.cardviewd,R.id.cardviewd1,R.id.cardviewd2,R.id.cardviewd3,R.id.cardviewd4};
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
        v2=inflater.inflate(R.layout.procedurerow, container, false);
        TypefaceUtil.overrideFonts(getContext(), v);
        commponent();
        Click();
       // recyclerView = (RecyclerView) v.findViewById(R.id.recycler_procedure);
        a1 = new String[]{"*اختر عدد الاجراءات"};

        a2 = new String[]{"1", "2", "3", "4", "5"};
        a3 = new String[]{"*قم باختيار الهدف"};

        a4 = new String[]{"1", "2", "3", "4", "5"};
        SpinnerDate(a1, a2, s2);
        SpinnerDate(a3, a4, s1);



        return v;
    }

    public void commponent() {
        s1 = (Spinner) v.findViewById(R.id.s1P);
        s2 = (Spinner) v.findViewById(R.id.s2P);
        Scrol=(LinearLayout)v.findViewById(R.id.Scrol);
        for(int i=0;i<5;i++){
            valesview[i]=v.findViewById(vales[i]);
        }
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

    /*private void prepareMovieData(String code[], String pro[]) {

        mAdapter.remove();
        for (int x = 0; x < code.length; x++) {
            Controlprocedure disUserControl = new Controlprocedure(code[x], pro[x]);
            disList.add(disUserControl);
            mAdapter.notifyDataSetChanged();


        }
    }
*/

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
             //   LinearLayout l[]=new LinearLayout[parent.getSelectedItemPosition()];

                for (int i = 0; i < parent.getSelectedItemPosition(); i++) {
                    valesview[i].setVisibility(View.VISIBLE);
                   /* l[i] = new LinearLayout(getContext()); // initialize it
                    l[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                   // ViewGroup layout = (ViewGroup) l(i);
                    l[i].addView(v2);
*/
                    procdure[i] = "الاجراء " + String.valueOf(nf.format(i + 1));
                    code[i] = String.valueOf(nf.format(i + 1));
                }

//                prepareMovieData(code, procdure);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
