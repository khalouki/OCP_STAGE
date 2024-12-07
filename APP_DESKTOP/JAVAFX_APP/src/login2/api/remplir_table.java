
package login2.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;

public class remplir_table {
     String nom,frequence,zone,id;
     public  remplir_table(){};
     public  remplir_table(String nn,String ff,String zz,String ii){
       this.frequence=ff;
       this.id=ii;
       this.nom=nn;
       this.zone=zz;
   }
   
    public String getNom() {
        return nom;
    }

    public String getFrequence() {
        return frequence;
    }

    public String getZone() {
        return zone;
    }

    public String getId() {
        return id;
    }
     public JSONArray getdata(){
          JSONArray jsonArray = null;
           try {
            URL url = new URL("https://finable-increase.000webhostapp.com/stage/get_data_eqp.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestMethod("POST");
            //String parameters = "salma=" + "Vv";
            connection.setDoOutput(true);
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
}
