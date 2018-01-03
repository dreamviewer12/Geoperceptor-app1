package abcd.com.databaseauto;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HP on 04-10-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    String todo_table="todo";
   // String table="description";
    public DatabaseHelper() {
        super(MyApplication.getAppContext(), "db_name", null , 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // db.execSQL("Create table" + tb +"());
        Log.d("codekamp","OnCreateOfDatabaseHelper called");
        db.execSQL("CREATE TABLE `todo` ( `_id` INTEGER PRIMARY KEY AUTOINCREMENT, `place` TEXT, `item` TEXT );");
      //  db.execSQL("CREATE TABLE `description` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT',`place` TEXT UNIQUE,`latitude` DOUBLE,`longitude` DOUBLE);");
     /*   db.execSQL("CREATE TABLE 'places' ('_id' INTEGER PRIMARY KEY AUTOINCREMENT,''place' TEXT);");
        db.execSQL("CREATE TABLE 'todo'  ('_id' INTEGER PRIMARY KEY AUTOINCREMENT ,'pid' INTEGER ,'item' TEXT); ");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("codekamp","OnUpgradeOfDatabaseHelper called");
    }
}

