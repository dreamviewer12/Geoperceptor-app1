package abcd.com.databaseauto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 04-10-2017.
 */

public class ApiError {
    @Expose
    @SerializedName("error message")
    public String message;
    @Expose
    @SerializedName("status")
    public String status;
    public ApiError(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
