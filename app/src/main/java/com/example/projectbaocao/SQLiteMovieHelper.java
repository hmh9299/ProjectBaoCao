package com.example.projectbaocao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteMovieHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "KhoMovie";
    private static final int DATABASE_VERSION = 1;

    public SQLiteMovieHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_FILM = "CREATE TABLE movie(" +
                "id integer primary key autoincrement,"+
                "name text,"+
                "type text,"+
                "description text,"+
                "date text,"+
                "review integer"+
                ")";
        db.execSQL(SQL_FILM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    
    //Them phim
    public long addMovie(Movie movie){
        ContentValues values = new ContentValues();
//        values.put("id",movie.getId());
        values.put("name",movie.getName());
        values.put("type",movie.getType());
        values.put("description",movie.getDescription());
//        values.put("image",movie.getImage());
        values.put("date",movie.getDate());
        values.put("review",movie.getReview());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("movie",null,values);

    }

    //Xoa phim
    public int deleteMovie(int id){
        String whereClause = "id = ?";
        String[] args = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("movie",whereClause,args);
    }

    //Cap nhat phim
    public int updateMovie(Movie movie){
        String whereClause = "id = ?";
        String[] args = {Integer.toString(movie.getId())};
        ContentValues values = new ContentValues();
        values.put("name",movie.getName());
//        values.put("type",movie.getType());
        values.put("description",movie.getDescription());
        values.put("date",movie.getDate());
        values.put("review",movie.getReview());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("movie",values,whereClause,args);
    }

    //Lay toan bo phim
    public List<Movie> getAllMovie(){
        List<Movie> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("movie",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String type = cursor.getString(2);
            String description = cursor.getString(3);
            String date = cursor.getString(4);
            int review = cursor.getInt(5);
//            Movie movie = new Movie(id,name,type,description,date,review);
            Movie movie = new Movie(id,name,type,description,date,review);
            list.add(movie);
        }
        return list;
    }


    public Movie getById(int id){
        String sql = "select * from movie where id = ?";
        String[] args = { Integer.toString(id) };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, args);
        if(cursor.moveToNext()) {
            //id = cursor.getInt(0);
            String name = cursor.getString(1);
//            String type = cursor.getString(2);
            String description = cursor.getString(3);
            String date = cursor.getString(4);
            int review = cursor.getInt(5);
            Movie movie = new Movie(id,name,null,description,date,review);
            return movie;
        }
        return null;
    }

    //Tim kiem
    public List<Movie> searchName(String namemovie){
           String whereClause = "name like ?";
           String[] args = { "%" + namemovie + "%" };
           SQLiteDatabase sqLiteDatabase = getReadableDatabase();
           Cursor cursor = sqLiteDatabase.query("movie", null,whereClause,args,null,null,null);
           List<Movie> movies = new ArrayList<>();
           while (cursor.moveToNext()){
               int id = cursor.getInt(0);
               String name = cursor.getString(1);
               String type = cursor.getString(2);
               String description = cursor.getString(3);
               String date = cursor.getString(4);
               int review = cursor.getInt(5);
               Movie movie = new Movie(id,name,type,description,date,review);
               movies.add(movie);
           }
        return movies ;
    }

    public List<Movie> searchStar(int starmovie){
        String whereClause = "review like ?";
        String[] args = { "%" + starmovie + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("movie", null,whereClause,args,null,null,null);
        List<Movie> movies = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String type = cursor.getString(2);
            String description = cursor.getString(3);
            String date = cursor.getString(4);
            int review = cursor.getInt(5);
            Movie movie = new Movie(id,name,type,description,date,review);
            movies.add(movie);
        }
        return movies ;
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if(db!= null && db.isOpen())
            db.close();
    }
}
