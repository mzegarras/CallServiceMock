package net.msonic.callservices;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.msonic.callservices.ws.dto.LoginResponse;
import net.msonic.callservices.ws.service.LoginProxy;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static String TAG = MainActivityFragment.class.getCanonicalName();

    public MainActivityFragment() {
    }

    EditText txtUser,txtPwd;
    Button btnAccept;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        txtUser = (EditText)view.findViewById(R.id.txtUser);
        txtPwd = (EditText)view.findViewById(R.id.txtPwd);
        btnAccept = (Button)view.findViewById(R.id.btnAccept);


        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginServiceTask loginServiceTask = new LoginServiceTask();

                loginServiceTask.execute(new String[]{txtUser.getText().toString().trim(),
                        txtPwd.getText().toString().trim()});



            }
        });

        return view;
    }

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
}
