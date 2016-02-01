
Cuando iniciamos un proyecto móvil, los services también están en desarrollo. Sin embargo, es común, que se definan las tramas de request/response además del formato (json/xml) o también algunas cabeceras de las peticiones.

Por ejemplo, vamos a suponer que definen lo siguiente:

El servicio de loging es POST

Request:
```
{"pwd":"admin","user":"password"}
```
Response:

Si el sts es 1 deberá mostrar un mensajes de error por el contrario, si el status es 0, deberá ingresar al menú principal. Entonces las tramas de response pueden ser:

```
{"sts":1,"des":"Usuario o password incorrecto."}
```

```
{"sts":0,"des":"Bienvenido usuario mzegarra"}
```

Podemos usar utilizar Mock, SoapUI u otros frameworks para crear servicios mocks con tramas en duro que permitarn avanzar con el desarrollo del APP. Otra alternativa, de manera temporal, es utilizar dentro APP tramas en duro, específicamente en nuestra carpeta "assets".

LoginRequest_OK.txt
LoginRequest_UserPwdIncorrect.txt

Adicionalmente, me gustaría avanzar con el mapear los objetos de request /response y cabeceras:

```
02-01 00:22:52.887 31954-32713/net.msonic.callservices D/Retrofit: ---> HTTP POST http://www.url.com/rest/v1/account/Logon
02-01 00:22:52.887 31954-32713/net.msonic.callservices D/Retrofit: Accept: application/json
02-01 00:22:52.887 31954-32713/net.msonic.callservices D/Retrofit: Content-Type: application/json
02-01 00:22:52.887 31954-32713/net.msonic.callservices D/Retrofit: Content-Length: 22
02-01 00:22:52.897 31954-32713/net.msonic.callservices D/Retrofit: {"pwd":"c","user":"f"}
02-01 00:22:52.897 31954-32713/net.msonic.callservices D/Retrofit: ---> END HTTP (22-byte body)
02-01 00:22:52.897 31954-32713/net.msonic.callservices D/net.msonic.callservices.ws.mock.LoginServiceMock: fetching uri: http://www.url.com/rest/v1/account/Logon
02-01 00:22:52.897 31954-32713/net.msonic.callservices D/Retrofit: <--- HTTP 200 http://www.url.com/rest/v1/account/Logon (1ms)
02-01 00:22:52.897 31954-32713/net.msonic.callservices D/Retrofit: ﻿{"sts":1,"des":"Usuario o password incorrecto."}
02-01 00:22:52.897 31954-32713/net.msonic.callservices D/Retrofit: <--- END HTTP (51-byte body)


Para este ejemplo, vamos utilizar retrofit. Observen la línea "setClient" con el valor "LoginServiceMock", en esta clase leemos el response de los archivos de textos y evitamos que inicia la llamada http. 

	```
	//.setClient(new OkClient(okHttpClient))
    .setClient(new LoginServiceMock(context))


Por el contrario, para realizar la petición habilitamos la línea:
```
	.setClient(new OkClient(okHttpClient))
    //.setClient(new LoginServiceMock(context))


Ejemplo completo.
```
    RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(requestInterceptor)
                .setConverter(new GsonConverter(gson))

                .setEndpoint(HOST_SERVICE)
                //.setClient(new OkClient(okHttpClient))
                .setClient(new LoginServiceMock(context))
                .build();

En nuestro fragment o activity, la llamada al service es transparente no se entera si hace la llamda http o si lee un archivo.


```
 LoginServiceTask loginServiceTask = new LoginServiceTask();

                loginServiceTask.execute(new String[]{txtUser.getText().toString().trim(),
                        txtPwd.getText().toString().trim()});

```

```
    private class LoginServiceTask extends AsyncTask<String, Void, LoginResponse> {


        @Override
        protected LoginResponse doInBackground(String... params) {
            LoginProxy loginProxy = new LoginProxy(getContext());

            LoginResponse loginResponse =  loginProxy.login(params[0], params[1]);

            return loginResponse;
        }

        @Override
        protected void onPostExecute(LoginResponse loginResponse) {
            //TextView txt = (TextView) findViewById(R.id.output);
            //txt.setText("Executed"); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you


            if(loginResponse.status==0){
                Log.d(TAG,"Reemplazar fragment");
            }else{
                Toast.makeText(getContext(),
                                loginResponse.descripcion, Toast.LENGTH_SHORT).show();
            }



        }

    }
