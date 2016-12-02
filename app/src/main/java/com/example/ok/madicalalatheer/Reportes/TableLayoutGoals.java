package com.example.ok.madicalalatheer.Reportes;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
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

import static android.support.v7.appcompat.R.attr.height;
import static android.support.v7.appcompat.R.id.top;

public class TableLayoutGoals extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
Spinner s1,s2;
    TextView close;
    View Mangment;
    String[] a1, a2 = null, a3, a4, a5, a6 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        TypefaceUtil.overrideFonts(getBaseContext(), Mangment);
        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        component();
        click();

        init();
        a1 = new String[]{"*اختيار الادارة", "عام", "خاص"};

        a3 = new String[]{"*اختيار القسم"};
        a4 = new String[]{"اختيار نوع الهدف", "عام", "خاص"};
        SpinnerDate(a1, a2, s1);
        SpinnerDate(a3, a4, s2);
        try {
            RequestParams params = new RequestParams();
            params.put("request", "goalsreport");

            Load(params);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
        }

    }
    public void click(){
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
    }
    public void component() {

        Mangment =  findViewById(R.id.activity_table_layout);
        s1 = (Spinner) findViewById(R.id.s1);
        s2 = (Spinner) findViewById(R.id.s2);
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
                getBaseContext(),
                R.layout.spinrtitem,
                Arrays.asList(arraySpinner)
        );
        adapter.setDropDownViewResource(R.layout.downspinner);
        s.setAdapter(adapter);
    }
    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText(" المسلسل ");
        tv0.setTextColor(Color.BLACK);
        tv0.setGravity(Gravity.CENTER);
        tv0.setPadding(10,10,10,10);

        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" الهدف ");

        tv1.setTextColor(Color.BLACK);
        tv1.setGravity(Gravity.CENTER);
        tv1.setPadding(10,10,10,10);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" عدد الاقسام ");
        tv2.setTextColor(Color.BLACK);
        tv2.setGravity(Gravity.CENTER);
        tv2.setPadding(10,10,10,10);
        tbrow0.addView(tv2);
       /* TextView tv3 = new TextView(this);
        tv3.setText(" Stock Remaining ");
        tv3.setTextColor(Color.BLACK);
        tv3.setGravity(Gravity.CENTER);
        tv3.setPadding(5,5,5,5);
        tbrow0.addView(tv3);*/
        stk.addView(tbrow0);
        for (int i = 0; i < 25; i++) {
            TableRow tbrow = new TableRow(this);
            tbrow.setBackground(i % 2 == 0 ?getResources().getDrawable( R.drawable.withee) :getResources().getDrawable( R.drawable.withet));
            TextView t1v = new TextView(this);
            t1v.setText("" + i);
            t1v.setBackground(getResources().getDrawable(R.drawable.table));
            t1v.setPadding(10,10,10,10);
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("Product " + i);
            t2v.setBackground(getResources().getDrawable(R.drawable.table));
            t2v.setPadding(10,10,10,10);
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("Rs." + i);
            t3v.setBackground(getResources().getDrawable(R.drawable.table));
            t3v.setPadding(10,10,10,10);
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
          /*  TextView t4v = new TextView(this);
            t4v.setText("" + i * 15 / 32 * 10);
            t4v.setBackground(getResources().getDrawable(R.drawable.table));
            t4v.setPadding(3,3,3,3);
            t4v.setTextColor(Color.BLACK);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);*/
            stk.addView(tbrow);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.s1:
                break;
            case R.id.s2:
                if (adapterView.getSelectedItem().toString().equals("خاص")) {
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
                progressDialog.setMessage("ÌÇÑì ÇáÈÍË...");
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.e("onSuccess", response + "");
                Log.e("onSuccess", response.length() + "");
                try {



                } catch (Exception ex) {

                    Toast.makeText(getApplicationContext(), "ÇÔÇÑå ÇáäÊ ÖÛíÝå", Toast.LENGTH_LONG).show();

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
