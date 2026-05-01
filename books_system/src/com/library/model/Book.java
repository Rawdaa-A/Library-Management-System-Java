package com.library.model;

public class Book {
    private int booksId;
    private String title;
    private String category;
    private int authorsId;
    private int libraryId;

    public Book(int booksId, String title, String category, int authorsId, int libraryId) {
        this.booksId   = booksId;
        this.title     = title;
        this.category  = category;
        this.authorsId = authorsId;
        this.libraryId = libraryId;
    }

    public int    getBooksId()               { return booksId; }
    public void   setBooksId(int booksId)    { this.booksId = booksId; }

    public String getTitle()                 { return title; }
    public void   setTitle(String title)     { this.title = title; }

    public String getCategory()              { return category; }
    public void   setCategory(String cat)    { this.category = cat; }

    public int    getAuthorsId()             { return authorsId; }
    public void   setAuthorsId(int id)       { this.authorsId = id; }

    public int    getLibraryId()             { return libraryId; }
    public void   setLibraryId(int id)       { this.libraryId = id; }

    @Override
    public String toString() { return title; }
}
