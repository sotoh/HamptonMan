package com.example.spect.truehampton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.Fragment;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.text.method.QwertyKeyListener;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.spect.truehampton.Models.RequestSingleton;
import com.example.spect.truehampton.clases.Cliente;
import com.example.spect.truehampton.clases.HabitacionReserva;
import com.example.spect.truehampton.dummy.DummyContent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        EditarDireccion.OnFragmentInteractionListener,
        EditarTelefonosTarjeta.OnFragmentInteractionListener,
        MisPagos.OnFragmentInteractionListener,
        ver_reservas.OnFragmentInteractionListener ,
        MenuHome.OnFragmentInteractionListener,
        Reservar.OnFragmentInteractionListener,
        Servicio.OnFragmentInteractionListener,
        HabitacionesFragment.OnListFragmentInteractionListener,
CuartoFragment.OnListFragmentInteractionListener{
    int idcliente;
    Cliente cliente;
    Toolbar toolbar;
    String url_get = "http://192.168.0.20/myapp/hamptonweb/public/info";
    CustomerTask mAuthTask = null;
    public ProgressBar mProgressView;

    private TextView navName;
    private TextView navEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        idcliente = getIntent().getIntExtra("idcliente", 0);
        getCustomerInfo();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setHome();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mProgressView = toolbar.findViewById(R.id.home_progress);
//        mProgressView.setVisibility(View.VISIBLE);
        View headerView = navigationView.getHeaderView(0);
        navName = (TextView) headerView.findViewById(R.id.clientName);
        navEmail = (TextView) headerView.findViewById(R.id.tvEmail);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        mProgressView = toolbar.findViewById(R.id.home_progress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            this.mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            this.mProgressView.animate().setDuration(shortAnimTime).alpha(
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
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //finish();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setHome() {
        android.support.v4.app.Fragment fragment = new MenuHome();
        Bundle bundle = new Bundle();
        bundle.putInt("idcliente", idcliente);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenido, fragment).commit();
        getSupportActionBar().setTitle("Menu");

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

        android.support.v4.app.Fragment fragment = null;
        boolean fragmenttransaction = false;

        if (id == R.id.nav_editard) {
            fragment = new EditarDireccion();
            Bundle bundle = new Bundle();
            bundle.putInt("idcliente", idcliente);
            fragment.setArguments(bundle);

            fragmenttransaction = true;

        } /*else if (id == R.id.nav_editart) {
            fragment = new EditarTelefonosTarjeta();
            Bundle bundle = new Bundle();
            bundle.putInt("idcliente", idcliente);
            fragmenttransaction = true;

        } */else if (id == R.id.nav_habitaciones) {
            fragment = new MisPagos();
            Bundle bundle = new Bundle();
            bundle.putInt("idcliente", idcliente);
            fragmenttransaction = true;

        } else if (id == R.id.nav_reservas) {
            fragment = new ver_reservas();
            Bundle bundle = new Bundle();
            bundle.putInt("idcliente", idcliente);
            fragmenttransaction = true;
        }
        //else if (id == R.id.nav_ser) {

//            fragmenttransaction=true;
        //      }
        if (fragmenttransaction) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contenido, fragment).commit();
            fragmentTransaction.addToBackStack("Menu");
            getSupportActionBar().setTitle(item.getTitle());

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void getCustomerInfo() {
        if (mAuthTask != null) {
            return;
        }
        boolean cancel = false;
        View focusView = null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", idcliente);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url_get, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                cliente = gson.fromJson(response.toString(), Cliente.class);
                mAuthTask = new CustomerTask(cliente);
                mAuthTask.execute((Void) null);
                navName.setText(mAuthTask.usuarioCliente.getNombre() + " " + mAuthTask.usuarioCliente.getApellidoPaterno());
                navEmail.setText(mAuthTask.usuarioCliente.getUsuario().getEmail());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "" + error);
                mAuthTask = new CustomerTask(cliente);
                mAuthTask.execute((Void) null);
            }
        });
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

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
                Toast.makeText(MainActivity.this, "Erro al Cargar", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
