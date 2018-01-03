package abcd.com.databaseauto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nitisha Agarwal on 10-10-2017.
 */

public class LangLongResponse {
    @Expose
    @SerializedName("results")
    public ArrayList<GeoList> results;
}
