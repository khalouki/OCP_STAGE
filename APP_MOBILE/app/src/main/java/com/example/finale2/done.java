package com.example.finale2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
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

public class done extends AppCompatActivity {
    TextView mes;
    String mes2;
    Button btn;
    String id;
    public void onBackPressed() {
        super.onBackPressed(); // Call the default behavior to return to the previous layout
        finish(); // Close this activity
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.done);
        mes = findViewById(R.id.dat);
        Intent intent = getIntent();
        mes2 = intent.getStringExtra("mes2");
        id = intent.getStringExtra("id");
        mes.setText(mes2);
        btn=findViewById(R.id.click);
        btn.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Description");
            builder.setMessage("donné votre description:");
            final EditText editText = new EditText(this);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(editText);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String text = editText.getText().toString();
                    new  description().execute(text,id);
                    //Toast.makeText(done.this, text, Toast.LENGTH_SHORT).show();
                }
            });
              // Add a "Cancel" button to the dialog
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
    public  class description extends AsyncTask<String, Void, String> {
        AlertDialog.Builder builder = new AlertDialog.Builder(done.this);
        AlertDialog alertDialog;

        protected void onPreExecute() {
            builder.setTitle("envoyer...");
            builder.setMessage("suavegarde de description en cour.");
            builder.setCancelable(false);
            alertDialog = builder.create();
            alertDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String des = params[0];
            String id=params[1];
            String url = "https://finable-increase.000webhostapp.com/stage/ajt_des.php";
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String mes = jsonObject.getString("message");
                                if (mes.equals("done")) {
                                    alertDialog.dismiss();
                                    showAlertDialog("Done");

                                }
                            } catch (JSONException e) {
                                alertDialog.dismiss();
                                e.printStackTrace();
                                showAlertDialog("Erreur dans la base donné");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error
                            alertDialog.dismiss();
                            showAlertDialog("aucun connection internet");
                            //Toast.makeText(MainActivity.this, "Failed check connection", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("des", des);
                    params.put("id", id);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(done.this);
            queue.add(request);
            return null;
        }
    }
    private void showAlertDialog(String mes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(done.this);
        builder.setMessage(mes)
                .setPositiveButton("OK", (dialog, which) -> {
                    // Do something when the OK button is clicked
                    Toast.makeText(done.this, "OK button clicked", Toast.LENGTH_SHORT).show();
                })
                .setCancelable(false)
                .show();
    }
}
