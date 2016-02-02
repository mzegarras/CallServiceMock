package net.msonic.callservices.ws.contract;

import net.msonic.callservices.ws.dto.Menu;
import net.msonic.callservices.ws.dto.LoginRequest;
import net.msonic.callservices.ws.dto.LoginResponse;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by manuelzegarra on 31/01/16.
 */
public interface LoginService {

    @POST("/account/Logon")
    LoginResponse login(@Body LoginRequest request);


    @POST("/noticia/{categoria}")
    List<Menu> list(@Path("categoria") int categoria, @Header("SESSION_KEY") String sessionKey);

}
