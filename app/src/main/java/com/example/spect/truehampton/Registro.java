package com.example.spect.truehampton;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sapereaude.maskedEditText.MaskedEditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registro.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registro extends Fragment implements View.OnClickListener, Response.ErrorListener,Response.Listener<JSONObject> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText nombre, paterno, materno, correo, contrasena;
    MaskedEditText fnaciniento;
    Button mandar;
    String URL_POST = "http://192.168.43.248/myapp/hamptonweb/public/insert";
    private String mysqlTargetIn;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Registro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro newInstance(String param1, String param2) {
        Registro fragment = new Registro();
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
        View view= inflater.inflate(R.layout.fragment_registro, container, false);
        correo = view.findViewById(R.id.correo);
        contrasena = view.findViewById(R.id.contrasena);
        nombre = view.findViewById(R.id.nombre);
        paterno = view.findViewById(R.id.apaterno);
        materno = view.findViewById(R.id.amaterno);
        fnaciniento = (MaskedEditText) view.findViewById(R.id.fnacimiento);
        mandar = view.findViewById(R.id.mandar);
        mandar.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mandar:
                JSONObject object = new JSONObject();

                try {
                    Date mysqlDateIn = null;
                    mysqlDateIn = new SimpleDateFormat("dd-MM-yyyy").parse(fnaciniento.getText().toString());
                    //arrival = mysqlDateIn;
                    String mysqlTarget = new SimpleDateFormat("yyyy-MM-dd").format(mysqlDateIn);
                    //this.mysqlTargetIn = mysqlTarget;
                    object.put("nombre", nombre.getText().toString());
                    object.put("apellidop", paterno.getText().toString());
                    object.put("apellidom", materno.getText().toString());
                    object.put("fnacimiento", mysqlTarget);
                    object.put("correo", correo.getText().toString());
                    object.put("contrasena", contrasena.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_POST, object, this, this);
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestSingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
                break;

        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Registro fallido", Toast.LENGTH_SHORT).show();
        Intent login = new Intent();

    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(),LoginActivity.class);
        startActivity(intent);

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
