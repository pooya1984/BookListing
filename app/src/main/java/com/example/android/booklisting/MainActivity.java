package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>,View.OnClickListener {

    //Variable to log errors as they occur
    public static final String LOG_TAG = MainActivity.class.getName();

    private static final int BOOK_LOADER_ID = 1;

    //Google Books API URL
    private static String GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    //Variable for the user inputs (text to search for and search button)
    private EditText editText;
    private Button searchButton;
    private ListView bookListView;
    private BookListAdapter adapter;
    private TextView mEmptyBookListTextView;
    private static String CURRENT_URL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        adapter = new BookListAdapter(this, new ArrayList<Book>());
        editText = (EditText) findViewById(R.id.text_edit);
        searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(MainActivity.this);

        bookListView = (ListView) findViewById(R.id.list);
        bookListView.setEmptyView(mEmptyBookListTextView);
        mEmptyBookListTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setAdapter(adapter);

        ConnectivityManager connMgr = (ConnectivityManager)
        getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.progress_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyBookListTextView.setText(R.string.info_no_books);
        }
    }

    @Override
    public void onClick(View v) {
        String searchString = editText.getText().toString();

        CURRENT_URL = GOOGLE_BOOKS_URL + searchString;

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            getLoaderManager().restartLoader(BOOK_LOADER_ID, null, MainActivity.this);
        } else {
            // Update empty state with no connection error message
            mEmptyBookListTextView.setText(R.string.info_no_books);
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, CURRENT_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        View loadingIndicator = findViewById(R.id.progress_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyBookListTextView.setText(R.string.no_books);

        adapter.clear();

        if (books != null && !books.isEmpty()) {
            adapter.addAll(books);}
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        adapter.clear();
    }
}