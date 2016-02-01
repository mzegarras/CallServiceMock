package net.msonic.callservices.ws.contract;

import net.msonic.callservices.ws.dto.LoginRequest;
import net.msonic.callservices.ws.dto.LoginResponse;

import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by manuelzegarra on 31/01/16.
 */
public interface LoginService {

    @POST("/account/Logon")
    LoginResponse login(@Body LoginRequest request);

}
