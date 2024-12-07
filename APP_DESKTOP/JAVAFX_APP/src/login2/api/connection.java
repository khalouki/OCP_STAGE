package login2.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class connection {
    public JSONObject check(String gmail,String pass){
            JSONObject mes = null;
            try {
            URL url = new URL("https://finable-increase.000webhostapp.com/stage/connection_app_desktop.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String parameters = "gmail="+gmail+"&pass="+pass ;
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
             mes= new JSONObject(response.toString());
          } catch (IOException e) {
             
        }
        return mes;
     }
}