package com.example.ok.madicalalatheer.AddGoal;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
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
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import net.alhazmy13.hijridatepicker.HijriCalendarDialog;
import net.alhazmy13.hijridatepicker.HijriCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsertGoal extends Fragment implements View.OnClickListener, CheckBox.OnCheckedChangeListener, HijriCalendarView.OnDateSetListener, AdapterView.OnItemSelectedListener {
    Spinner s1, s2, s3;
    int ClickedDate;
    TextView codenumber;
    EditText from, to, goalbody, goallevel, goalidea, goalideaaround;
    TextView addgoal;
    String[] a1, a2 = null, a3, a4, a5, a6 = null, id, cheackName;
    LinearLayout layout, Mangment;
    View v;
    String Date="";
    List<String> cheak = new ArrayList<String>();
    String oName[] = null;
    HijriCalendarDialog.Builder text;
    TextInputLayout input1, input2, input3, input4, input12, input11;

    public InsertGoal() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_insert_goal, container, false);
        try {
            RequestParams params = new RequestParams();
            params.put("request","addgoal");

            Load(params);
        } catch (Exception ex) {
            Toast.makeText(getActivity().getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
        }
        component();
        TypefaceUtil.overrideFonts(getContext(), v);
        addFont();
        click();
        a1 = new String[]{"*اختيار نوع الهدف", "عام", "خاص"};
        a5 = new String[]{"*اختر اهمية الهدف", "A", "B", "C"};
        a3 = new String[]{"*اختيار الادارة"};
        SpinnerDate(a1, a2, s1);
        SpinnerDate(a5, a6, s3);
        return v;
    }

    public boolean validate() {
        boolean out = true;
        if (s1.getSelectedItemPosition() == 0) {
            ((TextView) s1.getChildAt(0)).setError(".");
            out = false;
        }
        if (s2.isShown()) {
            if (s2.getSelectedItemPosition() == 0) {
                ((TextView) s2.getChildAt(0)).setError(".");
                out = false;
            }
        }

        if (s3.getSelectedItemPosition() == 0) {
            ((TextView) s3.getChildAt(0)).setError(".");
            out = false;
        }
        if (TextUtils.isEmpty(goalbody.getText().toString())) {
            input1.setErrorEnabled(true);
            input1.setError("ادخل نص الهدف");
            out = false;
        } else {
            input1.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(goallevel.getText().toString())) {
            input2.setErrorEnabled(true);
            input2.setError("ادخل قياس الهدف");
            out = false;

        } else {
            input2.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(goalidea.getText().toString())) {
            input3.setErrorEnabled(true);
            input3.setError("ادخل نبذة عن الهدف");
            out = false;
        } else {
            input3.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(goalideaaround.getText().toString())) {
            input4.setErrorEnabled(true);
            input4.setError("ادخل فكرة حول الهدف");
            out = false;
        } else {
            input4.setErrorEnabled(false);
        }
        if (cheak.isEmpty()&&s2.isShown()) {
            Toast.makeText(getContext(), "اختر قسم واحد ع الاقل", Toast.LENGTH_LONG).show();
            out = false;
        }
        if (TextUtils.isEmpty(from.getText().toString())) {
            input12.setErrorEnabled(true);
            input12.setError("ادخل التاريخ");
            out = false;
        }
        if (TextUtils.isEmpty(to.getText().toString())) {
            input11.setErrorEnabled(true);
            input11.setError("ادخل التاريخ");
            out = false;
        }

        return out;
    }

    public void click() {

        addgoal.setOnClickListener(this);
        from.setOnClickListener(this);
        to.setOnClickListener(this);
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.from2:
                input12.setErrorEnabled(false);
                Dialog();
                ClickedDate = 0;
                break;
            case R.id.to2:
                input11.setErrorEnabled(false);
                Dialog();
                ClickedDate = 1;
                break;
            case R.id.addgoal:
                validate();
                if (validate()) {
                    String[] array = cheak.toArray(new String[cheak.size()]);
                    System.out.println(codenumber.getText()+"\n"+s1.getSelectedItem() + ""+"\n"+s3.getSelectedItem() + ""+"\n"
                     + s2.getSelectedItem() + ""+goalbody.getText()+"\n"+from.getText()+"\n"+to.getText()+"\n"+goallevel.getText()+"\n"
                    +goalidea.getText()+"\n"+array+"\n");
                    try {
                        RequestParams params = new RequestParams();
                        params.put("request", "addgoalvalue");
                        params.put("goal_code", codenumber.getText());
                        params.put("goal_type", s1.getSelectedItem() + "");
                        params.put("goal_important", s3.getSelectedItem() + "");
                        if (s2.getSelectedItemPosition() == 0) {
                            params.put("departments", "");
                        } else {
                            params.put("departments", s2.getSelectedItem() + "");
                        }
                        params.put("goal_title", goalbody.getText());
                        params.put("goal_date_from", from.getText());
                        params.put("goal_date_to", to.getText());
                        params.put("goal_apprev", goallevel.getText());
                        params.put("goal_measurment", goalidea.getText());
                        params.put("goal_idea", goalideaaround.getText());

                        params.put("cheackbox", array);
                        System.out.println(params+"testemy");
                        Load(params);
                    } catch (Exception ex) {
                        Toast.makeText(getActivity().getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
                    }


                }
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
        goalbody = (EditText) v.findViewById(R.id.goalbody);
        goallevel = (EditText) v.findViewById(R.id.goallevel);
        goalidea = (EditText) v.findViewById(R.id.goalidea);
        goalideaaround = (EditText) v.findViewById(R.id.goalideaaround);
        to = (EditText) v.findViewById(R.id.to2);
        codenumber=(TextView)v.findViewById(R.id.codenum);
        addgoal = (TextView) v.findViewById(R.id.addgoal);
        addgoal.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi.ttf"));
        input12 = (TextInputLayout) v.findViewById(R.id.input12);
        input11 = (TextInputLayout) v.findViewById(R.id.input11);
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
        int month1 = month + 1;
        int m = 0;
        int d = 0;
        if (month1 > 9 && day > 9) {
            Date = year + "/" + month1 + "/" + day;
        } else if (month1 < 9 && day > 9) {
            Date = year + "/" + "0" + month1 + "/" + day;
        } else if (month1 > 9 && day < 9) {
            Date = year + "/" + month1 + "/" + "0" + day;
        } else {
            Date = year + "/" + "0" + month1 + "/" + "0" + day;
        }


        if (ClickedDate == 0) {
            from.setText(Date);
        } else {
            to.setText(Date);
        }
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
                int place = parent.getSelectedItemPosition();
                if (layout.getChildCount() != 0) {
                    layout.removeAllViews();
                }
                if (place != 0) {
                    oName = cheackName[place - 1].split(",");

                    if (oName.length > 1) {
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
                    } else {
                        layout.setVisibility(View.GONE);
                    }
                } else {
                    layout.setVisibility(View.GONE);
                }

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void Load(RequestParams params) throws JSONException {

        AsyncHttpClient.post("", params, new JsonHttpResponseHandler() {
            ProgressDialog progressDialog;

            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setCancelable(false);
                progressDialog.setMessage("جارى البحث...");
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.e("onSuccess", response + "");
                Log.e("onSuccess", response.length() + "");
                try {
                    if (response.length() != 1) {
                    a4 = new String[response.length() - 2];
                    id = new String[response.length() - 2];
                    cheackName = new String[a4.length];
                    for (int i = 0; i < response.length() - 2; i++) {
                        JSONObject json_data = response.getJSONObject(i + "");
                        a4[i] = json_data.getString("main_dep_name");
                        id[i] = json_data.getString("id");
                    }
                    JSONArray subdebartement = response.getJSONArray("subdebartement");
                    Log.e("onSuccess", subdebartement + "");
                    for (int i = 0; i < a4.length; i++) {
                        String box = "";
                        for (int j = 0; j < subdebartement.length(); j++) {
                            JSONObject json_data1 = subdebartement.getJSONObject(j);

                            if (id[i].equals(json_data1.getString("main_dep_f_id"))) {
                                if (box == null) {
                                    box += json_data1.getString("sub_dep_name");
                                } else {
                                    box += json_data1.getString("sub_dep_name") + ",";
                                }
                            }
                        }

                        cheackName[i] = box;
                    }

                    SpinnerDate(a3, a4, s2);
                        codenumber.setText(Integer.parseInt(response.getString("maxid")) + 1 + "");
                    } else {

                    }

                } catch (Exception ex) {

                    Toast.makeText(getActivity().getApplicationContext(), "اشاره النت ضغيفه", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(getActivity().getApplicationContext(), "onFailure", Toast.LENGTH_LONG).show();
                Log.e("onFailure", "----------" + responseString);

            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressDialog.dismiss();
            }
        });


    }


}
