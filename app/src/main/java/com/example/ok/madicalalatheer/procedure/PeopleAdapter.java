package com.example.ok.madicalalatheer.procedure;

/**
 * Created by ahmed on 11/2/2016.
 */


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ok.madicalalatheer.AddGoal.AddGoal;
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
        public JSONObject detals;

        public PeopleListItem(int sort, String group) {
            super(TYPE_HEADER);

            Text = group;
            this.sort = sort;
        }

        public PeopleListItem(String first, String date, JSONObject detals) {
            super(TYPE_PERSON);
            this.detals = detals;
            Text = first + "";
            this.date = date + "";
    }
    }

    public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        TextView txt_target, sort;

        public HeaderViewHolder(View view) {
            super(view, (ImageView) view.findViewById(R.id.item_arrow));
            txt_target = (TextView) view.findViewById(R.id.txt_target);
            sort = (TextView) view.findViewById(R.id.num);
        }

        public void bind(int position) {
            super.bind(position);
            txt_target.setText(visibleItems.get(position).Text);
            sort.setText((String.valueOf(visibleItems.get(position).sort)));

    }
    }

    public class PersonViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        TextView procedure, date;
        View Editproc, Deletproc;

        public PersonViewHolder(View view) {
            super(view);
            procedure = (TextView) view.findViewById(R.id.procedure);
            date = (TextView) view.findViewById(R.id.date);
            Deletproc = view.findViewById(R.id.Deletproc);
            Editproc = view.findViewById(R.id.Editproc);
        }

        public void bind(final int position) {
            procedure.setText(visibleItems.get(position).Text);
            date.setText(visibleItems.get(position).date);

            Deletproc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        RequestParams params = new RequestParams();
                        params.put("request", "delete_procedure");//هتغير الاسم حسب ما يقولك وهتبعتلة ال id من الshared refrance
                        params.put("proid", visibleItems.get(position).detals.getString("goal_id"));
                        Load(params);
                    } catch (Exception ex) {
                        Toast.makeText(context.getContext(), "Exception" + ex, Toast.LENGTH_LONG).show();
                }

                    System.out.println(visibleItems.get(position).detals + "jhjgyugy");
                }
            });

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

}