package net.msonic.callservices.ws.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuelzegarra on 31/01/16.
 */
public class LoginRequest {


    @Expose()
    @SerializedName("user")
    public String usuario;

    @Expose()
    @SerializedName("pwd")
    public String password;

}
