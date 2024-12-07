/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login2.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import login2.api.envoi_demande;

public class DemandeController implements Initializable {
    String id;
    @FXML
    private TextArea text;
    @FXML
    void send_demande() {
          Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
waitAlert.setTitle("Veuillez patienter envoi en cour.........");
waitAlert.setHeaderText(null);
waitAlert.show();

Task<Void> sendDataTask = new Task<Void>() {
    @Override
    protected Void call() throws Exception {
        envoi_demande ab = new envoi_demande();
        ab.data(text.getText(), id);
        return null;
    }
};

sendDataTask.setOnSucceeded(event -> {
    waitAlert.close();

    Stage stg = (Stage) text.getScene().getWindow();
    Alert wai = new Alert(Alert.AlertType.CONFIRMATION);
    wai.setTitle("Demande envoyée avec succès");
    wai.setHeaderText(null);
    wai.showAndWait();
    stg.close();
});

sendDataTask.setOnFailed(event -> {
    waitAlert.close();

    // Handle the failure case, e.g., show an error message
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    errorAlert.setTitle("Erreur lors de l'envoi de la demande");
    errorAlert.setHeaderText(null);
    errorAlert.setContentText("Une erreur s'est produite lors de l'envoi de la demande. Veuillez réessayer.");
    errorAlert.showAndWait();
});

Thread sendThread = new Thread(sendDataTask);
sendThread.start();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
