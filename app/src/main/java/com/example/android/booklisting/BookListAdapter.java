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


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Book currentBook = getItem(position);


        EditText primaryBookView = (EditText) listItemView.findViewById(R.id.text_edit);


        primaryBookView.setText(currentBook.getBookTitle());



        return listItemView;
    }
}
