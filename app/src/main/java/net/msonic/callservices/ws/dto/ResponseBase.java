package net.msonic.callservices.ws.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuelzegarra on 31/01/16.
 */
public abstract class ResponseBase {

    @Expose()
    @SerializedName("sts")
    public int status;

    @Expose()
    @SerializedName("des")
    public String descripcion;

}
