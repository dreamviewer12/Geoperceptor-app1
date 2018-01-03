package trainage.minor_sem_5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 01-10-2017.
 */

public class PhoneContract {

    Helper helper;

    public PhoneContract(Context context) {
        this.helper= new Helper(context);
    }

    class Helper extends SQLiteOpenHelper {

        public Helper(Context context) {
            super(context, "secretDb", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
//we create our table here..
            db.execSQL("create table secret (_id INTEGER PRIMARY KEY AUTOINCREMENT, ques varchar(50) UNIQUE, ans VARCHAR(50));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//we delete our table here..
            db.execSQL("drop tables if exists secret");
            onCreate(db);
        }
    }

    interface Constants {
        public static final String TBL_MOVIES = "secret";
        public static final String COL_ID = "_id";
        public static final String COL_QUES = "ques";
        public static final String COL_ANS = "ans";

    }
    public long add(String ques, String ans)
    {
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Constants.COL_QUES, ques);
        values.put(Constants.COL_ANS, ans);

        return db.insert(Constants.TBL_MOVIES, null, values);
    }
    public Cursor list()
    {
        SQLiteDatabase db= helper.getReadableDatabase();
        return db.query(Constants.TBL_MOVIES, null, null, null, null, null, null);
    }
    public int deleteById(String id)
    {
        SQLiteDatabase db= helper.getWritableDatabase();
        String where=Constants.COL_ID+"=?";
        String[] whereargs= new String[]{id};
        return db.delete(Constants.TBL_MOVIES, where, whereargs); //will return how many rows are being deleted..or affected
    }
    public int updateById(String id, String ques, String ans)
    {
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Constants.COL_QUES, ques);
        values.put(Constants.COL_ANS, ans);

        String where=Constants.COL_ID+"=?";
        String[] whereargs= new String[]{id};
        return db.update(Constants.TBL_MOVIES, values, where, whereargs);

    }
}

