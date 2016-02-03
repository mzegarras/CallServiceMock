package net.msonic.callservices;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);


        rv = (RecyclerView)view.findViewById(R.id.rv);

        return view;
    }








    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

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

            Resumen_Adapter resumen_Adapter = new Resumen_Adapter(response);
            rv.setAdapter(resumen_Adapter);

        }

    }



    public class Resumen_Adapter extends RecyclerView.Adapter<ResumenViewHolder> {

        private final List<Menu> detalle;

        public Resumen_Adapter(List<Menu> detalle) {
            this.detalle = detalle;
        }


        @Override
        public ResumenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_menu_content, parent, false);
            ResumenViewHolder pvh = new ResumenViewHolder(v);

            return pvh;

        }

        @Override
        public void onBindViewHolder(ResumenViewHolder holder, int position) {


            Menu menu = detalle.get(position);

            holder.txtTitle.setText(menu.titulo);

            Picasso
                    .with(getContext())
                    .load(menu.urlFoto)
                    .into(holder.imgIcon);

        }

        @Override
        public int getItemCount() {
            if(detalle==null)
                return 0;
            else{
                return detalle.size();
            }
        }

    }
    public class ResumenViewHolder extends RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView txtTitle;

        ResumenViewHolder(View itemView) {
            super(itemView);

            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);


        }

    }
}
