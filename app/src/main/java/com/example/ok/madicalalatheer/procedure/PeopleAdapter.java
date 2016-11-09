package com.example.ok.madicalalatheer.procedure;

/**
 * Created by ahmed on 11/2/2016.
 */


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ok.madicalalatheer.R;
import com.innodroid.expandablerecycler.ExpandableRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends ExpandableRecyclerAdapter<PeopleAdapter.PeopleListItem> {
    public static final int TYPE_PERSON = 1001;

    public PeopleAdapter(Context context) {
        super(context);

        setItems(getSampleItems());
    }

    public static class PeopleListItem extends ExpandableRecyclerAdapter.ListItem {
        public String Text;
        public String txt_last;
        public int sort;
        public String date;

        public PeopleListItem(int sort,String group) {
            super(TYPE_HEADER);

            Text = group;
            this.sort=sort;
        }

        public PeopleListItem(String first,String date) {
            super(TYPE_PERSON);

            Text = first +"" ;
            this.date = date+"";
        }
    }

    public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        TextView txt_target,sort;

        public HeaderViewHolder(View view) {
            super(view, (ImageView) view.findViewById(R.id.item_arrow));

            txt_target = (TextView) view.findViewById(R.id.txt_target);
            sort=(TextView)view.findViewById(R.id.num);
        }

        public void bind(int position) {
            super.bind(position);

            txt_target.setText(visibleItems.get(position).Text);
            sort.setText((String.valueOf(visibleItems.get(position).sort)));
        }
    }

    public class PersonViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        TextView procedure,date;

        public PersonViewHolder(View view) {
            super(view);

            procedure = (TextView) view.findViewById(R.id.procedure);
            date=(TextView)view.findViewById(R.id.date);
        }

        public void bind(int position) {

            procedure.setText(visibleItems.get(position).Text);
            date.setText(visibleItems.get(position).date);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new HeaderViewHolder(inflate(R.layout.row_expand_parent, parent));
            case TYPE_PERSON:
            default:
                return new PersonViewHolder(inflate(R.layout.row_expand_child, parent));
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

    private List<PeopleListItem> getSampleItems() {

        List<PeopleListItem> items = new ArrayList<>();
        items.add(new PeopleListItem(1,"الهدف الاول"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem(2,"الهدف الاول"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem(3,"الهدف الاول"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));
        items.add(new PeopleListItem("الأجراء الجديد","9/2/1438"));

        return items;
    }
}