package abcd.com.databaseauto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nitisha Agarwal on 10-10-2017.
 */

public class GeoLatLong {
    @Expose
    @SerializedName("lat")
    public double lat;
    @Expose
    @SerializedName("lng")
    public double longi ;
}
