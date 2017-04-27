package com.liangcang.kunlun.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.liangcang.kunlun.Book;
import com.liangcang.kunlun.BookManager;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service
{
    public final String TAG = this.getClass().getSimpleName();
    // 包含Book对象的List
    private List<Book> mBooks = new ArrayList<>();

    public AIDLService()
    {

    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Book book = new Book();
        book.setName("大清相国");
        book.setPrice(28);
        mBooks.add(book);
    }

    private final BookManager.Stub mBookManager = new BookManager.Stub()
    {
        @Override
        public List<Book> getBooks() throws RemoteException
        {
            synchronized(this)
            {
                Log.d("AIDLService", "getBooks(), now the list is " + mBooks.toString());
                if(mBooks != null)
                {
                    return mBooks;
                }
                return new ArrayList<>();
            }

        }

        @Override
        public void addBook(Book book) throws RemoteException
        {
            synchronized(this)
            {
                if(mBooks == null)
                {
                    mBooks = new ArrayList<>();
                }
                if(book == null)
                {
                    Log.d("AIDLService", "addBook(), now the list is " + mBooks.toString());
                    book = new Book();
                }
                book.setPrice(25);
                if(!mBooks.contains(book))
                {
                    mBooks.add(book);
                }
            }

        }
    };

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d(getClass().getSimpleName(), String.format("on bind, intent = %s", intent.toString()));
        return mBookManager;
    }
}
