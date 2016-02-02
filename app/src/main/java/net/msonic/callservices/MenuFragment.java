package net.msonic.callservices;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.msonic.callservices.ws.dto.Menu;
import net.msonic.callservices.ws.service.MenuListProxy;

import java.util.List;

/**
 * Created by manuelzegarra on 1/02/16.
 */
public class MenuFragment extends Fragment {

    private static String TAG = MainActivityFragment.class.getCanonicalName();

    public MenuFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListMenuTask listMenuTask = new ListMenuTask();
        listMenuTask.execute("2");
    }

    private class ListMenuTask extends AsyncTask<String, Void, List<Menu>> {


        @Override
        protected List<Menu> doInBackground(String... params) {
            MenuListProxy menuListProxy = new MenuListProxy(getContext());

            List<Menu> response =  menuListProxy.list(Integer.parseInt(params[0]));

            return response;
        }

        @Override
        protected void onPostExecute(List<Menu> response) {
            //TextView txt = (TextView) findViewById(R.id.output);
            //txt.setText("Executed"); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you






        }

    }
}
