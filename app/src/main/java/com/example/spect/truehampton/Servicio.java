package com.example.spect.truehampton;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.spect.truehampton.Models.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Servicio.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Servicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Servicio extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView titulo;
    Button comida, estacionamiento, turista;
    RequestQueue requestQueue;
    String s_id;
    int dato;
    final String URL_POST = "http://192.168.43.248/myapp/hamptonweb/public/servicesalt";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Servicio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Servicio.
     */
    // TODO: Rename and change types and number of parameters
    public static Servicio newInstance(String param1, String param2) {
        Servicio fragment = new Servicio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_servicio, container, false);

        titulo = view.findViewById(R.id.textView2);
        comida = view.findViewById(R.id.comida);
        estacionamiento = view.findViewById(R.id.esta);
        turista = view.findViewById(R.id.turismo);
        dato = getArguments().getInt("id");


        comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
                dialogo1.setTitle("Reservar");
                dialogo1.setMessage("¿ Desea reservar una mesa en el restaurante ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                        s_id = "2";

                        requestQueue = RequestSingleton.getInstance(getContext()).getRequestQueue();
                        JSONObject object = new JSONObject();
                        try {


                            int ser= Integer.valueOf(s_id.replaceAll("[\\D]",""));
                            int dat = Integer.valueOf(dato);
                            object.put("cid", dat);
                            object.put("sid", ser);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_POST, object,Servicio.this,Servicio.this);
                        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                        requestQueue.add(jsonObjectRequest);



                        AlertDialog.Builder dialogo2 = new AlertDialog.Builder(getContext());
                        dialogo2.setTitle("Hecho");
                        dialogo2.setMessage("Fue reservada una mesa");
                        dialogo2.show();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Toast.makeText(getContext(), "No se realizo ninguna reservacion de mesa ", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogo1.show();
            }
        });
        estacionamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
                dialogo1.setTitle("Estacionamiento");
                dialogo1.setMessage("¿ Desea reservar un espacio en el estacionamiento ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        s_id = "4";
                        requestQueue = RequestSingleton.getInstance(getContext()).getRequestQueue();

                        JSONObject object = new JSONObject();
                        try {
                            int ser= Integer.valueOf(s_id.replaceAll("[\\D]",""));
                            int dat = Integer.valueOf(dato);
                            object.put("cid", dat);
                            object.put("sid", ser);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_POST, object,Servicio.this,Servicio.this);
                        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        requestQueue.add(jsonObjectRequest);


                        AlertDialog.Builder dialogo2 = new AlertDialog.Builder(getContext());
                        dialogo2.setTitle("Hecho");
                        dialogo2.setMessage("El espacio A1 esta reservado para su vehiculo");
                        dialogo2.show();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Toast.makeText(getContext(), "No cuenta con un espacio de estacionmiento reservado", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogo1.show();
            }
        });
        turista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hsimple = new Intent(getContext(), DestinosTuristicos.class);
                startActivity(hsimple);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
