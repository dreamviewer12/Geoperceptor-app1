package abcd.com.databaseauto;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by HP on 04-10-2017.
 */

public class TodoCursor extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TodoCursor(Cursor cursor) {
        super(cursor);
    }
    /* public String getTitle() {
                  return this.getString(this.getColumnIndex("title"));
              }
     public int getId() {
                  return this.getInt(this.getColumnIndex("_id"));
              }
     public int getCompleted() {
                  return this.getInt(this.getColumnIndex("completed"));
              }*/
    public Todo getTodo()
    {
        return new Todo(getInt(this.getColumnIndex("_id")), getString(this.getColumnIndex("place")), getString(this.getColumnIndex("item")));
    }
}

