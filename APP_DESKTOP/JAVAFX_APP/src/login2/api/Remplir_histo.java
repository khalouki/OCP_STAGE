
package login2.api;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class Remplir_histo {
      String nom,zone,dated,des,id;
      public Remplir_histo(){};
    public Remplir_histo(String nn,String zz,String dd,String ii){
        this.dated=dd;
        this.id=ii;
        this.zone=zz;
        this.nom=nn;
    }

    public String getNom() {
        return nom;
    }
    public String getZone() {
        return zone;
    }
    public String getDated() {
        return dated;
    }
    public String getDes() {
        return des;
    }
    public String getId() {
        return id;
    }
       public JSONArray getdata(){
          JSONArray jsonArray = null;
           try {
            URL url = new URL("https://finable-increase.000webhostapp.com/stage/get_histo.php");
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
       public JSONObject getd(String id,String d){
          JSONObject jsonArray = null;
           try {
            URL url = new URL("https://abdelkhalk.000webhostapp.com/stage/get_statu.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String parameters = "id=" + id+"&date="+d;
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
             jsonArray = new JSONObject(response.toString()); 
          } catch (IOException e) {
            System.out.println(" non Success");
        } 
          return jsonArray ;
  
       }
 }