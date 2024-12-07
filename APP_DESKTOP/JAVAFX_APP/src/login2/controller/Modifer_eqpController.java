/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login2.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import login2.api.equipment;
public class Modifer_eqpController implements Initializable {
    String id;
    @FXML
    private TextField frequence;
    @FXML
    private TextField nom;
    @FXML
    private TextField zone;
    @FXML
    void save() {
           Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
           waitAlert.setTitle("Veuillez patienter");
           waitAlert.setHeaderText(null);
           waitAlert.setContentText("Veuillez patienter pendant modification...");
           waitAlert.show();
           equipment ab = new equipment();
           String n=nom.getText(),z=zone.getText(),f=frequence.getText();
           String mm= ab.modifier(n, f, z, id);
           waitAlert.close();
            if(mm.equals("valider")){
             Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("modification fini.");
            successAlert.showAndWait();
            }
            else{
            Alert successAlert = new Alert(Alert.AlertType.ERROR);
            successAlert.setTitle("404");
            successAlert.setHeaderText(null);
            successAlert.setContentText("verifier votre connection");
            successAlert.showAndWait(); 
            }
           Stage stg=(Stage)nom.getScene().getWindow();
           stg.close();
    }
     public void get(String n,String e,String f,String x){
           frequence.setText(f);
           nom.setText(n);
           zone.setText(e);
           id=x;
     };
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
