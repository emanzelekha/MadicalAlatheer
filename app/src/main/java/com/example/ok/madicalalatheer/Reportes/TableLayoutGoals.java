package com.example.ok.madicalalatheer.Reportes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ok.madicalalatheer.Fonts.MySpinnerAdapter;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TableLayoutGoals extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1, s2;
    TextView close, subname, goals;
    Context c;
    View Mangment, baclspinner, font;
    String[] a1, a2 = null, a3, a4, a5, a6 = null, id, id2, iddep, idsupall, idsup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        component();
        TypefaceUtil.overrideFonts(this, Mangment);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        click();

        //  init();
        a1 = new String[]{"*اختيار الادارة"};
        a3 = new String[]{"*اختيار القسم"};
        SharedPreferences pref = getBaseContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
        a2 = pref.getString("MainDep", "").split(",");
        a5 = pref.getString("SubDep", "").split("oo");
        iddep = pref.getString("MainDepId", "").split(",");
        idsupall = pref.getString("SubDepId", "").split("oo");

        //  String maindep = pref.getString("maindep", "");

        SpinnerDate(a1, a2, s1);

    }

    public void click() {
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
    }

    public void component() {
        close = (TextView) findViewById(R.id.close);
        goals = (TextView) findViewById(R.id.goals);
        subname = (TextView) findViewById(R.id.supname);
        Mangment = findViewById(R.id.activity_table_layout);
        font = findViewById(R.id.font);
        s1 = (Spinner) findViewById(R.id.s1);
        s2 = (Spinner) findViewById(R.id.s2);
        baclspinner = findViewById(R.id.baclspinner);
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
                TableLayoutGoals.this,
                R.layout.spinrtitem,
                Arrays.asList(arraySpinner)
        );
        adapter.setDropDownViewResource(R.layout.downspinner);
        s.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.s1:
                if (s1.getSelectedItemPosition() != 0) {
                    font.setVisibility(View.GONE);
                    baclspinner.setVisibility(View.VISIBLE);
                    a4 = a5[s1.getSelectedItemPosition() - 1].split(",");
                    a6=idsupall[s1.getSelectedItemPosition() - 1].split(",");
                } else {
                    baclspinner.setVisibility(View.GONE);
                }
                SpinnerDate(a3, a4, s2);
                break;
            case R.id.s2:
                if(adapterView.getSelectedItemPosition()!=0){
                    //   font.setVisibility(View.GONE);
                    try {
                        RequestParams params = new RequestParams();
                        params.put("request", "goalreportoutput");
                        params.put("main", iddep[s1.getSelectedItemPosition() - 1]);
                        params.put("sub", a6[s2.getSelectedItemPosition() - 1]);
                        Load(params);

                    } catch (Exception ex) {
                        System.out.println(ex+"لطخة"+"khngjdfghju");
                    }}else {
                    //  font.setVisibility(View.GONE);
                }
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//web swrver function

    public void Load(RequestParams params) throws JSONException {

        AsyncHttpClient.post("", params, new JsonHttpResponseHandler() {
            ProgressDialog progressDialog;

            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(TableLayoutGoals.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("جارى التحميل...");
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.e("onSuccess", response + "");
                Log.e("onSuccess", response.length() + "");
                font.setVisibility(View.VISIBLE);
                try {
                    String p, g;
                    if (response.isNull("privite")) {
                        p = "0";
                    } else {
                        p = response.getString("privite");
                    }
                    if (response.isNull("global")) {
                        g = "0";
                    } else {
                        g = response.getString("global");
                    }
                    goals.setText("عام: "+g+" | "+"خاص: "+p);
                    subname.setText(a4[s2.getSelectedItemPosition() - 1]);

                } catch (Exception ex) {

                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

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