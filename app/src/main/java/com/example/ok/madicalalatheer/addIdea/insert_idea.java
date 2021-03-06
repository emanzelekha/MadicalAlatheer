package com.example.ok.madicalalatheer.addIdea;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.AddGoal.AddGoal;
import com.example.ok.madicalalatheer.Fonts.MySpinnerAdapter;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ahmed on 11/6/2016.
 */

public class insert_idea extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    TextView btn_add,Coding;
    JSONObject Date;
    EditText t1, t2;
    Spinner spinnermanges, spinner1;
    Spinner spinneremployer;
    Spinner sp_depart;
    String[] idsupall,a5,choose, choosetarget,choosetargetid, choosemang, manages, chooseemployer, employer,employerid, choose_depart, depart,departid,managesid;
    LinearLayout layout1, layout2;
    CardView Main;
    TextInputLayout input1, input2;
    View v;
    Intent i;
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
        choose = new String[]{"اختر الهدف"};
        choosemang = new String[]{"اختر الادارة التابع لها"};
        chooseemployer = new String[]{"اختر الموظف"};
        choose_depart = new String[]{"اختر القسم"};
        v = inflater.inflate(R.layout.insert_idea, container, false);
        TypefaceUtil.overrideFonts(getContext(), v);
        commponent();
        SharedPreferences pref = getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);

        a5 = pref.getString("SubDep", "").split("oo");

        idsupall = pref.getString("SubDepId", "").split("oo");
        try {
            RequestParams params = new RequestParams();
            params.put("request", "ideaformspinner");

            Load(params);
        } catch (Exception ex) {
            Toast.makeText(getActivity().getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
        }
        addFont();
        click();



        i = getActivity().getIntent();
        if (i.getStringExtra("InsertIdea").equals("1")) {
            try {
                Date = new JSONObject(i.getStringExtra("IdeaData"));
              //  spinner1.setSelection();
                System.out.println(i.getStringExtra("IdeaData") + "Ooooout");
                t1.setText(Date.getString("idea_appre"));
                t2.setText(Date.getString("idea_content"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            btn_add.setText("تعديل الفكرة");
            // JSONObject out = null;

/*
            try {
                out = new JSONObject(i.getStringExtra("goalData"));
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
        return v;
    }

    public void click() {
        spinnermanges.setOnItemSelectedListener(this);
        sp_depart.setOnItemSelectedListener(this);
        btn_add.setOnClickListener(this);
    }

    public boolean validate() {
        boolean out = true;
        if (spinner1.getSelectedItemPosition() == 0) {
            ((TextView) spinner1.getChildAt(0)).setError(".");
            out = false;
        }
        if (spinnermanges.isShown()) {
            if (spinnermanges.getSelectedItemPosition() == 0) {
                ((TextView) spinnermanges.getChildAt(0)).setError(".");
                out = false;
            }
        }
        if (spinneremployer.isShown()) {
        if (spinneremployer.getSelectedItemPosition() == 0) {
            ((TextView) spinneremployer.getChildAt(0)).setError(".");
            out = false;
        }}
        if (sp_depart.isShown()) {
        if (sp_depart.getSelectedItemPosition() == 0) {
            ((TextView) sp_depart.getChildAt(0)).setError(".");
            out = false;
        }}
        if (TextUtils.isEmpty(t1.getText().toString())) {
            input1.setErrorEnabled(true);
            input1.setError("ادخل نص الفكرة");
            out = false;
        } else {
            input1.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(t2.getText().toString())) {
            input1.setErrorEnabled(true);
            input1.setError("نبذة عن الفكرة");
            out = false;
        } else {
            input1.setErrorEnabled(false);
        }
        return out;
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
                    depart = a5[spinnermanges.getSelectedItemPosition() - 1].split(",");
                    departid=idsupall[spinnermanges.getSelectedItemPosition() - 1].split(",");
                    SpinnerDate(choose_depart, depart, sp_depart);
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
        Coding=(TextView) v.findViewById(R.id.Coding);
        t1=(EditText)v.findViewById(R.id.editText);
        t2=(EditText)v.findViewById(R.id.editText2);
        layout1 = (LinearLayout) v.findViewById(R.id.layout1);
        layout2 = (LinearLayout) v.findViewById(R.id.layout2);
        input1 = (TextInputLayout) v.findViewById(R.id.inputText1);
        input2 = (TextInputLayout) v.findViewById(R.id.inputText2);
        spinner1 = (Spinner) v.findViewById(R.id.sp_choosetarget);
        spinnermanges = (Spinner) v.findViewById(R.id.sp_managment);
        spinneremployer = (Spinner) v.findViewById(R.id.sp_employer);
        sp_depart = (Spinner) v.findViewById(R.id.sp_depart);
        btn_add = (TextView) v.findViewById(R.id.add_btn);
        btn_add.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi.ttf"));
        Main = (CardView) v.findViewById(R.id.InsertIdea);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated
        Main.setMinimumHeight(height - 310);
    }
    public void addFont() {
        input1.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
        input2.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_btn:
                validate();
                if(validate()){
                    try {
                        RequestParams params = new RequestParams();
                        if (i.getStringExtra("InsertIdea").equals("1")) {
                        params.put("request", "update_idea");
                        params.put("ideaid",i.getStringExtra("Ideaid"));
                        }
                        else{
                            params.put("request", "add_idea");
                        }
                        params.put("goal_id",choosetargetid[spinner1.getSelectedItemPosition()-1]);
                        params.put("idea_emp_id",employerid[spinneremployer.getSelectedItemPosition()-1]);
                        params.put("idea_code",Coding.getText());
                        params.put("idea_content",t1.getText());
                        params.put("idea_appre",t2.getText());
                        SharedPreferences pref = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
                        String id = pref.getString("name", "");
                        params.put("admin_name",id);
                        Load(params);
                        System.out.println(params+"fghfrjgf");
                    } catch (Exception ex) {
                        Toast.makeText(getActivity().getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
                    }








                }
                break;
        }

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
                    if (response.length() == 5) {

                        JSONArray Goal = response.getJSONArray("goalinfo");
                        JSONArray Dep = response.getJSONArray("departementinfo");
                        JSONArray Emp = response.getJSONArray("employeeinfo");
                        JSONArray subDep = response.getJSONArray("supdepartementinfo");

                        depart = new String[subDep.length()];
                        departid = new String[subDep.length()];
                        employer = new String[Emp.length()];
                        employerid = new String[Emp.length()];
                        manages=new String[Dep.length()];
                        managesid=new String[Dep.length()];
                        choosetarget = new String[Goal.length()];
                        choosetargetid = new String[Goal.length()];
                        for(int i=0;i<Goal.length();i++){
                            JSONObject goales=Goal.getJSONObject(i);
                            choosetarget[i]=goales.getString("goal_title");
                            choosetargetid[i]=goales.getString("id");
                        }
                        for(int i=0;i<Dep.length();i++){
                            JSONObject Deps=Dep.getJSONObject(i);
                            manages[i]=Deps.getString("main_dep_name");
                            managesid[i]=Deps.getString("id");
                        }
                        for(int i=0;i<Emp.length();i++){
                            JSONObject Emps=Emp.getJSONObject(i);
                            employer[i]=Emps.getString("emp_name");
                            employerid[i]=Emps.getString("id");
                        }
                       /* for(int i=0;i<subDep.length();i++){
                            JSONObject subDeps=subDep.getJSONObject(i);
                            depart[i]=subDeps.getString("sub_dep_name");
                            departid[i]=subDeps.getString("id");
                        }*/

                        SpinnerDate(choose, choosetarget, spinner1);
                        SpinnerDate(choosemang, manages, spinnermanges);
                        SpinnerDate(chooseemployer, employer, spinneremployer);
                        if (i.getStringExtra("InsertIdea").equals("1")) {
                            Coding.setText(Date.getString("id"));
                            for (int i = 0; i < choosetargetid.length; i++) {
                                if (Date.getString("goal_id").equals(choosetargetid[i])) {
                                    spinner1.setSelection(i + 1);
                                    break;
                                }
                            }
                        } else {
                            Coding.setText(Integer.parseInt(response.getJSONObject("id").getString("id")) + 1 + "");
                        }

                    }else {
                        JSONObject response1=response.getJSONObject("respond");
                        if(response1.getString("action").equals("insert success")&&response1.getString("message").equals("1")){
                            Intent i = new Intent(getContext(), activity_addIdea.class);
                            i.putExtra("InsertIdea", "0");
                            Toast.makeText(getActivity().getApplicationContext(), "تم تسجيل الفكرة بنجاح", Toast.LENGTH_LONG).show();
                            startActivity(i);
                        }

                       else if(response1.getString("action").equals("updated success")&&response1.getString("message").equals("1")){
                            Intent i = new Intent(getContext(), activity_addIdea.class);
                            i.putExtra("InsertIdea", "0");
                            Toast.makeText(getActivity().getApplicationContext(), "تم تعديل الفكرة بنجاح", Toast.LENGTH_LONG).show();
                            startActivity(i);
                        }

                        else{
                            Toast.makeText(getActivity().getApplicationContext(), "حاول مرة اخرى ", Toast.LENGTH_LONG).show();
                        }
                    }




                } catch (Exception ex) {

                    Toast.makeText(getActivity().getApplicationContext(), "اشاره النت ضغيفه", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                // Toast.makeText(getActivity().getApplicationContext(), "onFailure", Toast.LENGTH_LONG).show();
                // Log.e("onFailure", "----------" + responseString);

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
