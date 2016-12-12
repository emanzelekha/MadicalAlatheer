package com.example.ok.madicalalatheer.procedure;

/**
 * Created by ahmed on 11/2/2016.
 */


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ok.madicalalatheer.Fonts.TypefaceUtil;
import com.example.ok.madicalalatheer.R;
import com.innodroid.expandablerecycler.ExpandableRecyclerAdapter;

import org.json.JSONObject;

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

        public PeopleListItem(int sort, String group) {
            super(TYPE_HEADER);

            Text = group;
            this.sort = sort;
        }

        public PeopleListItem(String first, String date,JSONObject detals) {
            super(TYPE_PERSON);

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

        public void bind(int position) {

            procedure.setText(visibleItems.get(position).Text);
            date.setText(visibleItems.get(position).date);
            Deletproc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }


}