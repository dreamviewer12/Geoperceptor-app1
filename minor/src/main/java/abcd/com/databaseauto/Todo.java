package abcd.com.databaseauto;

/**
 * Created by HP on 04-10-2017.
 */

public class Todo {
    public int id;
    public String place;
    public String item;
    public Todo( int id, String place,String item) {
        this.item = item;
        this.place = place;
        this.id = id;
    }
}
