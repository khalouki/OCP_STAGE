
package login2.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
public class remplir_visite {
      String nm,zone,date,id;
      public remplir_visite(){};
      public remplir_visite(String n,String z,String d,String i){
          this.date=d;
          this.id=i;
          this.nm=n;
          this.zone=z;
          
      }
    public String getNm() {
        return nm;
    }

    public String getZone() {
        return zone;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }
    public JSONArray data(){
          JSONArray jsonArray = null;
           try {
            URL url = new URL("https://finable-increase.000webhostapp.com/stage/visite_fx.php");
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
}
