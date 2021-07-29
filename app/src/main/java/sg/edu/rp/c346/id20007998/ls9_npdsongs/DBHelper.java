package sg.edu.rp.c346.id20007998.ls9_npdsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ndpSongs.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql="CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, " + COLUMN_SINGERS + " TEXT, " +
                COLUMN_YEAR + " INTEGER, " + COLUMN_STARS + " REAL ) ";
        db.execSQL(createSongTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);

    }
    public long insertSong(String title,String singers,int year, float stars){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_SINGERS,singers);
        values.put(COLUMN_YEAR,year);
        values.put(COLUMN_STARS,stars);
        long result=db.insert(TABLE_SONG,null,values);
        db.close();
        return result;
    }

    public ArrayList<Song> getAllSong(){
        ArrayList<Song> songs = new ArrayList<Song>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE +","
                + COLUMN_SINGERS+","
                +COLUMN_YEAR+","
                +COLUMN_STARS+ " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do{
                int id=cursor.getInt(0);
                String title=cursor.getString(1);
                String singers=cursor.getString(2);
                int year=cursor.getInt(3);
                float stars=cursor.getFloat(4);
                Song song=new Song(id,title,singers,year,stars);
                songs.add(song);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;

    }


    public ArrayList<Song> getSongByFilter(){
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns={COLUMN_ID,COLUMN_TITLE,COLUMN_SINGERS,COLUMN_YEAR,COLUMN_STARS};
        String condition=COLUMN_STARS+ " = 5";
        Cursor cursor = db.query(TABLE_SONG,columns,condition,null, null,null,null);
        if (cursor.moveToFirst()) {
            do{
                int id=cursor.getInt(0);
                String title=cursor.getString(1);
                String singers=cursor.getString(2);
                int year=cursor.getInt(3);
                float stars=cursor.getInt(4);
                Song song=new Song(id,title,singers,year,stars);
                songs.add(song);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;

    }

    public int updateSong(Song data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }
    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }

}
