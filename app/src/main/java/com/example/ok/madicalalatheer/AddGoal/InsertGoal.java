package com.example.ok.madicalalatheer.AddGoal;


import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.Fonts.MySpinnerAdapter;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;


import net.alhazmy13.hijridatepicker.HijriCalendarDialog;
import net.alhazmy13.hijridatepicker.HijriCalendarView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsertGoal extends Fragment implements View.OnClickListener, CheckBox.OnCheckedChangeListener, HijriCalendarView.OnDateSetListener, AdapterView.OnItemSelectedListener {
    Spinner s1, s2, s3;
    EditText from, to;
    TextView addgoal;
    String[] a1, a2 = null, a3, a4, a5, a6 = null;
    LinearLayout layout, Mangment;
    View v;
    List<String> cheak = new ArrayList<String>();
    String oName[] = null;
    HijriCalendarDialog.Builder text;
    TextInputLayout input1, input2, input3, input4;

    public InsertGoal() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_insert_goal, container, false);

        component();
        TypefaceUtil.overrideFonts(getContext(), v);
        addFont();
        click();
        a1 = new String[]{"*اختيار نوع الهدف", "عام", "خاص"};
        a5 = new String[]{"*اختر اهمية الهدف", "A", "B", "C"};
        a3 = new String[]{"*اختيار الادارة"};
        a4 = new String[]{"اختيار نوع الهدف", "عام", "خاص"};
        SpinnerDate(a1, a2, s1);
        SpinnerDate(a3, a4, s2);
        SpinnerDate(a5, a6, s3);
        oName = new String[]{"x", "2", "3", "4"};//array of cheackbox


        return v;
    }


    public void click() {
        from.setOnClickListener(this);
        to.setOnClickListener(this);
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.from2:
                Dialog();
                break;
            case R.id.to2:
                Dialog();
                break;

        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        for (int i = 0; i < oName.length; i++) {

            if ((compoundButton.getId() == 2000 + i) && b == true) {
                Toast.makeText(getContext(), oName[i], Toast.LENGTH_SHORT).show();
                cheak.add(oName[i]);
            } else if ((compoundButton.getId() == 2000 + i) && b == false) {
                cheak.remove(oName[i]);

            }

        }
        System.out.println(cheak + "999");
    }

    public void component() {
        layout = (LinearLayout) v.findViewById(R.id.cheackbox);
        Mangment = (LinearLayout) v.findViewById(R.id.Mangment);
        s1 = (Spinner) v.findViewById(R.id.s1);
        s2 = (Spinner) v.findViewById(R.id.s2);
        s3 = (Spinner) v.findViewById(R.id.s3);
        from = (EditText) v.findViewById(R.id.from2);
        to = (EditText) v.findViewById(R.id.to2);
       /* Drawable drawable1 = MrVector.inflate(getContext().getResources(), R.drawable.calendar);
        Drawable drawable2 = MrVector.inflate(getContext().getResources(), R.drawable.plus);
        from.setCompoundDrawablesWithIntrinsicBounds(drawable1, null, null, null);
        to.setCompoundDrawablesWithIntrinsicBounds(drawable1, null, null, null);*/
        addgoal = (TextView) v.findViewById(R.id.addgoal);
       // addgoal.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable2, null);
        addgoal.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi.ttf"));
        input1 = (TextInputLayout) v.findViewById(R.id.Textinput1);
        input2 = (TextInputLayout) v.findViewById(R.id.Textinput2);
        input3 = (TextInputLayout) v.findViewById(R.id.Textinput3);
        input4 = (TextInputLayout) v.findViewById(R.id.Textinput4);
    }

    public void addFont() {
        input1.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
        input2.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
        input3.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
        input4.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));


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
    public void onDateSet(int year, int month, int day) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("ar", "EG"));//formate
        String year1 = nf.format(year);
        String month1 = nf.format(month + 1);
        String day1 = nf.format(day);
    }

    public void Dialog() {
        text = new HijriCalendarDialog.Builder(getContext()).setUILanguage(HijriCalendarDialog.Language.Arabic).setOnDateSetListener(this).show();

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.s1:
                if (parent.getSelectedItem().toString().equals("خاص")) {
                    Mangment.setVisibility(View.VISIBLE);
                } else {
                    Mangment.setVisibility(View.GONE);
                    layout.setVisibility(View.GONE);
                    s2.setSelection(0);
                }

                break;
            case R.id.s2:
                if (parent.getSelectedItem().toString().equals("خاص") && oName != null) {
                    layout.setVisibility(View.VISIBLE);
                    CheckBox btn[] = new CheckBox[oName.length];
                    for (int x = 0; x < oName.length; x++) {
                        btn[x] = new CheckBox(getContext()); // initialize it
                        btn[x].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        btn[x].setText(oName[x]);
                        btn[x].setTextColor(getContext().getResources().getColor(R.color.text));
                        btn[x].setId(2000 + x);
                        btn[x].setOnCheckedChangeListener(this);
                        layout.addView(btn[x]);
                    }
                } else if (!(parent.getSelectedItem().toString().equals("خاص"))) {
                    layout.removeAllViews();
                    layout.setVisibility(View.GONE);
                }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
