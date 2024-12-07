package login2.api;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
public class insert {
      public String insert(String n,String p,String g,String pa){
          String res;
             try {
            URL url = new URL("https://finable-increase.000webhostapp.com/stage/creation_compte_fx.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String parameters = "nom=" + n+ "&prenom="+p+ "&gmail="+g+"&pass="+pa ;
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
              JSONObject mes= new JSONObject(response.toString());
              res = mes.getString("compte_fx");
            
          } catch (IOException e) {
               res="erreur";
        }
          return res;
      }
}