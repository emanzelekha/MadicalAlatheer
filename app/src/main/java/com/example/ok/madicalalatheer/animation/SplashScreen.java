package com.example.ok.madicalalatheer.animation;

/**
 * Created by VGA! on 28/08/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ok.madicalalatheer.Main.Login;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class SplashScreen extends Activity {
int finish=0;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    Thread splashTread;
    String[] MainDep, MainDepId, SubDep, SubDepId;
    String MainDep1 = "", MainDepId1 = "", SubDep1 = "", SubDepId1 = "",SubDep1e = "", SubDepId1e = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alph);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        View text =  findViewById(R.id.splash_text);

       try {
            RequestParams params = new RequestParams();
            params.put("request", "goalsreport");
            Load(params);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
        }
        iv.clearAnimation();
        iv.startAnimation(anim);
        text.clearAnimation();
        text.startAnimation(anim);
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        while (finish==0){
                        sleep(100);}
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreen.this,
                            Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        startActivity(intent);
                        SplashScreen.this.finish();



                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashScreen.this.finish();
                }

            }
        };
        splashTread.start();

    }
    public void Load(RequestParams params) throws JSONException {

        AsyncHttpClient.post("", params, new JsonHttpResponseHandler() {
            ProgressDialog progressDialog;

            @Override
            public void onStart() {
               // progressDialog = new ProgressDialog(SplashScreen.this);
               /* progressDialog.setCancelable(false);
                progressDialog.setMessage("جارى التحميل...");
                progressDialog.show();*/
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.e("onSuccess", response + "");
                Log.e("onSuccess", response.length() + "");
                try {
                    MainDep = new String[response.length()-2 ];
                    MainDepId = new String[response.length()-2];
                    SubDep = new String[response.getJSONArray("supdepartement").length()];
                    SubDepId = new String[response.getJSONArray("supdepartement").length()];
                    for (int i = 0; i < response.length()-2; i++) {
                        JSONObject json_data = response.getJSONObject(i + "");
                        MainDep[i] = json_data.getString("main_dep_name");
                        MainDepId[i] = json_data.getString("id");
                        MainDepId1 += json_data.getString("id") + ",";
                        MainDep1 += json_data.getString("main_dep_name") + ",";

                    }
                    JSONArray subdebartement = response.getJSONArray("supdepartement");
                    for (int i = 0; i < response.length() - 2; i++) {
                        String set = "";
                        String set1 = "";
                        for (int j = 0; j < subdebartement.length(); j++) {
                            JSONObject json_data1 = subdebartement.getJSONObject(j);
                            if (MainDepId[i].equals(json_data1.getString("main_dep_f_id"))) {
                                set += json_data1.getString("id") + ",";
                                set1 += json_data1.getString("sub_dep_name") + ",";
                            }

                        }
                        if(set.equals("")&&set1.equals("")){
                            SubDepId1 += "0"+ "oo";
                            SubDep1 += "لا يوجد اقسام" + "oo";
                        }else {
                            SubDepId1 += set + "oo";
                            SubDep1 += set1 + "oo";}
                    }
                    JSONArray employee = response.getJSONArray("employee");
                    for (int i = 0; i < response.length() - 2; i++) {
                        String sete = "";
                        String set1e = "";
                        for (int j = 0; j < employee.length(); j++) {
                            JSONObject json_data1 = employee.getJSONObject(j);
                            if (MainDepId[i].equals(json_data1.getString("main_dep_f_id"))) {
                                sete += json_data1.getString("id") + ",";
                                set1e += json_data1.getString("emp_name") + ",";
                            }

                        }

                        SubDepId1e += sete + "oo";
                        SubDep1e += set1e + "oo";
                    }
                    System.out.println(SubDep1e + " bbbbbbb               " + SubDepId1e);
                    SharedPreferences sharedPref = getBaseContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("MainDep", MainDep1);
                    editor.putString("MainDepId", MainDepId1);
                    editor.putString("SubDep", SubDep1);
                    editor.putString("SubDepId", SubDepId1);
                    editor.putString("employee", SubDep1e);
                    editor.putString("employeeId", SubDepId1e);
                    editor.commit();

                } catch (Exception ex) {

                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("onFailure", "----------" + responseString);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                finish=1;

             //   progressDialog.dismiss();
            }
        });


    }
}
