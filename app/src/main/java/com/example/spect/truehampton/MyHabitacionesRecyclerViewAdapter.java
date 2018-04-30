package com.example.spect.truehampton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.spect.truehampton.HabitacionesFragment.OnListFragmentInteractionListener;
import com.example.spect.truehampton.Models.RequestSingleton;
import com.example.spect.truehampton.clases.Cliente;
import com.example.spect.truehampton.clases.Habitacion;
import com.example.spect.truehampton.clases.HabitacionReserva;
import com.example.spect.truehampton.dummy.DummyContent.DummyItem;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHabitacionesRecyclerViewAdapter extends RecyclerView.Adapter<MyHabitacionesRecyclerViewAdapter.ViewHolder> {

    private final List<Habitacion> mValues;

    Context context;
    private int habitacion = -1;
    private HabitacionReserva singleRoom;
   QueryTask mAuthTask = null;
   ProgressBar mProgressView;

    public void setProgressBar(ProgressBar progressBar) {
        this.mProgressView = progressBar;
    }
    ///// Interfaces

    SendData data;
    public interface SendData{public void send(HabitacionReserva item); }



    public MyHabitacionesRecyclerViewAdapter(List<Habitacion> items, SendData listener) {
        mValues = items;
        data = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_habitaciones, parent, false);
        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        /*holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
*/
        Habitacion habitacion = mValues.get(position);
        holder.title.setText("Habitacion "+ (position+1));
        holder.price.setText(habitacion.getPrecio());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String tipoHabitacion, int huespedes
                //singleRoom = new HabitacionReserva()
                HabitacionReserva habitacionReserva =new HabitacionReserva(holder.spinnRoom.getSelectedItem().toString(),(holder.nAdults.getValue()+ holder.nKids.getValue()));
                if(habitacionReserva == null)
                {
                    Toast.makeText(context, "No se puede avanzar", Toast.LENGTH_SHORT).show();
                }
                else {
                    data.send(habitacionReserva);
                    holder.nKids.setEnabled(false);
                    holder.nAdults.setEnabled(false);
                    holder.btnAdd.setEnabled(false);
                    holder.spinnRoom.setEnabled(false);
                }
            }
        });
        /*
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
       // mProgressView = toolbar.findViewById(R.id.home_progress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = ((Activity)context).getResources().getInteger(android.R.integer.config_shortAnimTime);
            if(mProgressView == null)
            {
                Toast.makeText(context, "Progress Nel perro", Toast.LENGTH_SHORT).show();
            }
            else
            {
            this.mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            this.mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });}
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public final View mView;
        private  TextView title;
        private NumberPicker nAdults;
        private NumberPicker nKids;
        private Spinner spinnRoom;
        private Button btnAdd;
        private TextView availability;
        private TextView price;
        public Habitacion mItem;
//idNHabitaciones
        String item;
        String[] roomTypes = new String[] {"Junior","Sencilla","Doble"};
        List<String> roomList = new ArrayList<>(Arrays.asList(roomTypes));
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.idRoomType);
            nAdults = (NumberPicker) view.findViewById(R.id.idAdults);
            nKids = (NumberPicker) view.findViewById(R.id.idKids);
            spinnRoom = (Spinner) view.findViewById(R.id.idComboBox);
            btnAdd = (Button) view.findViewById(R.id.idbtAdd);

            price = (TextView) view.findViewById(R.id.idPrice);
            availability = (TextView)view.findViewById(R.id.idNHab);
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,roomList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnRoom.setAdapter(adapter);
            spinnRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("tipo",spinnRoom.getSelectedItem().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                            "http://192.168.0.20/myapp/hamptonweb/public/allrooms", jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                habitacion = response.getInt("number");
                                disableItem(habitacion,spinnRoom.getSelectedItem().toString());
                                availability.setText(""+habitacion);
                                //roomTask=new RoomTask(habitacion);
                                //roomTask.execute((Void) null);
                                //setValue(response.getInt("cantidad"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Error de Conexión en Habitacion", Toast.LENGTH_SHORT).show();
                        }
                    });
                    jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);
                    //Toast.makeText(context, "N es "+ habitacion, Toast.LENGTH_SHORT).show();
                    switch (spinnRoom.getSelectedItem().toString())
                    {
                        case "Junior":price.setText("400.00");break;
                        case "Double": price.setText("500.00");break;
                        case "Sencilla":price.setText("300.00"); break;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });//Termina Botón
            nAdults.setMaxValue(4);
            nAdults.setMinValue(1);
            nKids.setMaxValue(4);
            nKids.setMinValue(1);
            /*nAdults.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                    numberPicker.getValue();
                }
            });*/
            //mView = view;
            // mIdView = (TextView) view.findViewById(R.id.item_number);
            // mContentView = (TextView) view.findViewById(R.id.content);
            /*btnAvailability.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Info Sended", Toast.LENGTH_SHORT).show();

                    JSONObject jsonObject = new JSONObject();

                }
            });*/
        }
        private void disableItem(int N,String name)
        {

            if(N==0)
            {
                switch (name)
                {//"Junior","Sencilla","Doble"
                    case "Junior":
                        roomList.remove(0);  break;
                    case "Sencilla":
                        roomList.remove(1);  break;
                    case "Doble":
                        roomList.remove(2);  break;
                }
            }
        }

        /*private void enableItem(String name)
        {
            if(roomList.size()<3){
                for (int i = 0; i < 2; i++) {
                    if()
                }}
        }*/


    }

    public class QueryTask extends AsyncTask<Void, Void, Boolean> {

        //private final String mEmail;
        //private final String mPassword;
        private HabitacionReserva reserva;
/*
        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }*/

        public QueryTask(HabitacionReserva reserva) {
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

            if(reserva== null)
            {
                //Toast.makeText(getApplicationContext(), "Love", Toast.LENGTH_SHORT).show();
                // navName.setText(usuarioCliente.getNombre()+" "+usuarioCliente.getApellidoPaterno());
                //navEmail.setText(usuarioCliente.getUsuario().getEmail());
                return false;
            }

            return false;

            // TODO: register the new account here.

        }
        /*Aqui es donde se mueve a la siguiente actividad  y hay que cambiar el booleaon*/
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                //finish();
                //intend para entrar a la siguiente actividad
                //Intent intento = new Intent(LoginActivity.this, drawerhampton.class);
                //Mandamos el id del cliente al siguiente activity
                //intento.putExtra("idcliente",usuario.getCliente_id());
                //navName.setText(usuarioCliente.getNombre()+" "+usuarioCliente.getApellidoPaterno());
                //navEmail.setText(usuarioCliente.getUsuario().getEmail());
                //Toast.makeText(drawerhampton.this, "Hay Objeto ", Toast.LENGTH_SHORT).show();
                //Toast.makeText(LoginActivity.this, ""+usuario.getEmail(), Toast.LENGTH_SHORT).show();
                //LoginActivity.this.startActivity(intento);
            }else if(reserva == null)
            {
                // mEmailView.setError(getString(R.string.error_no_email));
                //mEmailView.requestFocus();
                Toast.makeText(context, "Erro al Cargar", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
