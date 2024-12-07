package com.example.finale2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class planing extends AppCompatActivity {
    LinearLayout ab;
    LinearLayout con;

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Call the default behavior to return to the previous layout
        finish(); // Close this activity
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planing);
        ab = findViewById(R.id.cnt);
        con = ab.findViewById(R.id.lay);
        new searche().execute();
       }

    public class searche extends AsyncTask<Void, Void, String> {
       JSONArray res;
        AlertDialog.Builder builder = new AlertDialog.Builder(planing.this);
        AlertDialog alertDialog;
        @Override
        protected void onPreExecute() {
            builder.setTitle("recupuration...");
            builder.setMessage("att quelque second....");
            builder.setCancelable(false);
            alertDialog = builder.create();
            alertDialog.show();
        }
        @Override
        protected String doInBackground(Void... voids) {
            String url = "https://finable-increase.000webhostapp.com/stage/plan.php";
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                res = new JSONArray(response);
                                display(res);
                                alertDialog.dismiss();
                            }  catch (JSONException e) {
                                e.printStackTrace();
                                alertDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error
                            Toast.makeText(planing.this, "Failed check connection", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }) {

            };
            RequestQueue queue = Volley.newRequestQueue(planing.this);
            queue.add(request);
            return null;
        }
        protected void onPostExecute(String result) {

        }
    }
    public void display(JSONArray res){
        LayoutInflater inflater = LayoutInflater.from(planing.this);
        // Create the view to add multiple times
        View view = inflater.inflate(R.layout.plan, null);
        // Add the view to the LinearLayout multiple times
        for (int i = 0;i<res.length(); i++) {
            JSONObject ab= null;
            try {
                ab = res.getJSONObject(i);
                View newView = new View(planing.this);
                newView = inflater.inflate(R.layout.plan, null);
                TextView mes= newView.findViewById(R.id.name);
                TextView nb =newView.findViewById(R.id.number);
                TextView zz =newView.findViewById(R.id.zone);
                nb.setText(String.valueOf(i+1));
                mes.setText(ab.getString("nom"));
                zz.setText(ab.getString("zone"));
                con.addView(newView);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}