package net.msonic.callservices;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
}
