package com.example.spect.truehampton;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.spect.truehampton.Models.RequestSingleton;
import com.example.spect.truehampton.clases.Reserva;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.sapereaude.maskedEditText.MaskedEditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Reservar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Reservar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reservar extends Fragment implements View.OnClickListener {
    private MaskedEditText dateIn, dateOut;
    private TextView nhab;
    private EditText timeIn, timeOut;
    private Button register;
    private String mysqlTargetIn, mysqlTargetOut;
    public Reserva reserva;
    int hab;
    private View mProgressView;
    private View mLoginFormView;
    private CheckBox checkBox;

    Date arrival = null, departure= null;
    String url_get = "http://192.168.0.20/myapp/hamptonweb/public/booking";
    private int idClient;
    private BookingTask bookingTask = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_reservar, container, false);
      //  fragment = new HabitacionFragment();
        //fragment = new Reservar();
        register = viewFragment.findViewById(R.id.btningre);
        dateIn = (MaskedEditText) viewFragment.findViewById(R.id.Efecha);
        dateOut = (MaskedEditText) viewFragment.findViewById(R.id.Ehora);
        timeOut = (EditText) viewFragment.findViewById(R.id.Sh);
        timeIn = (EditText) viewFragment.findViewById(R.id.Eh);

        mLoginFormView = viewFragment.findViewById(R.id.relativeLayout3);
        mProgressView = viewFragment.findViewById(R.id.login_progress);
        dateIn.setText(setActualDate());
        nhab = (TextView) viewFragment.findViewById(R.id.nohab);
        checkBox = (CheckBox) viewFragment.findViewById(R.id.timeCheckBox);
        register.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                { timeIn.setEnabled(true);}
                else
                    timeIn.setEnabled(false);
            }
        });
        idClient = getArguments().getInt("id");
        dateOut.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    Date mysqlDateIn = null;
                    Date mysqlDateOut = null;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        mysqlDateIn = simpleDateFormat.parse(dateIn.getText().toString());
                        mysqlDateOut = simpleDateFormat.parse(dateOut.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace(); }
                    if(dateOut.length() == 10 && dateIn.length() == 10)
                    {
                        //Toast.makeText(getActivity(), "Entro", Toast.LENGTH_SHORT).show();
                        long n = getDays(mysqlDateIn,mysqlDateOut);
                        nhab.setText(String.valueOf(n));
                    }
                    // arrival = mysqlDateIn;
                    //departure = mysqlDateOut;
                }
            }
        });
        return viewFragment;
    }
    private String setActualDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        Date now = Calendar.getInstance().getTime();
        return sdf.format(now);
    }
    private long getDays(Date arrival, Date departure)
    {
        long diff = departure.getTime() - arrival.getTime();
        long militodays = 86400000;
        long r = diff / militodays;
        return r;
    }
    private void doDates() {
        Date mysqlDateIn = null;
        try {
            mysqlDateIn = new SimpleDateFormat("dd-MM-yyyy").parse(dateIn.getText().toString());
            arrival = mysqlDateIn;
            String mysqlTarget = new SimpleDateFormat("yyyy-MM-dd").format(mysqlDateIn);
            this.mysqlTargetIn = mysqlTarget;
            Date mysqlDateOut = new SimpleDateFormat("dd-MM-yyyy").parse(dateOut.getText().toString());
            departure = mysqlDateOut;
            String mysqlTargetOut = new SimpleDateFormat("yyyy-MM-dd").format(mysqlDateOut);
            this.mysqlTargetOut = mysqlTargetOut;
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    private void bookingHotel()
    {
        if(bookingTask != null)
        {return ;}
        View focusView = null;
        dateIn.setError(null);
        dateOut.setError(null);
        timeOut.setError(null);
        Boolean cancel = false;
        doDates();
        JSONObject jsonObject = new JSONObject();
        String check;
        if(checkBox.isChecked()){
            try {
                check = "1";
                //doDates();
                jsonObject.put("llegada",mysqlTargetIn);
                jsonObject.put("partida",mysqlTargetOut);
                jsonObject.put("cliente",idClient);
                jsonObject.put("horadiferente",check);
                jsonObject.put("nuevahora",timeIn.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else
        {
            try {
                check = "0";
                jsonObject.put("llegada",mysqlTargetIn);
                jsonObject.put("partida",mysqlTargetOut);
                jsonObject.put("cliente",idClient);
                jsonObject.put("horadiferente",check);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,url_get,jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                reserva = gson.fromJson(response.toString(),Reserva.class);
                bookingTask = new BookingTask(reserva);
                bookingTask.execute((Void) null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "" + error);
                bookingTask = new BookingTask(reserva);
                bookingTask.execute((Void) null);
            }
        });
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestSingleton.getInstance(getActivity()).addToRequestQueue(jsonRequest);
        if (arrival == null) {
            dateIn.setError(getString(R.string.error_arrival));
            focusView = dateIn;
            cancel = true;
        }
        //Toast.makeText(getContext(), ""+departure.toString(), Toast.LENGTH_SHORT).show();
        if (departure ==null || departure.toString().equals("2010-12-31")) {
            dateOut.setError(getString(R.string.error_departure));
            focusView = dateOut;
            cancel = true;
        }
        //

        if (TextUtils.isEmpty(timeOut.getText().toString())) {
            timeOut.setError(getString(R.string.error_hab));
            focusView = timeOut;
            cancel = true;
        }
        if(cancel)
        {
            focusView.requestFocus();
        }
        else
        {

            showProgress(true);
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            mProgressView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
            if(show)
            {
                dateOut.setEnabled(false);
                checkBox.setEnabled(false);
                timeOut.setEnabled(false);
                dateIn.setEnabled(false);
                register.setEnabled(false);
                timeIn.setEnabled(false);
                nhab.setEnabled(false);
            }
            else
            {
                dateIn.setEnabled(true);
                dateOut.setEnabled(true);
                checkBox.setEnabled(true);
                timeOut.setEnabled(true);
                nhab.setEnabled(true);
                register.setEnabled(true);
                if(checkBox.isChecked())
                    timeIn.setEnabled(true);

            }
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btningre:
                //Intent intent = new Intent();
                bookingHotel();

                break;
        }
    }
    public class BookingTask extends AsyncTask<Void, Void, Boolean> {
        private Reserva reserva;
        public BookingTask(Reserva reserva) {
            this.reserva = reserva;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            try {
                // Simulate network access.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return true;
            }
            if(reserva != null)
            {
                return true;
            }

            return false;

            // TODO: register the new account here.
        }
        /*Aqui es donde se mueve a la siguiente actividad  y hay que cambiar el booleaon*/
        @Override
        protected void onPostExecute(final Boolean success) {
            bookingTask = null;
            showProgress(false);

            if (success) {
                Fragment fragment = new HabitacionesFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenido, fragment).addToBackStack("Menu").commit();
                hab = Integer.parseInt(timeOut.getText().toString());
                Bundle bundle = new Bundle();
                //bundle.putString("id",reserva.getId_re());
                bundle.putInt("idreserva",reserva.getId_re());
                bundle.putInt("numero",hab);
                fragment.setArguments(bundle);
            }else if(reserva == null)
            {
                Toast.makeText(getActivity(), "Error ", Toast.LENGTH_SHORT).show();
            }
            else {

            }
        }
        @Override
        protected void onCancelled() {
            bookingTask = null;
            showProgress(false);
        }
    }



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Reservar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reservar.
     */
    // TODO: Rename and change types and number of parameters
    public static Reservar newInstance(String param1, String param2) {
        Reservar fragment = new Reservar();
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
