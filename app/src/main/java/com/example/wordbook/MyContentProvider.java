package com.example.wordbook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {
    public static final int CAT_DIR=0;
    public static final int CAT_ITEM=1;
    public static final String AUTHORITY="com.example.wordbook.provider";
    private static UriMatcher uriMatcher;
    private DatabaseHelper dbHelper;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"CAT",CAT_DIR);
        uriMatcher.addURI(AUTHORITY,"CAT/#",CAT_ITEM);

    }
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        int deleteRows=0;
        switch (uriMatcher.match(uri)){
            case CAT_DIR:
                deleteRows=db.delete("CAT",selection,selectionArgs);
                break;
            case CAT_ITEM:
                String catName=uri.getPathSegments().get(1);
                deleteRows=db.delete("CAT","id=?",new String[]{catName});
                break;
            default:
                break;
        }
        // Implement this to handle requests to delete one or more rows.
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case CAT_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.wordbook.provider.CAT";

            case CAT_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.wordbook.provider.CAT";
            default:
                break;
        }
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Uri uriReturn=null;
        switch (uriMatcher.match(uri)){
            case CAT_DIR:
            case CAT_ITEM:
                long newCatName=db.insert("CAT",null,values);
                uriReturn=Uri.parse("content://"+AUTHORITY+"/cat/"+newCatName);
                break;
            default:
                break;
        }
        Log.d("mint","insert");
        // TODO: Implement this to handle requests to insert a new row.
        return uriReturn;
    }

    @Override
    public boolean onCreate() {
        dbHelper=new DatabaseHelper(getContext(),"CatShop.db",null,9);
        // TODO: Implement this to initialize your content provider on startup.
        Log.d("mint","build");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case CAT_DIR:
                cursor=db.query("CAT",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CAT_ITEM:
                String catName=uri.getPathSegments().get(1);
                cursor=db.query("CAT",projection,"id=?",new String[]{catName},null,null,sortOrder);
                break;
            default:
                break;
        }
        // TODO: Implement this to handle query requests from clients.
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        int updatedRows=0;
        switch (uriMatcher.match(uri)){
            case CAT_DIR:
                updatedRows=db.update("CAT",values,selection,selectionArgs);
                break;
            case CAT_ITEM:
                String catName=uri.getPathSegments().get(1);
                updatedRows=db.update("CAT",values,"id=?",selectionArgs);
                break;
            default:
                break;
        }
        // TODO: Implement this to handle requests to update one or more rows.
       return updatedRows;
    }
}
