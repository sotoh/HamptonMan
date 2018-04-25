package com.example.spect.truehampton.Models;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by spect on 14/03/2018.
 */

public class RequestSingleton {
    private static RequestSingleton requestSingleton;
     private static Context context;
    RequestQueue requestQueue;
    /*
    * private MySingleTon(Context context){
        this.mctx=context;
        this.requestQueue=getRequestQueue();

    }
    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }*/
    public static synchronized RequestSingleton getInstance(Context context) {
        if (requestSingleton == null) {
            requestSingleton = new RequestSingleton(context);
        }
        return requestSingleton;
    }
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

   private RequestSingleton(Context context) {
        this.context = context;
        this.getRequestQueue();
    }

    public <JSONObject> void addToRequestQueue(Request<JSONObject> req) {
        getRequestQueue().add(req);
    }
}
