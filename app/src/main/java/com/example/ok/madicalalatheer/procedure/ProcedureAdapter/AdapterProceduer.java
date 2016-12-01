package com.example.ok.madicalalatheer.procedure.ProcedureAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ok.madicalalatheer.Fonts.MySpinnerAdapter;
import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.example.ok.madicalalatheer.procedure.Modle.Controlprocedure;

import net.alhazmy13.hijridatepicker.HijriCalendarDialog;
import net.alhazmy13.hijridatepicker.HijriCalendarView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/*
 * Created by ok on 06/11/2016.
 */

public class AdapterProceduer extends RecyclerView.Adapter<AdapterProceduer.MyViewHolder> implements View.OnClickListener, HijriCalendarView.OnDateSetListener
{
    HijriCalendarDialog.Builder text;
    ArrayList<String> DateData=new ArrayList<String>();;
    String[] a1, a2;
    EditText date, doing;
    private List<Controlprocedure> displayList;
    Context context;
    MyViewHolder context1;
    String Date = "";
    public AdapterProceduer(List<Controlprocedure> displayList) {
        this.displayList = displayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.procedurerow, parent, false);
        context = itemView.getContext();
        TypefaceUtil.overrideFonts(parent.getContext(), itemView);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //   NumberFormat nf = NumberFormat.getInstance(new Locale("ar", "EG"));//formate
        Controlprocedure disUserControl = displayList.get(position);
        holder.Code.setText(disUserControl.getSerial1());
        holder.serial.setText(disUserControl.getGoal());
        holder.input1.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/DroidKufi-Bold.ttf"));
        holder.input2.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/DroidKufi-Bold.ttf"));
        date.setOnClickListener(this);
        holder.input1.setOnClickListener(this);
        if (position + 1 == displayList.size()) {
            holder.Insert.setVisibility(View.VISIBLE);
        }

        holder.Insert.setOnClickListener(this);




        a1 = new String[]{"*قم باختيار الوظف"};
        a2 = new String[]{"1", "2", "3", "4", "5"};
        SpinnerDate(a1, a2, holder.s1);
    }

    public Boolean validate() {
        Boolean out = true;


        return out;
    }
    @Override
    public int getItemCount() {
        return displayList.size();
    }

    @Override
    public void onClick(View view) {
        Intent i;
        MyViewHolder holder = context1;
        switch (view.getId()) {

            case R.id.Date:
                text = new HijriCalendarDialog.Builder(context).setUILanguage(HijriCalendarDialog.Language.Arabic).setOnDateSetListener(this).show();
               DateData.add(date.getText() + "");
                break;

            case R.id.Insert:
                //  final AdapterProceduer holder1=context1;


                    if (DateData.isEmpty()) {
                        System.out.println("empty");

                  /*  if (TextUtils.isEmpty(doing.getText())) {
                        holder.input1.setErrorEnabled(true);
                        holder.input1.setError("ادخل نص الاجراء");

                    } else {
                        holder.input1.setErrorEnabled(false);
                    }
                    if (TextUtils.isEmpty(date.getText())) {
                        holder.input2.setErrorEnabled(true);
                        holder.input2.setError("ادخل نص الاجراء");

                    } else {
                        holder.input2.setErrorEnabled(false);
                    }*/


                }


                break;
        }
    }

    @Override
    public void onDateSet(int year, int month, int day) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("ar", "EG"));//formate
        // String year1 = nf.format(year);
        String month1 = (month + 1) + "";
        //String day1 = nf.format(day);
        Date = year + "/" + month1 + "/" + day;
        date.setText(Date);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextInputLayout input1, input2;

        public TextView Code, serial;
        public CardView card;
        public Spinner s1;
        public View Insert;

        public MyViewHolder(View view) {
            super(view);
            doing = (EditText) view.findViewById(R.id.doing);
            serial = (TextView) view.findViewById(R.id.codeProcedureText);
            Code = (TextView) view.findViewById(R.id.codeProcedure);
            card = (CardView) view.findViewById(R.id.cardview);
            input1 = (TextInputLayout) view.findViewById(R.id.Textinput1p);
            input2 = (TextInputLayout) view.findViewById(R.id.input2Date);
            s1 = (Spinner) view.findViewById(R.id.s1row);
            date = (EditText) view.findViewById(R.id.Date);
            Insert = view.findViewById(R.id.Insert);
        }
    }

    public void remove() {
        int size = this.displayList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.displayList.remove(0);
            }
            this.notifyItemRangeRemoved(0, size);
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
                context,
                R.layout.spinrtitem,
                Arrays.asList(arraySpinner)
        );
        adapter.setDropDownViewResource(R.layout.downspinner);
        s.setAdapter(adapter);
    }


}