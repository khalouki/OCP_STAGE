package com.example.finale2;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
public class sign extends AppCompatActivity{
    TextView log;
    Button btn;
    EditText n,p,t,g,pa;
    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Call the default behavior to return to the previous layout
        finish(); // Close this activity
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        log=findViewById(R.id.log);
        btn=findViewById(R.id.btnSignUp);
        n=findViewById(R.id.name);p=findViewById(R.id.last);g=findViewById(R.id.email);
         t=findViewById(R.id.phone);pa=findViewById(R.id.mot);
        log.setOnClickListener(V->{
            Intent ab = new Intent(this,MainActivity.class);
            startActivity(ab);
            finish();
        });
        btn.setOnClickListener(V->{
                new create().execute(n.getText().toString(),p.getText().toString(),
                        t.getText().toString(),g.getText().toString(),pa.getText().toString());
       });
    }
    public class create extends AsyncTask<String, Void, String> {
        AlertDialog.Builder builder = new AlertDialog.Builder(sign.this);
        AlertDialog alertDialog;
        protected void onPreExecute() {
            builder.setTitle("att...");
            builder.setMessage("votre compte en cour de creation.");
            builder.setCancelable(false);
            alertDialog = builder.create();
            alertDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String nom = params[0];
            String prenom = params[1];
            String teli = params[2];
            String gmail = params[3];
            String pass = params[4];
            String url = "https://finable-increase.000webhostapp.com/stage/compte_app.php";
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String mes = jsonObject.getString("mes");
                                if (mes.equals("done")) {
                                     fin();
                                }else if(mes.equals("vide")){
                                    alertDialog.dismiss();
                                    showAlertDialog("remplir tout les champs");
                                }else{
                                    alertDialog.dismiss();
                                    showAlertDialog("il y a un probléme de execution de requet sql");
                                }
                            } catch (JSONException e) {
                                alertDialog.dismiss();
                                e.printStackTrace();
                                showAlertDialog("Probléme Accé a la base donné ou Internet");
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
                    params.put("nom", nom);
                    params.put("pass",pass);params.put("gmail",gmail);params.put("teli",teli);
                    params.put("prenom",prenom);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(sign.this);
            queue.add(request);
            return null;
        }
        }
    public void fin(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Success");
            builder.setMessage("Your Account have been created.");
            builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Code to go back to previous activity or fragment
                    Intent ab = new Intent(sign.this, MainActivity.class);
                    startActivity(ab);
                    finish();
                }
            });
            builder.show();
        }
    private void showAlertDialog(String mes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(sign.this);
        builder.setMessage(mes)
                .setPositiveButton("OK", (dialog, which) -> {
                    // Do something when the OK button is clicked
                    Toast.makeText(sign.this, "OK button clicked", Toast.LENGTH_SHORT).show();
                })
                .setCancelable(false)
                .show();
    }
}
