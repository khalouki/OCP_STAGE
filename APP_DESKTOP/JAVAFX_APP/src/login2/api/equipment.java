
package login2.api;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
public class equipment {
      public String insert(String n,String f,String z){
             String res;
             try {
            URL url = new URL("https://finable-increase.000webhostapp.com/stage/ajouter_eqp.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String parameters = "nom=" + n+ "&fre="+f+"&zone="+z ;
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
            res = mes.getString("ff");
            
          } catch (IOException e) {
             res="erreur";
        }
          return res;
      }  
      public String suprimer(String id){
            String res;
             try {
            URL url = new URL("https://abdelkhalk.000webhostapp.com/stage/suprimer_eqp.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String parameters = "id=" +id  ;
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
            res = mes.getString("message");
            
          } catch (IOException e) {
             res="erreur";
        }
          return res;
      } 
      public String modifier(String n,String f,String z,String id){
          String res;
             try {
            URL url = new URL("https://abdelkhalk.000webhostapp.com/stage/modifier_eq.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String parameters = "nom=" + n+ "&fre="+f+"&zone="+z+"&id="+id ;
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
            res = mes.getString("ff");
            
          } catch (IOException e) {
             res="erreur";
        }
          return res;
      }
 }
  
