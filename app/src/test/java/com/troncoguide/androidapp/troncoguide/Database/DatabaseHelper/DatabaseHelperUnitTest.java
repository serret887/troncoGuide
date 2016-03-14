package com.troncoguide.androidapp.troncoguide.Database.DatabaseHelper;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.troncoguide.androidapp.troncoguide.ContentProvider.Contract.DatabaseContract;
import com.troncoguide.androidapp.troncoguide.ContentProvider.DatabaseHelper.DatabaseHelper;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Alejandro on 12/3/2015.
 */

public class DatabaseHelperUnitTest extends AndroidTestCase {


    private DatabaseHelper dbHelper;

    @BeforeClass
    public void setUp() throws Exception {
        dbHelper = new DatabaseHelper(mContext);
        junit.framework.Assert.assertNotNull("dbHelper cant be null", dbHelper);
    }




    @Test
    public void testOnCreate_executeQuery() throws Exception {
        SQLiteDatabase sq = mock(SQLiteDatabase.class);
        dbHelper.onCreate(sq);
        verify(sq, times(1)).execSQL(" CREATE TABLE "+
                DatabaseContract.MovieEntry.TABLE_NAME + " ( "+
                DatabaseContract.MovieEntry._ID +" INTEGER PRIMARY KEY , "+
                DatabaseContract.MovieEntry.COLUMN_TITLE+"  TEXT NOT NULL UNIQUE , "+
                DatabaseContract.MovieEntry.COLUMN_VOTE_AVG+" REAL , "+
                DatabaseContract.MovieEntry.COLUMN_BACKDROP_IMG+" TEXT , "+
                DatabaseContract.MovieEntry.COLUMN_POSTER_IMG+" TEXT , "+
                DatabaseContract.MovieEntry.COLUMN_VOTE_COUNT +" REAL  "+" ); ");
    }

    @Test
    public void testOnUpgrade() throws Exception {
        SQLiteDatabase sq = mock(SQLiteDatabase.class);
        DatabaseHelper mockdb = spy(dbHelper);
        mockdb.onUpgrade(sq, 1, 2);
        verify(sq, times(2)).execSQL(anyString());
        verify(mockdb, times(1)).onCreate(sq);
    }

    @Test
    public void testOnDowngrade() throws Exception {
        SQLiteDatabase sq = mock(SQLiteDatabase.class);
        DatabaseHelper mockdb = spy(dbHelper);
        mockdb.onDowngrade(sq, 1, 2);
        verify(sq, times(2)).execSQL(anyString());
        verify(mockdb, times(1)).onCreate(sq);
    }
}

