package com.mapletree.zihover.familytodo.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mapletree.zihover.familytodo.R;

import java.util.ArrayList;

/**
 * Created by yonghak on 2016-01-19.
 */
public class BookAdapter extends ArrayAdapter<Book> {
   public BookAdapter(Context context, ArrayList<Book> books) {
       super(context, 0, books);
   }

    @Override
    public View getView(int position,View convertView, ViewGroup parent){
        // Get the data item for this position
        Book book = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_fruit, parent, false);
        }
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.title);
        TextView tvKind = (TextView) convertView.findViewById(R.id.kind);

        TextView tvDate = (TextView) convertView.findViewById(R.id.date);
        TextView tvValue = (TextView) convertView.findViewById(R.id.value);

        // Populate the data into the template view using the data object
        tvTitle.setText(book.getTitle());
        tvKind.setText(book.getAuthor());

        tvDate.setText(book.getTitle());
        tvValue.setText(book.getAuthor());

        return convertView;

    }


}
