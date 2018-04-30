package com.example.spect.truehampton;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.spect.truehampton.Models.RequestSingleton;
import com.example.spect.truehampton.clases.Habitacion;
import com.example.spect.truehampton.clases.HabitacionReserva;
import com.example.spect.truehampton.clases.Usuario;
import com.example.spect.truehampton.dummy.DummyContent;
import com.example.spect.truehampton.dummy.DummyContent.DummyItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HabitacionesFragment extends Fragment implements MyHabitacionesRecyclerViewAdapter.SendData {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    int idReserva;
    private RecyclerView myRecyclerView;
    private MyHabitacionesRecyclerViewAdapter myHabitacionesRecyclerViewAdapter;
    private List<Habitacion> habitacionList = new ArrayList<>();
    private List<HabitacionReserva> reservaList = new ArrayList<>();
    private Button button;
    String url_get = "http://192.168.0.20/myapp/hamptonweb/public/addrooms";
    int nhabitaciones;

    Button btnNext;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HabitacionesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HabitacionesFragment newInstance(int columnCount) {
        HabitacionesFragment fragment = new HabitacionesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habitaciones_list, container, false);
        idReserva = getArguments().getInt("idreserva");
        nhabitaciones = getArguments().getInt("numero");
        button = (Button) view.findViewById(R.id.idbtnFinalize);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reservaList.size()== nhabitaciones)
                {
                    saveRooms();
                    Fragment fragment = new MisPagos();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenido, fragment).addToBackStack("Menu").commit();

                }
                else {
                    Toast.makeText(getContext(), "Agregue todas las Habitaciones", Toast.LENGTH_SHORT).show();
                }

            }
        });
        // Set the adapter
        /*generate(nhabitaciones);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            myHabitacionesRecyclerViewAdapter = new MyHabitacionesRecyclerViewAdapter(habitacionList,this);
            myHabitacionesRecyclerViewAdapter.setProgressBar((ProgressBar) ((Toolbar)getActivity().findViewById(R.id.toolbar)).findViewById(R.id.home_progress));
            recyclerView.setAdapter(myHabitacionesRecyclerViewAdapter);
        }*/
        myRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        //toolbar.findViewById(R.id.home_progress);
        generate(nhabitaciones);
        myHabitacionesRecyclerViewAdapter = new MyHabitacionesRecyclerViewAdapter(habitacionList,this);
        myHabitacionesRecyclerViewAdapter.setProgressBar((ProgressBar) ((Toolbar)getActivity().findViewById(R.id.toolbar)).findViewById(R.id.home_progress));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        myRecyclerView.setLayoutManager(manager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        myRecyclerView.setAdapter(myHabitacionesRecyclerViewAdapter);

        return view;
    }
    private void saveRooms(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
       JSONObject single = new JSONObject();
        try {
            jsonObject.put("reserva",idReserva);
            for (int i = 0; i < nhabitaciones; i++) {
                single.put("tipo",reservaList.get(i).getTipoHabitacion());
                single.put("personas",reservaList.get(i).getHuespedes());
                jsonArray.put(single);
            }
            jsonObject.put("habitaciones",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,url_get,jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               // Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                //usuario = gson.fromJson(response.toString(),Usuario.class);
                Toast.makeText(getContext(), "Transaccion Exitosa", Toast.LENGTH_SHORT).show();
                Fragment fragment = new MisPagos();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.contenido, fragment).commit();
                fragmentTransaction.addToBackStack("Menu");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "" + error);
            }
        });
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestSingleton.getInstance(getContext()).addToRequestQueue(jsonRequest);
    }
    private void generate(int nhabitaciones)
    {
        if(this.habitacionList.isEmpty()) {
            for (int i = 0; i < nhabitaciones; i++) {
                habitacionList.add(new Habitacion());
            }
        }
        else {
            this.habitacionList.clear();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void send(HabitacionReserva item) {
            reservaList.add(item);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

}
