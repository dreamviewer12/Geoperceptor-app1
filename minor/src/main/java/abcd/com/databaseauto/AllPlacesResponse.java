package abcd.com.databaseauto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by HP on 04-10-2017.
 */

public class AllPlacesResponse {
    @Expose
    @SerializedName("predictions")
    public ArrayList<GooglePlayList> predictions;
}
