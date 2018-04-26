package com.example.spect.truehampton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaypalDetalles extends AppCompatActivity {

    TextView txtaide, txtmonto, txtestado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal_detalles);
        txtaide = findViewById(R.id.txtaide);
        txtmonto = findViewById(R.id.txtmonto);
        txtestado = findViewById(R.id.txestado);

        Intent intent = getIntent();
        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetalles"));
            showDetails(jsonObject.getJSONObject("response"), intent.getStringExtra("PaymentMonto"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showDetails(JSONObject response, String paymentMonto) {
        try {
            txtaide.setText(response.getString("id"));
            txtmonto.setText("$" + paymentMonto);
            txtestado.setText(response.getString("state"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
