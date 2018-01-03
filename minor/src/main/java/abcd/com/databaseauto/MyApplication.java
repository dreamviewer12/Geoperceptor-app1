package abcd.com.databaseauto;

import android.app.Application;
import android.content.Context;

/**
 * Created by HP on 04-10-2017.
 */

public class MyApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getAppContext() {
        return context;
    }
}
