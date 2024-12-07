package com.example.finale2;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    public Button btn;
    public EditText gm;
    public TextView sign;
    TextInputEditText pas;
    private boolean touchEventsEnabled = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        btn =findViewById(R.id.click);
        gm=findViewById(R.id.gmail);
        sign=findViewById(R.id.sign);
        pas=findViewById(R.id.pass);
        sign.setOnClickListener(V->{
            Intent ab = new Intent(this,sign.class);
            startActivity(ab);
        });
        btn.setOnClickListener(V->{
            new log().execute(gm.getText().toString(),pas.getText().toString());
       });

    }

    public class log extends AsyncTask<String, Void, String> {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog alertDialog;

        protected void onPreExecute() {
            builder.setTitle("Connection...");
            builder.setMessage("verification les information.");
            builder.setCancelable(false);
            alertDialog = builder.create();
            alertDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String user = params[0];
            String pass = params[1];
            String url = "https://finable-increase.000webhostapp.com/stage/log_app.php";
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String mes = jsonObject.getString("message");
                                if (mes.equals("exist")) {
                                    alertDialog.dismiss();

                                    String id=jsonObject.getString("id");
                                    Intent intent = new Intent(MainActivity.this, menu.class);

                                    intent.putExtra("id",id);
                                    startActivity(intent);
                                    finish();
                                } if(mes.equals("erreur")){
                                    alertDialog.dismiss();
                                    showAlertDialog("Username or password incorrect");
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
                            showAlertDialog("aucun connection internet");
                            //Toast.makeText(MainActivity.this, "Failed check connection", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", user);
                    params.put("pass",pass);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(request);
            return null;
        }
    }
    private void showAlertDialog(String mes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mes)
                .setPositiveButton("OK", (dialog, which) -> {
                    // Do something when the OK button is clicked
                    Toast.makeText(MainActivity.this, "OK button clicked", Toast.LENGTH_SHORT).show();
                })
                .setCancelable(false)
                .show();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!touchEventsEnabled) {
            return true; // Consume touch events if they are disabled
        }
        return super.dispatchTouchEvent(ev);
    }

    // Method to disable touch events
    private void disableTouchEvents() {
        touchEventsEnabled = false;
    }

    // Method to enable touch events
    private void enableTouchEvents() {
        touchEventsEnabled = true;
    }

    // Example usage to enable/disable touch events
    private void someMethod() {
        // Disable touch events
        disableTouchEvents();

        // Enable touch events
        enableTouchEvents();
    }
}