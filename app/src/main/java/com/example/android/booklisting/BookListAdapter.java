package com.example.android.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;


public class BookListAdapter extends ArrayAdapter<Book> {

    public BookListAdapter(Context context, List<Book> BookTitle) {
        super(context, 0, BookTitle);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }



        String primaryBook = null;

        // Find the TextView with view ID location
        EditText primaryBookView = (EditText) listItemView.findViewById(R.id.text_edit);
        // Display the location of the current earthquake in that TextView
        primaryBookView.setText(primaryBook);



        return listItemView;
    }
}
