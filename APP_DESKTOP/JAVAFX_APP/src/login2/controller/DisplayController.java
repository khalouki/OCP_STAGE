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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class DisplayController implements Initializable {
  
     @FXML
     public TextArea message;

    public void setMessage(String mes) {
        message.setText(mes);
    }
     
    @FXML
    void sortie(ActionEvent event) {
          Stage stg=(Stage)message.getScene().getWindow();
          stg.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
