package login2.controller;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import login2.api.remplir_plan;
import org.json.JSONArray;
import org.json.JSONObject;
public class PlanificationController implements Initializable {
    @FXML
    private TableColumn<remplir_plan, String> date_pre;
    @FXML
    private TableColumn<remplir_plan,  String> fre;
    @FXML
    private TableColumn<remplir_plan,  String> nom;
    @FXML
    private TableView<remplir_plan> plane_visite;
    @FXML
    private TableColumn<remplir_plan,  String> zone;
    @FXML
    private DatePicker dat;
    @FXML
   public void add() {
                 plane_visite.getItems().clear();
                 remplir_plan bb = new   remplir_plan();
                 JSONArray Array=bb.data();
                 if(Array.length()<0){
                     System.out.print("vide");
                 }else{
                      for (int i = 0; i < Array.length(); i++) {
                      JSONObject jsonObject = Array.getJSONObject(i);
                      String nm = jsonObject.getString("nom");
                      String free = jsonObject.getString("fre");
                      String zon = jsonObject.getString("zone");
                      String der = jsonObject.getString("dernier");
                      remplir_plan ab= new remplir_plan(nm,zon,free,der);
                      //remplir_table ab= new remplir_table("essaid","34","OUEDZEM","2");
                      plane_visite.getItems().add(ab);
                      }
                 } 
    }
   @FXML
    void sersh() {
             Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
            waitAlert.setTitle("Veuillez patienter");
            waitAlert.setHeaderText(null);
            waitAlert.setContentText("cherche en cour...");
            waitAlert.show();
               LocalDate selectedDate = dat.getValue();
               String form = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
               plane_visite.getItems().clear();
               remplir_plan bb = new   remplir_plan();
                JSONArray Array=bb.data_d(form);
                 for (int i = 0; i < Array.length(); i++) {
                      JSONObject jsonObject = Array.getJSONObject(i);
                      String nom = jsonObject.getString("nom");
                      String fre = jsonObject.getString("fre");
                      String zone = jsonObject.getString("zone");
                      String der = jsonObject.getString("dernier");
                      remplir_plan ab= new remplir_plan(nom,fre,zone,der);
                      //remplir_plan ab= new remplir_plan("essaid","34","OUEDZEM","2");
                      plane_visite.getItems().add(ab);
                      }
                 waitAlert.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setCellValueFactory(new PropertyValueFactory<remplir_plan, String>("nm"));
        fre.setCellValueFactory(new PropertyValueFactory<remplir_plan, String>("fr"));
        zone.setCellValueFactory(new PropertyValueFactory<remplir_plan, String>("zon"));
        date_pre.setCellValueFactory(new PropertyValueFactory<remplir_plan, String>("da"));
        add();
    }    
    
}
