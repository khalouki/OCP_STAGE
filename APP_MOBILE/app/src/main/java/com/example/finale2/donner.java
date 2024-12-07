package com.example.finale2;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class donner extends AppCompatActivity {
    String id;
    private EditText nm;
    private EditText pr;
    private EditText gm;
    private EditText tl;
    TextView n,p,te,g;
    Button sh;
    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Call the default behavior to return to the previous layout
        finish(); // Close this activity
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donner);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        n=findViewById(R.id.nom);
        p=findViewById(R.id.prenom);
        g=findViewById(R.id.gmail);
        te=findViewById(R.id.teli);
        sh=findViewById(R.id.show);
        new get_data().execute(id);
        sh.setOnClickListener(V->{
            show2();
        });
        //showAlertDialog(id);
    }
    public  class get_data extends AsyncTask<String, Void, String> {
        AlertDialog.Builder builder = new AlertDialog.Builder(donner.this);
        AlertDialog alertDialog;
        protected void onPreExecute() {
            builder.setTitle("wait...");
            builder.setMessage("recupuration de information en cour.");
            builder.setCancelable(false);
            alertDialog = builder.create();
            alertDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String idus=params[0];
            String url = "https://finable-increase.000webhostapp.com/stage/get_info.php";
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                alertDialog.dismiss();
                                String nm = jsonObject.getString("nom");
                                if(nm.equals("not")){
                                    showAlertDialog("Erreur produit");
                                }
                                else {
                                    String pr = jsonObject.getString("prenom");
                                    String t = jsonObject.getString("teli");
                                    String gm = jsonObject.getString("gmail");
                                    n.setText(nm);p.setText(pr);te.setText(t);g.setText(gm);
                                }
                            } catch (JSONException e) {
                                alertDialog.dismiss();
                                e.printStackTrace();
                                showAlertDialog("Probléme Accé a la base donné");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error
                            alertDialog.dismiss();
                            showAlertDialog("aucun connection internet repeté");
                            //Toast.makeText(MainActivity.this, "Failed check connection", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", idus);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(donner.this);
            queue.add(request);
            return null;
        }
    }
    private void showAlertDialog(String mes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(donner.this);
        builder.setMessage(mes)
                .setPositiveButton("OK", (dialog, which) -> {
                    // Do something when the OK button is clicked
                    Toast.makeText(donner.this, "OK button clicked", Toast.LENGTH_SHORT).show();
                })
                .setCancelable(false)
                .show();
    }
    private void show2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(donner.this);
        builder.setTitle("Information Personnel");
        builder.setMessage("Enter votre information:");
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.modifier, null);
        builder.setView(dialogView);
        nm= dialogView.findViewById(R.id.nm);
        pr= dialogView.findViewById(R.id.pr);
        gm= dialogView.findViewById(R.id.gm);
        tl= dialogView.findViewById(R.id.pa);
        // Get references to the EditText fields in the dialog
        nm.setText(n.getText().toString());pr.setText(p.getText().toString());
        gm.setText(g.getText().toString());tl.setText(te.getText().toString());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                   new update().execute(nm.getText().toString(),pr.getText().toString(),
                            tl.getText().toString(),gm.getText().toString(),id);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public class update extends AsyncTask<String, Void, String> {
        AlertDialog.Builder builder = new AlertDialog.Builder(donner.this);
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
            String name=params[0];
            String last=params[1];
            String phone=params[2];
            String email=params[3];
            String i=params[4];
            String url = "https://abdelkhalk.000webhostapp.com/stage/modifier_compte.php";
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                String mes=jsonObject.getString("message");
                                if(mes.equals("done")){
                                    new get_data().execute(id);
                                    alertDialog.dismiss();
                                }else{
                                    Toast.makeText(donner.this, "Erreur,repeté", Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error
                            alertDialog.dismiss();
                            showAlertDialog("chek connection internet");
                            //Toast.makeText(MainActivity.this, "Failed check connection", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nom", name);
                    params.put("prenom", last);
                    params.put("gmail", email);
                    params.put("tele", phone);
                    params.put("id", i);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(donner.this);
            queue.add(request);
            return null;
        }

    }
}
