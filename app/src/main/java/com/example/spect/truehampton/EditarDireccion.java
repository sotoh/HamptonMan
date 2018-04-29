package com.example.spect.truehampton;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.spect.truehampton.Models.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditarDireccion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditarDireccion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarDireccion extends Fragment implements View.OnClickListener,
        Response.Listener<JSONObject>,
        Response.ErrorListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String valort;
    EditText call,numcal,ciudad,pais,estado,cp;
    Button save;
    String Url_UP="http://hampton.uttsistemas.com/updateAddress";
    String url_get="http://hampton.uttsistemas.com/address";
    int id;
    JSONObject jsonObject;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EditarDireccion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditarDireccion.
     */
    // TODO: Rename and change types and number of parameters
    public static EditarDireccion newInstance(String param1, String param2) {
        EditarDireccion fragment = new EditarDireccion();
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
        View view= inflater.inflate(R.layout.fragment_editar_direccion, container, false);
        call=(EditText)view.findViewById(R.id.calle);
        numcal =(EditText)view.findViewById(R.id.numcalle);
        ciudad=(EditText)view.findViewById(R.id.ciudad);
        pais=(EditText)view.findViewById(R.id.pais);
        estado=(EditText)view.findViewById(R.id.estado);
        cp=(EditText)view.findViewById(R.id.codigop);
        id= getArguments().getInt("id");
        save = view.findViewById(R.id.gg);
        save.setOnClickListener(this);

        ver_cliente();
        return view;
    }
    public void ver_cliente() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url_get, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestSingleton.getInstance(getContext()).addToRequestQueue(jsonRequest);
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
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.gg:
            {
                JSONObject jsonObject= new JSONObject();
                try {
                    jsonObject.put("calle", call.getText().toString());
                    jsonObject.put("ncalle", numcal.getText().toString());
                    jsonObject.put("ciudad", ciudad.getText().toString());
                    jsonObject.put("estado", estado.getText().toString());
                    jsonObject.put("pais", pais.getText().toString());
                    jsonObject.put("codigopostal", cp.getText().toString());
                    jsonObject.put("idcliente",id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,Url_UP,jsonObject,this,
                        this);
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES
                        ,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestSingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), "Cambios Sin Guardar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getActivity(), "Cambios Exitosos", Toast.LENGTH_SHORT).show();
    }
    public  void valor(int v){
        id=v;
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
