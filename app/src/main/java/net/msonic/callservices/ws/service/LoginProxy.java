package net.msonic.callservices.ws.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import net.msonic.callservices.ws.contract.LoginService;
import net.msonic.callservices.ws.dto.LoginRequest;
import net.msonic.callservices.ws.dto.LoginResponse;
import net.msonic.callservices.ws.mock.LoginServiceMock;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by manuelzegarra on 31/01/16.
 */
public class LoginProxy {

    private static int TIMEOUT=90;
    private static String HOST_SERVICE="http://www.url.com/rest/v1/";

    private final Context context;


    public LoginProxy(Context context) {
        this.context = context;
    }

    public LoginResponse login(String usuario,String password){
        final LoginService service = getRestAdapter().create(LoginService.class);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.password = password;
        loginRequest.usuario = usuario;

        return service.login(loginRequest);

    }

    public RestAdapter getRestAdapter(){

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
                request.addHeader("Content-type", "application/json");
            }
        };

        Gson gson = new GsonBuilder().create();


        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(requestInterceptor)
                .setConverter(new GsonConverter(gson))

                .setEndpoint(HOST_SERVICE)
                //.setClient(new OkClient(okHttpClient))
                .setClient(new LoginServiceMock(context))
                .build();

        return restAdapter;
    }
}
