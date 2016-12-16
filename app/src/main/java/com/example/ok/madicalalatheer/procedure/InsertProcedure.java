package com.example.ok.madicalalatheer.procedure;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsertProcedure extends Fragment implements View.OnClickListener, HijriCalendarView.OnDateSetListener, AdapterView.OnItemSelectedListener {
    String maxid = "", Date = "";
    int posation=10009000;
    Intent i;
    int e = 0;
    HijriCalendarDialog.Builder text;
    TextInputLayout input2Date, input2Date2, input2Date3, input2Date4, input2Date5;
    TextInputLayout Textinput1p, Textinput1p2, Textinput1p3, Textinput1p4, Textinput1p5;
    String[] a1, a2, a3, a4, procdure, code, goalid, a6, a7, employee, employeeId, emp;
    View v, v2, cardviewd, cardviewd2, cardviewd3, cardviewd4, cardviewd5, Insert, Spinner1;
    LinearLayout Scrol;
    TextInputLayout[] Input2 = new TextInputLayout[]{input2Date, input2Date2, input2Date3, input2Date4, input2Date5};
    int inputvales2[] = new int[]{R.id.input2Date, R.id.input2Date2, R.id.input2Date3, R.id.input2Date4, R.id.input2Date5};
    int Datet = 0;
    TextInputLayout[] Input = new TextInputLayout[]{Textinput1p, Textinput1p2, Textinput1p3, Textinput1p4, Textinput1p5};
    int inputvales[] = new int[]{R.id.Textinput1p, R.id.Textinput1p2, R.id.Textinput1p3, R.id.Textinput1p4, R.id.Textinput1p5};
    //View
    View[] valesview = new View[]{cardviewd, cardviewd2, cardviewd3, cardviewd4, cardviewd5};
    int vales[] = new int[]{R.id.cardviewd, R.id.cardviewd1, R.id.cardviewd2, R.id.cardviewd3, R.id.cardviewd4};
    JSONObject out = null;
    //All Spiner
    Spinner s1row, s2row, s3row, s4row, s5row;
    Spinner s[]=new Spinner[]{s1row, s2row, s3row, s4row, s5row};
    int[] Spinner = new int[]{R.id.s1row, R.id.s1row2, R.id.s1row3, R.id.s1row4, R.id.s1row5};
    //Date Time
    EditText date, date2, date3, date4, date5;
    EditText Text[] = new EditText[]{date, date2, date3, date4, date5};
    int[] Textall = new int[]{R.id.Date, R.id.Date2, R.id.Date3, R.id.Date4, R.id.Date5};
    //process
    EditText doing, doing2, doing3, doing4, doing5;
    EditText Editproc[] = new EditText[]{doing, doing2, doing3, doing4, doing5};
    int[] codeProcedureTextall = new int[]{R.id.doing, R.id.doing2, R.id.doing3, R.id.doing4, R.id.doing5};
    //Coding
    TextView addgoal;
    TextView codeProcedure, codeProcedure2, codeProcedure3, codeProcedure4, codeProcedure5;
    TextView codeing[] = new TextView[]{codeProcedure, codeProcedure2, codeProcedure3, codeProcedure4, codeProcedure5};
    int[] codeall = new int[]{R.id.codeProcedure, R.id.codeProcedure2, R.id.codeProcedure3, R.id.codeProcedure4, R.id.codeProcedure5};
    Spinner  s2;

    AutoCompleteTextView s1;
    ArrayAdapter<String> adapter;

    public InsertProcedure() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_insert_procedure, container, false);
        v2 = inflater.inflate(R.layout.procedurerow, container, false);
        TypefaceUtil.overrideFonts(getContext(), v);
        commponent();
        Click();
        SharedPreferences pref = getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);

        a6= pref.getString("employee", "").split("oo");
        a7= pref.getString("employeeId", "").split("oo");
        int f = 0;
        int f2 = 0;
        for (int x = 0; x < a6.length; x++) {
            String row[] = a6[x].split(",");
            f2 += row.length;
        }
        employee = new String[f2];
        employeeId = new String[f2];

        for (int i = 0; i < a6.length; i++) {
            String row[] = a6[i].split(",");
            String row2[] = a7[i].split(",");
            for (int j = 0; j < row.length; j++) {
                employee[f + j] = row[j];
                employeeId[f + j] = row2[j];
            }
            f += row.length;
        }

        try {
            RequestParams params = new RequestParams();
            params.put("request", "getproceduresparam");//هتغير الاسم حسب ما يقولك وهتبعتلة ال id من الshared refrance

            Load(params);
        } catch (Exception ex) {
            Toast.makeText(getActivity().getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
        }
        // recyclerView = (RecyclerView) v.findViewById(R.id.recycler_procedure);
        a1 = new String[]{"*اختر عدد الاجراءات"};
        emp = new String[]{"*قم باختيار الموظف"};
        a2 = new String[]{"1", "2", "3", "4", "5"};
        a3 = new String[]{"*قم باختيار الهدف"};
        SpinnerDate(a1, a2, s2);


        i = getActivity().getIntent();

        if (i.getStringExtra("Insertprocedure").equals("1")) {
          /*  try {
                RequestParams params = new RequestParams();
                params.put("request", "Editgoal");
                params.put("goalid", i.getStringExtra("goalIdEdit"));
                Load(params);
            } catch (Exception ex) {
                Toast.makeText(getActivity().getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
            }*/
            addgoal.setText("تعديل الاجراء");
            SpinnerDate(emp, employee, s[0]);

            valesview[0].setVisibility(View.VISIBLE);
            Spinner1.setVisibility(View.GONE);
            try {
                out = new JSONObject(i.getStringExtra("Data"));

                codeing[0].setText(out.getString("pro_code"));
                //s1.setSelection(Integer.parseInt(out.getString("goal_type")));
                int print = 0;
                for (int j = 0; j < employeeId.length; j++) {
                    if (employeeId[j].equals(out.getString("employee_id"))) {
                        print = j;
                        break;
                    }
                }

                s[0].setSelection(print + 1);
                Editproc[0].setText(out.getString("pro_title"));
                // DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                // SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");


                /*Date date = df.parse(str);
                long epoch = date.getTime();*/
                // format.format(date);  goal_date_to
                //  System.out.println(formatted);

                // System.out.println(df.format(new Date(new long[] {out.getString("goal_date_from")}))+"jjj");
                // from.setText(out.getString("goal_date_from"));


                //  to.setText(out.getString("goal_date_to"));

                Text[0].setText(out.getString("pro_end_date"));
               s1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        posation=position;
                       // Toast toast= Toast.makeText(getApplicationContext(), s + " is clicked", Toast.LENGTH_SHORT);
                       // toast.setGravity(Gravity.CENTER,0,0); toast.show();
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }













        return v;
    }

    public void commponent() {
        Spinner1 = v.findViewById(R.id.s22);
        addgoal = (TextView) v.findViewById(R.id.addgoal);
        s1 = (AutoCompleteTextView) v.findViewById(R.id.s1P);
        s2 = (Spinner) v.findViewById(R.id.s2P);
        Scrol = (LinearLayout) v.findViewById(R.id.Scrol);
        for (int i = 0; i < 5; i++) {
            valesview[i] = v.findViewById(vales[i]);
            s[i] = (Spinner) v.findViewById(Spinner[i]);
            Text[i] = (EditText) v.findViewById(Textall[i]);
            Editproc[i] = (EditText) v.findViewById(codeProcedureTextall[i]);
            codeing[i] = (TextView) v.findViewById(codeall[i]);
            Input[i] = (TextInputLayout) v.findViewById(inputvales[i]);
            Input2[i] = (TextInputLayout) v.findViewById(inputvales2[i]);

        }
        Insert = v.findViewById(R.id.Insert);
    }

    public Boolean Validate() {
        Boolean out = true;
        if (TextUtils.isEmpty(s1.getText())) {
            s1.setError("ادخل الهدف...");
            out = false;
        }
        if (i.getStringExtra("Insertprocedure").equals("0")) {
        if (s2.getSelectedItemPosition() == 0) {
            ((TextView) s2.getChildAt(0)).setError(".");
            out = false;
        }
        }
        if (i.getStringExtra("Insertprocedure").equals("1")) {
            e = 1;
        } else {
            e = s2.getSelectedItemPosition();
        }
        for (int i = 0; i < e; i++) {

            if (s[i].getSelectedItemPosition() == 0) {
                ((TextView) s[i].getChildAt(0)).setError(".");
                out = false;
            }
            if (TextUtils.isEmpty(Text[i].getText().toString())) {
                Input2[i].setErrorEnabled(true);
                Input2[i].setError("ادخل التاريخ");
                out = false;
            } else {
                Input2[i].setErrorEnabled(false);
            }
            if (TextUtils.isEmpty(Editproc[i].getText().toString())) {
                Input[i].setErrorEnabled(true);
                Input[i].setError("ادخل الاجراء");
                out = false;
            } else {
                Input[i].setErrorEnabled(false);
            }

        }

        return out;
    }
    public void Click() {
        s1.setOnClickListener(this);
        s2.setOnItemSelectedListener(this);
        Insert.setOnClickListener(this);
        for (int i = 0; i < 5; i++) {
            Text[i].setOnClickListener(this);
        }
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Date:
                Dialog();
                Datet = 0;
                break;
            case R.id.Date2:
                Dialog();
                Datet = 1;
                break;
            case R.id.Date3:
                Dialog();
                Datet = 2;
                break;
            case R.id.Date4:
                Dialog();
                Datet = 3;
                break;
            case R.id.Date5:
                Dialog();
                Datet = 4;
                break;
            case R.id.Insert:
                if (Validate()) {
                    try {
                        RequestParams params = new RequestParams();
                        if (i.getStringExtra("Insertprocedure").equals("1")) {
                            params.put("request", "update_procedure");
                            params.put("pro_num", 1 );
                            params.put("employee_id", employeeId[s[0].getSelectedItemPosition() - 1]);
                            params.put("pro_title",  Editproc[0].getText().toString());
                            params.put("pro_end_date", Text[0].getText().toString());
                            params.put("pro_code",codeing[0].getText().toString());
                        } else {
                            params.put("request", "insertprocedure");
                            params.put("pro_num", s2.getSelectedItemPosition() );
                            String arr1[] = new String[e ];
                            String arr2[] = new String[e ];
                            String arr3[] = new String[e ];
                            String arr4[] = new String[e ];
                            for (int i = 0; i < e; i++) {
                                arr1[i] = employeeId[s[i].getSelectedItemPosition() - 1];
                                arr2[i] = Text[i].getText().toString();
                                arr3[i] = Editproc[i].getText().toString();
                                arr4[i]=((Integer.parseInt(maxid))+i)+"";
                            }
                            params.put("employee_id", arr1);
                            params.put("pro_title", arr3);
                            params.put("pro_end_date", arr2);
                            params.put("pro_code",arr4);
                        }//هتغير الاسم حسب ما يقولك وهتبعتلة ال id من الshared refrance}

                        params.put("goal_id", goalid[posation]);

                        Load(params);
                        System.out.println(params+"dnsgvjfgh");
                    } catch (Exception ex) {
                        Toast.makeText(getActivity().getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
                    }
                }
                break;


        }
    }

    @Override
    public void onDateSet(int year, int month, int day) {
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

        Text[Datet].setText(Date);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.s2P:
                System.out.println(parent.getSelectedItem().toString() + "lll");
                procdure = new String[parent.getSelectedItemPosition()];
                code = new String[parent.getSelectedItemPosition()];
                for (int i = 0; i < 5; i++) {
                    if (i < parent.getSelectedItemPosition()) {
                        Input[i].setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
                        Input2[i].setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
                        valesview[i].setVisibility(View.VISIBLE);
                        codeing[i].setText((Integer.parseInt(maxid) + i) + "");
                        SpinnerDate(emp, employee, s[i]);
                    } else {
                        valesview[i].setVisibility(View.GONE);
                    }

                }

                break;
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
                    a4 = new String[response.length() - 1];
                    goalid = new String[response.length() - 1];
                    for (int i = 0; i < response.length() - 1; i++) {
                        JSONObject out = response.getJSONObject("" + i);
                        a4[i] = out.getString("goal_title");
                        goalid[i] = out.getString("id");
                    }
                    maxid = response.getString("maxid");
                    adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, a4);
                    s1.setAdapter(adapter);

                    if (i.getStringExtra("Insertprocedure").equals("1")) {
                        for (int x = 0; x < goalid.length; x++) {
                            if (goalid[x].equals(out.getString("goal_id"))) {
                             //   s1.setSelection(x);
                                s1.setText(a4[x]);
                                break;
                            }
                        }
                    }
                } catch (Exception ex) {

                    Toast.makeText(getActivity().getApplicationContext(), "اشاره النت ضغيفه", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("onFailure", "----------" + responseString);
                String insert = responseString.replace("<meta charset=\"utf-8\" />", "");
                try {
                    JSONObject x = new JSONObject(insert);
                    JSONObject response = x.getJSONObject("respond");
                    if (response.getInt("message") == 1) {
                        Intent i = new Intent(getContext(), activity_procedure.class);
                        i.putExtra("Insertprocedure", "0");
                        if(response.getString("action").equals("insert success")){
                            Toast.makeText(getActivity().getApplicationContext(), "تم الاضافة بنجاح", Toast.LENGTH_LONG).show();
                            startActivity(i);
                        }else{
                            Toast.makeText(getActivity().getApplicationContext(), "تم التعديل بنجاح", Toast.LENGTH_LONG).show();}
                        startActivity(i);
                    } else if (response.getInt("message") == 0) {
                        Toast.makeText(getActivity().getApplicationContext(), "حاول مرة اخرى ", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressDialog.dismiss();
            }
        });


    }

    public void Dialog() {

        text = new HijriCalendarDialog.Builder(getContext()).setUILanguage(HijriCalendarDialog.Language.Arabic).setOnDateSetListener(this).show();


    }
}
