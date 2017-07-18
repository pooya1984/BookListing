package com.example.android.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class BookListAdapter extends ArrayAdapter<Book> {
    private static final String LOG_TAG = BookListAdapter.class.getName();



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
        TextView primaryBookTitleView = (TextView) listItemView.findViewById(R.id.title_view);
        primaryBookTitleView.setText(currentBook.getBookTitle());

        TextView primaryBookAutorView = (TextView) listItemView.findViewById(R.id.author_view);
        primaryBookAutorView.setText(currentBook.getBookAuthor());



        return listItemView;
    }
}