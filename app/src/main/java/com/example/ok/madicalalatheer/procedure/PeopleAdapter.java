package com.example.ok.madicalalatheer.procedure;

/**
 * Created by ahmed on 11/2/2016.
 */


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.DateFormatein.HajreDate;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.uilit.AsyncHttpClient;
import com.innodroid.expandablerecycler.ExpandableRecyclerAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class PeopleAdapter extends ExpandableRecyclerAdapter<PeopleAdapter.PeopleListItem> {
    public static final int TYPE_PERSON = 1001;
    View context;
    HajreDate dateout = new HajreDate();
    public PeopleAdapter(Context context) {
        super(context);


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                context = inflate(R.layout.row_expand_parent, parent);
                TypefaceUtil.overrideFonts(parent.getContext(), context);
                return new HeaderViewHolder(context);
            case TYPE_PERSON:
            default:
                context = inflate(R.layout.row_expand_child, parent);
                TypefaceUtil.overrideFonts(parent.getContext(), context);
                return new PersonViewHolder(context);
        }
    }

    @Override
    public void onBindViewHolder(ExpandableRecyclerAdapter.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                ((HeaderViewHolder) holder).bind(position);
                break;
            case TYPE_PERSON:
            default:
                ((PersonViewHolder) holder).bind(position);
                break;
        }
    }

    public static class PeopleListItem extends ExpandableRecyclerAdapter.ListItem {
        public String Text;
        public String txt_last;
        public int sort;
        public String date;
        public String button;
        public JSONObject detals;
        public String ButtonDate1;

        public PeopleListItem(int sort, String group, String ButtonType) {
            super(TYPE_HEADER);

            ButtonDate1 = ButtonType + "";
            Text = group;
            this.sort = sort;
        }

        public PeopleListItem(String first, String date, JSONObject detals, String buttons) {
            super(TYPE_PERSON);
            this.detals = detals;
            Text = first + "";

            button = buttons + "";
            this.date = date + "";
    }
    }

    public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        TextView txt_target, sort, Done;

        public HeaderViewHolder(View view) {
            super(view, (ImageView) view.findViewById(R.id.item_arrow));
            txt_target = (TextView) view.findViewById(R.id.txt_target);
            sort = (TextView) view.findViewById(R.id.num);
            Done = (TextView) view.findViewById(R.id.Done);

        }

        public void bind(int position) {
            super.bind(position);
            txt_target.setText(visibleItems.get(position).Text);
            sort.setText((String.valueOf(visibleItems.get(position).sort)));

            if (!(String.valueOf(visibleItems.get(position).ButtonDate1).equals("0"))) {
                Done.setText("تم إعتماد الهدف بتاريخ " + (dateout.Dateout(String.valueOf(visibleItems.get(position).ButtonDate1))) + " هـ ");
                Done.setVisibility(View.VISIBLE);
            } else {
                Done.setVisibility(View.GONE);
            }
    }
    }

    public class PersonViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        TextView procedure, date, Done1;
        View Editproc, Deletproc, Editproc1, Deletproc1;

        public PersonViewHolder(View view) {
            super(view);
            procedure = (TextView) view.findViewById(R.id.procedure);
            Done1 = (TextView) view.findViewById(R.id.Done2);
            date = (TextView) view.findViewById(R.id.date);
            Deletproc = view.findViewById(R.id.Deletproc);
            Editproc = view.findViewById(R.id.Editproc);
            Deletproc1 = view.findViewById(R.id.Deletproc1);
            Editproc1 = view.findViewById(R.id.Editproc1);
        }

        public void bind(final int position) {
            procedure.setText(visibleItems.get(position).Text);
            date.setText(visibleItems.get(position).date + " هـ ");
            if (visibleItems.get(position).button.equals("0")) {
                Editproc.setVisibility(View.VISIBLE);
                Deletproc.setVisibility(View.VISIBLE);


                Editproc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(context.getContext(),activity_procedure.class);
                    i.putExtra("Insertprocedure", "1");
                    i.putExtra("Data",visibleItems.get(position).detals+"");
                    context.getContext().startActivity(i);
                }
            });
            Deletproc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        RequestParams params = new RequestParams();
                        params.put("request", "delete_procedure");//هتغير الاسم حسب ما يقولك وهتبعتلة ال id من الshared refrance
                        params.put("id", visibleItems.get(position).detals.getString("pro_code"));
                        Load(params);
                    } catch (Exception ex) {
                        Toast.makeText(context.getContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
                }

                    System.out.println(visibleItems.get(position).detals + "jhjgyugy");
                }
            });
            } else {
                Editproc.setVisibility(View.GONE);
                Deletproc.setVisibility(View.GONE);
                //if(((int) new Date().getTime()/1000)>=Integer.parseInt(visibleItems.get(position).date)){
                if (true) {
                    Editproc1.setVisibility(View.VISIBLE);

                    Deletproc1.setVisibility(View.VISIBLE);
                    Editproc1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openChooseImage(1);
                        }
                    });
                    Deletproc1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openChooseImage(0);
                        }
                    });
                } else {
                    Editproc1.setVisibility(View.GONE);
                    Deletproc1.setVisibility(View.GONE);
                }

            }



    }
    }

    public void Load(RequestParams params) throws JSONException {

        AsyncHttpClient.post("", params, new JsonHttpResponseHandler() {
            ProgressDialog progressDialog;

            @Override
            public void onStart() {
                progressDialog = new ProgressDialog(context.getContext());
                progressDialog.setCancelable(false);
                progressDialog.setMessage("جارى البحث...");
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.e("onSuccess", response + "");
                Log.e("onSuccess", response.length() + "");
                try {
                    JSONObject r = response.getJSONObject("respond");
                    if (r.getInt("message") == 1) {
                        Toast.makeText(context.getContext(), "تم مسح الاجراء بنجاح", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(context.getContext(), activity_procedure.class);
                        i.putExtra("Insertprocedure", "0");
                        context.getContext().startActivity(i);

                    } else {

                        Toast.makeText(context.getContext(), "حاول مرة اخرى", Toast.LENGTH_LONG).show();

                    }

                } catch (Exception ex) {

                    Toast.makeText(context.getContext(), "اشاره النت ضغيفه", Toast.LENGTH_LONG).show();

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

    private void openChooseImage(int x) {

        RadioGroup rr;
        TextView dialog1;
        RadioButton r1, r2;
        EditText notes;
        Button save;
        final Dialog dialog = new Dialog(context.getContext());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog);
        rr = (RadioGroup) dialog.findViewById(R.id.rr);
        if (x == 0) {
            rr.setVisibility(View.GONE);
        } else {
            rr.setVisibility(View.VISIBLE);
        }
        r1 = (RadioButton) dialog.findViewById(R.id.r1);
        dialog1 = (TextView) dialog.findViewById(R.id.dialog);
        r2 = (RadioButton) dialog.findViewById(R.id.r2);
        notes = (EditText) dialog.findViewById(R.id.notes);
        save = (Button) dialog.findViewById(R.id.save);
        dialog1.setTypeface(Typeface.createFromAsset(context.getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
        r1.setTypeface(Typeface.createFromAsset(context.getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
        r2.setTypeface(Typeface.createFromAsset(context.getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
        save.setTypeface(Typeface.createFromAsset(context.getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
        notes.setTypeface(Typeface.createFromAsset(context.getContext().getAssets(), "fonts/DroidKufi-Bold.ttf"));
        // dialog.setTitle("اختر صورتك");


        dialog.show();

        //dialog.getWindow().setLayout((6*width)/6,(3*height)/6);
        SharedPreferences pref = context.getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
        System.out.println(pref.getInt("screen", 0) + "fbgg");

        dialog.getWindow().setLayout((6 * pref.getInt("screen", 0)) / 6, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}