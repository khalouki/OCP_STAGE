/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login2.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
public class Description_eqpController implements Initializable {
    @FXML
    public TextArea area_des;
    String id;
    @FXML
    void demande() throws IOException {
          Stage stg=(Stage)area_des.getScene().getWindow();
          stg.close();
          Stage stg2 =new Stage();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/demande.fxml"));
          Parent root;
          root = loader.load();
          DemandeController cnt = loader.getController();
          cnt.id=id;
          Scene scene = new Scene(root);
          stg2.setScene(scene);
          stg2.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
