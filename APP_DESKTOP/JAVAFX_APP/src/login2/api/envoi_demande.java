
package login2.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
public class envoi_demande {
        public JSONObject data(String txt,String id){
            JSONObject json = null;
           try {
            URL url = new URL("https://finable-increase.000webhostapp.com/stage/ajouter_demande.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String parameters = "id=" + id+"&txt="+txt;
            connection.setDoOutput(true);
          try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                    outputStream.writeBytes(parameters);
                    outputStream.flush();
                }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            json = new JSONObject(response.toString()); 
          } catch (IOException e) {
            System.out.println(" non Success");
        } 
 
          return json ;
          
      }
}
