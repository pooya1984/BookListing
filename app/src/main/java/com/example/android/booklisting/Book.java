package com.example.android.booklisting;



public class Book {



    private String mBookAuthor;
    private String mBookTitl;

    public Book(String BookTitl,String BookAutor) {
        mBookTitl= BookTitl;
        mBookAuthor=BookAutor;
    }

    public String getBookTitle() {
        return mBookTitl;
    }
    public String getBookAuthor() {return mBookAuthor;}

}