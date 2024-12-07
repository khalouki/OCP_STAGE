package login2.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import login2.api.description;
import login2.api.remplir_visite;
import org.json.JSONArray;
import org.json.JSONObject;

public class VisiteController implements Initializable {
    @FXML
    private TableColumn<remplir_visite, String> date_v;
    @FXML
    private TableColumn<remplir_visite, Void> desc;
    @FXML
    private TableColumn<remplir_visite, String>emplacement;
    @FXML
    private TableColumn<remplir_visite, String> nom;
    @FXML
    private TableColumn<remplir_visite, String> id;
    @FXML
    private TableView<remplir_visite> table_des;

   
    @FXML
    void remplire() {
                  
                   table_des.getItems().clear();
              try {
                   remplir_visite bb = new remplir_visite();
                  JSONArray Array=bb.data();
                 for (int i = 0; i < Array.length(); i++) {
                      JSONObject jsonObject = Array.getJSONObject(i);
                      String idd = jsonObject.getString("id");
                      String name = jsonObject.getString("nom");
                      String dat = jsonObject.getString("date");
                      String zo = jsonObject.getString("zone");
                      remplir_visite ab= new remplir_visite(name,zo,dat,idd);
                      //remplir_visite ab= new remplir_visite("essaid","34","OUEDZEM","2");
                      table_des.getItems().add(ab);
                      }
                  } catch (Exception e) {
                   e.printStackTrace();
           }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date_v.setCellValueFactory(new PropertyValueFactory<remplir_visite, String>("date"));
        nom.setCellValueFactory(new PropertyValueFactory<remplir_visite, String>("nm"));
        emplacement.setCellValueFactory(new PropertyValueFactory<remplir_visite, String>("zone"));
        id.setCellValueFactory(new PropertyValueFactory<remplir_visite, String>("id"));
        
        desc.setCellFactory((TableColumn<remplir_visite, Void> column) -> {
                final TableCell<remplir_visite, Void> cell = new TableCell<remplir_visite, Void>() {
                    private final Button btn = new Button("Afficher");
            
                {
                     btn.setStyle("-fx-background-color:linear-gradient(to right,#247a4d,#2F4F4F,#243748);-fx-text-fill:white;-fx-pref-width:94;-fx-font-weight: bold;");
                     btn.setOnAction(event -> {
                        remplir_visite cc=getTableView().getItems().get(getIndex());
                        JSONObject re = new description().data(cc.getId());
                        String ii=cc.getId();
                          try {
                       Stage stage=new Stage();
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/description_eqp.fxml"));
                       Parent root;
                       root = loader.load();
                       Description_eqpController cnt = loader.getController();
                       cnt.area_des.setText(re.getString("des"));
                       cnt.id=ii;
                       Scene scene = new Scene(root); 
                      stage.setScene(scene);
                      stage.show();
                     }catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
              }
                 @Override
                 protected void updateItem(Void item, boolean empty) {
                     super.updateItem(item, empty);
                     if (empty) {
                         setGraphic(null);
                     } else {
                         setGraphic(btn);
                     }
                 }
             };
             return cell;
          });
        remplire();
    }    
    
}
