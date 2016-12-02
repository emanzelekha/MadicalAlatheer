package com.example.ok.madicalalatheer.Reportes;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.example.ok.madicalalatheer.zoom.ZoomableRelativeLayout;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class TableLayoutPerformance extends AppCompatActivity {
View v;
    TextView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout_performance);
        v=findViewById(R.id.activity_table_layout_performance );
        TypefaceUtil.overrideFonts(getBaseContext(), v);
        init();
      /*  final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(this, new OnPinchListener());
        v.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });*/
        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        try {
            RequestParams params = new RequestParams();
            params.put("request", "proceduresreport");//åÊÛíÑ ÇáÇÓã ÍÓÈ ãÇ íÞæáß æåÊÈÚÊáÉ Çá id ãä Çáshared refrance

            Load(params);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
        }
    }


    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText(" المسلسل ");
        tv0.setTextColor(getResources().getColor( R.color.text));
        tv0.setGravity(Gravity.CENTER);
        tv0.setPadding(10,10,10,10);
        tv0.setTextSize(20);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" الهدف ");
        tv1.setTextSize(20);
        tv1.setTextColor(getResources().getColor( R.color.text));
        tv1.setGravity(Gravity.CENTER);
        tv1.setPadding(10,10,10,10);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" عدد الاجراءات ");
        tv2.setTextSize(20);
        tv2.setTextColor(getResources().getColor( R.color.text));
        tv2.setGravity(Gravity.CENTER);
        tv2.setPadding(10,10,10,10);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" تاريخ الاعتماد ");
        tv3.setTextColor(getResources().getColor( R.color.text));
        tv3.setGravity(Gravity.CENTER);
        tv3.setPadding(10,10,10,10);
        tv3.setTextSize(20);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(" القائمة بالاعتماد ");
        tv4.setTextSize(20);
        tv4.setTextColor(getResources().getColor( R.color.text));
        tv4.setGravity(Gravity.CENTER);
        tv4.setPadding(10,10,10,10);
        tbrow0.addView(tv4);

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
            t2v.setText("Product " + i+"frtdtfrvfvefrvdefrdefre");
            t2v.setBackground(getResources().getDrawable(R.drawable.table));
            t2v.setPadding(10,10,10,10);
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("Rs." + i+"hjgfygvthb tgfgbt");
            t3v.setBackground(getResources().getDrawable(R.drawable.table));
            t3v.setPadding(10,10,10,10);
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
          TextView t4v = new TextView(this);
            t4v.setText("" + i * 15 / 32 * 10);
            t4v.setBackground(getResources().getDrawable(R.drawable.table));
            t4v.setPadding(10,10,10,10);
            t4v.setTextColor(Color.BLACK);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            TextView t5v = new TextView(this);
            t5v.setText("" + i * 15 / 32 * 10);
            t5v.setBackground(getResources().getDrawable(R.drawable.table));
            t5v.setPadding(10,10,10,10);
            t5v.setTextColor(Color.BLACK);
            t5v.setGravity(Gravity.CENTER);
            tbrow.addView(t5v);
            stk.addView(tbrow);
        }

    }

    public void Load(RequestParams params) throws JSONException {

        AsyncHttpClient.post("", params, new JsonHttpResponseHandler() {
            ProgressDialog progressDialog;

            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(TableLayoutPerformance.this);
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

                Log.e("onFailure", "----------" +responseString);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressDialog.dismiss();
            }
        });


    }




}
