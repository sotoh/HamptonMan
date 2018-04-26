package com.example.spect.truehampton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.spect.truehampton.Models.RequestSingleton;
import com.example.spect.truehampton.clases.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Reservar reservarFragment;
    Habitacion habitacionFragment;
    Servicio servicioFragment;
    EditarTelefonosTarjeta fragment_editarT;
    Cliente cliente;
    ImageView per;
    String url_get = "http://hampton.uttsistemas.com/info";
    Boolean doubleTap = false;
    int idcliente;
    Button reservar, habitacion, servicio, ubicacion;
    private CustomerTask mAuthTask = null;
    private View mProgressView;
    private TextView navName;
    private TextView navEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.Fragment fragment=null;
        Boolean frag_select=false;
        if (id == R.id.nav_editard) {
            fragment = new EditarDireccion();
            Bundle  bundle= new Bundle();
            bundle.putInt("id",idcliente);
            fragment.setArguments(bundle);
            frag_select =true;
            // Handle the camera action
        } else if (id == R.id.nav_editart) {
            fragment = new EditarTelefonosTarjeta();
            Bundle  bundle= new Bundle();
            bundle.putInt("id",idcliente);
            frag_select=true;
            fragment.setArguments(bundle);


        } else if (id == R.id.nav_reservas) {

        } else if (id == R.id.nav_habitaciones) {

        } else if (id == R.id.nav_ser) {

        }
        if (frag_select){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
/*
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
*/
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            // mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    private void getCustomerInfo() {
        if (mAuthTask != null) {
            return;
        }
        boolean cancel = false;
        View focusView = null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",idcliente);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,url_get,jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                cliente = gson.fromJson(response.toString(),Cliente.class);
                mAuthTask = new CustomerTask(cliente);
                mAuthTask.execute((Void) null);
                navName.setText(mAuthTask.usuarioCliente.getNombre()+" "+mAuthTask.usuarioCliente.getApellidoPaterno());
                navEmail.setText(mAuthTask.usuarioCliente.getUsuario().getEmail());
                //navName.setText(mAuthTask.usuarioCliente.getNombre()+" "+mAuthTask.usuarioCliente.getApellidoPaterno());
                // navEmail.setText(mAuthTask.usuarioCliente.getUsuario().getEmail());
                //public Usuario(int id, String email, String create, String update, String delete, Cliente cliente, String contrasena) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "" + error);
                mAuthTask = new CustomerTask(cliente);
                mAuthTask.execute((Void) null);
            }
        });
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonRequest);

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

        }
    }
    public class CustomerTask extends AsyncTask<Void, Void, Boolean> {

        //private final String mEmail;
        //private final String mPassword;
        private Cliente usuarioCliente;
/*
        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }*/

        public CustomerTask(Cliente usuario) {
            this.usuarioCliente = usuario;
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

            if(usuarioCliente== null)
            {
                Toast.makeText(getApplicationContext(), "Love", Toast.LENGTH_SHORT).show();
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
            }else if(usuarioCliente == null)
            {
                // mEmailView.setError(getString(R.string.error_no_email));
                //mEmailView.requestFocus();
                //Toast.makeText(drawerhampton.this, "Error al cargar", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
