
package login2.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
public class description {
    public JSONObject data(String id){
          JSONObject json = null;
           try {
            URL url = new URL("https://finable-increase.000webhostapp.com/stage/get_des.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String parameters = "id=" + id;
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
             json = new JSONObject(response.toString()); 
          } catch (IOException e) {
            System.out.println(" non Success");
        } 
          return json ;
          
      }
}
