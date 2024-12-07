package login2.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;

public class remplir_plan {
      String nm,zon,fr,da;
      public remplir_plan(){};
      public remplir_plan(String nom,String zz,String ff,String dd ){
          this.nm=nom;
          this.da=dd;
          this.zon=zz;
          this.fr=ff;
      }

    public String getNm() {
        return nm;
    }

    public String getZon() {
        return zon;
    }

    public String getFr() {
        return fr;
    }

    public String getDa() {
        return da;
    }

   public JSONArray data(){
          JSONArray jsonArray = null;
           try {
            URL url = new URL("https://finable-increase.000webhostapp.com/stage/planification_fx.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestMethod("POST");
            //String parameters = "salma=" + "Vv";
            //connection.setDoOutput(true);
            //DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            //outputStream.writeBytes(parameters);
            //outputStream.flush();
            //outputStream.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
             jsonArray = new JSONArray(response.toString()); 
          } catch (IOException e) {
            System.out.println(" non Success");
        } 
          return jsonArray ;
      }
    public JSONArray  data_d(String d){
            JSONArray jsonArray = null;
           try {
            URL url = new URL("https://finable-increase.000webhostapp.com/stage/plan_par_date_fx.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String parameters = "date=" + d;
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(parameters);
            outputStream.flush();
            outputStream.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
             jsonArray = new JSONArray(response.toString()); 
          } catch (IOException e) {
            System.out.println(" non Success");
        } 
          return jsonArray ;
    }
}
