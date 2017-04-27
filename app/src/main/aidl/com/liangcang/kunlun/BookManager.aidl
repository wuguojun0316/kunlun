// BookManager.aidl
package com.liangcang.kunlun;

import com.liangcang.kunlun.Book;
// Declare any non-default types here with import statements

interface BookManager
{
    List<Book> getBooks();

    void addBook(in Book book);
}
