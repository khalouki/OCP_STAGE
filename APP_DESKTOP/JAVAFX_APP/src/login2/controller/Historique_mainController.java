/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import login2.api.Remplir_histo;
import login2.api.remplir_table;
import org.json.JSONArray;
import org.json.JSONObject;

public class Historique_mainController implements Initializable {
    @FXML
    private TableColumn<Remplir_histo, String> date_d;
    @FXML
    private TableColumn<Remplir_histo, Void> des;

    @FXML
    private TableColumn<Remplir_histo, String>id;
 
    @FXML
    private TableColumn<Remplir_histo, String> nom;

    @FXML
    private TableView<Remplir_histo> table_histo;
 
    @FXML
    private TableColumn<Remplir_histo, String> zone;
    public void remplir(){
          try {
                 Remplir_histo res =new Remplir_histo();
                 JSONArray Array=res.getdata();
                 for (int i = 0; i < Array.length(); i++) {
                     JSONObject jsonObject = Array.getJSONObject(i);
                      String id = jsonObject.getString("id");
                      String name = jsonObject.getString("nom");
                      String zone = jsonObject.getString("zone");
                      String date = jsonObject.getString("date");
                      Remplir_histo ab= new Remplir_histo(name,zone,date,id);
                      //remplir_table ab= new remplir_table("essaid","34","OUEDZEM","2");
                      table_histo.getItems().add(ab);
                      }
                  } catch (Exception e) {
                   e.printStackTrace();
           }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setCellValueFactory(new PropertyValueFactory< Remplir_histo, String>("nom"));
        id.setCellValueFactory(new PropertyValueFactory< Remplir_histo, String>("id"));
        zone.setCellValueFactory(new PropertyValueFactory<Remplir_histo, String>("zone"));
        date_d.setCellValueFactory(new PropertyValueFactory<Remplir_histo, String>("dated"));
        des.setCellFactory((TableColumn< Remplir_histo, Void> column) -> {
                final TableCell<Remplir_histo, Void> cell = new TableCell<Remplir_histo, Void>() {
                    private final Button btn = new Button("Afficher");
            
                {
                     btn.setStyle("-fx-background-color:linear-gradient(to right,#247a4d,#2F4F4F,#243748);-fx-text-fill:white;-fx-pref-width:94;-fx-font-weight: bold;");
                     btn.setOnAction(event -> {
                     Remplir_histo item = getTableView().getItems().get(getIndex());
                     String ii= item.getId();
                     String da=item.getDated();
                     Stage cc=new Stage();
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/display.fxml"));
                    Parent root;
                         try {
                             root = loader.load();
                             DisplayController cnt = loader.getController();
                             Remplir_histo gg= new Remplir_histo();
                             JSONObject re = gg.getd(ii,da);
                             cnt.setMessage(re.getString("statu"));
                             Scene scene = new Scene(root); 
                             cc.setScene(scene);
                             cc.show();
                         } catch (IOException ex) {
                             Logger.getLogger(Historique_mainController.class.getName()).log(Level.SEVERE, null, ex);
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
          remplir();
    }    
  
}
