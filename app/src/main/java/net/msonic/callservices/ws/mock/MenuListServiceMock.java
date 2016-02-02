package net.msonic.callservices.ws.mock;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by manuelzegarra on 2/02/16.
 */
public class MenuListServiceMock implements Client {


    private static String TAG = LoginServiceMock.class.getCanonicalName();

    Context context;

    public MenuListServiceMock(Context context)
    {
        this.context=context;
    }

    @Override
    public Response execute(Request request) throws IOException {
        Uri uri = Uri.parse(request.getUrl());

        Log.d(TAG, "fetching uri: " + uri.toString());

        String filename="MenuList_OK.txt";
        //String filename="LoginRequest_UserPwdIncorrect.txt";


        InputStream is = context.getAssets().open(filename);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String responseString = new String(buffer);
        return new Response(request.getUrl(), 200, filename, Collections.EMPTY_LIST, new TypedByteArray("application/json", responseString.getBytes()));
    }
}
