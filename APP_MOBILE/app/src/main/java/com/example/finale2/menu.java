package com.example.finale2;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class menu extends AppCompatActivity {
    ImageView scan;
    LinearLayout plan;
    LinearLayout info;
    TextView user;
    String name;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceulle);
        scan =findViewById(R.id.scan);
        plan=findViewById(R.id.plan_j);
       // user=findViewById(R.id.username);
        info=findViewById(R.id.info);
        Intent intent = getIntent();
        //name = intent.getStringExtra("name");
        id=intent.getStringExtra("id");


        plan.setOnClickListener(v->{
            Intent ab = new Intent(this,planing.class);
            startActivity(ab);
        });
        //////////:Scan le QRCODE//////////////////////:
        scan.setOnClickListener(V->{
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(false);
            integrator.setPrompt("Scan a QR Code");
            integrator.setBeepEnabled(true);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrator.setCaptureActivity(Capture.class);
            integrator.initiateScan();
        });
        //////////: display les information de utilisateur/////////////
        info.setOnClickListener(v->{
            Intent ab = new Intent(menu.this,donner.class);
            ab.putExtra("id",id);
            startActivity(ab);
        });
    }
    ///////////afficher la page de succes apré le Scan de QRCODE/////::::::::
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                String qrResult = result.getContents();
                new visite().execute(qrResult);

            }
        }
    }
    ///////////la fonction de validation visite apré le Scan de QRCODE///////////
    public class visite extends AsyncTask<String, Void, String> {
        AlertDialog.Builder builder = new AlertDialog.Builder(menu.this);
        AlertDialog alertDialog;
        protected void onPreExecute() {
            builder.setTitle("Processing...");
            builder.setMessage("Please wait while the task is being executed.");
            builder.setCancelable(false);
            alertDialog = builder.create();
            alertDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String ab=params[0];
            String url = "https://finable-increase.000webhostapp.com/stage/chek_visite.php";
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                String mes=jsonObject.getString("message");
                                if(mes.equals("visite")){
                                    alertDialog.dismiss();
                                    String dat=jsonObject.getString("datef");
                                    Intent intent = new Intent(menu.this, done.class);
                                    intent.putExtra("mes2", dat);
                                    intent.putExtra("id", ab);
                                    startActivity(intent);

                                }
                                if(mes.equals("deja")){
                                    alertDialog.dismiss();
                                    String dat=jsonObject.getString("datef");
                                    showAlertDialog("equipment deja vue la prochaine visite c'est au "+dat);
                                }
                                if(mes.equals("retard")){
                                    alertDialog.dismiss();
                                    showAlertDialog("vous ete en retard");
                                }
                                if(mes.equals("unknown")){
                                    alertDialog.dismiss();
                                    showAlertDialog("Erreur base donné");
                                }
                                if(mes.equals("Erreur")){
                                    alertDialog.dismiss();
                                    showAlertDialog("Id Incorrect");
                                }
                            }  catch (JSONException e) {
                                alertDialog.dismiss();
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error
                            alertDialog.dismiss();
                            showAlertDialog("Probléme Accé a la base donné ou Internet");
                            //Toast.makeText(MainActivity.this, "Failed check connection", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", ab);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(menu.this);
            queue.add(request);
            return null;
        }

    }
    private void showAlertDialog(String mes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mes)
                .setPositiveButton("OK", (dialog, which) -> {
                    // Do something when the OK button is clicked
                    Toast.makeText(menu.this, "OK button clicked", Toast.LENGTH_SHORT).show();
                })
                .setCancelable(false)
                .show();
    }
}
