package com.example.ok.madicalalatheer.Fonts;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ok on 10/11/2016.
 */

public class MySpinnerAdapter extends ArrayAdapter<String> {
    // Initialise custom font, for example:
    Typeface font = Typeface.createFromAsset(getContext().getAssets(),
            "fonts/DroidKufi-Bold.ttf");
    float width = 0, height = 0;
    double diagonalInches = 0;
    TelephonyManager manager;
    // (In reality I used a manager which caches the Typeface objects)
    // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

    public MySpinnerAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        DisplayMetrics metrics = new DisplayMetrics();
        manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.heightPixels / metrics.ydpi;
        height = metrics.widthPixels / metrics.xdpi;
        diagonalInches = Math.sqrt(height * height + width * width);
        System.out.println(diagonalInches+"hfdtgrdgfsdfetdtdtrdtr");
    }

    // Affects default (closed) state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);

        if (diagonalInches>=6.5) {
            //code for big screen (like tablet)
            ((TextView) view).setTextSize(30);
        }
        view.setTypeface(font);
        return view;
    }

    // Affects opened state of the spinner
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        if (diagonalInches>=6.5) {
            //code for big screen (like tablet)
            ((TextView) view).setTextSize(30);
        }
        view.setTypeface(font);
        return view;
    }
}
