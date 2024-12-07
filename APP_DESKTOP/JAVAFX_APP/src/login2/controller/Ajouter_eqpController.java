package login2.controller;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import login2.api.equipment;

public class Ajouter_eqpController implements Initializable {

    @FXML
    private TextField frequence;

    @FXML
    private TextField nom;

    @FXML
    private TextField zone;
    private Stage stage; // To hold the stage reference

    // Setter to inject the stage reference
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    // Méthode pour enregistrer les données
    @FXML
    void save() {
        String nomValue = nom.getText().trim();
        String zoneValue = zone.getText().trim();
        String frequenceValue = frequence.getText().trim();

        // Alerte d'attente pendant l'insertion
        Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
        waitAlert.setTitle("Veuillez patienter");
        waitAlert.setHeaderText(null);
        waitAlert.setContentText("Veuillez patienter pendant l'insertion...");
        waitAlert.show();

        // Vérification des champs
        if (nomValue.isEmpty() || zoneValue.isEmpty() || frequenceValue.isEmpty()) {
            // Fermeture de l'alerte d'attente si les champs sont vides
            waitAlert.close();

            // Alerte d'erreur pour les champs vides
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Champs manquants");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Veuillez remplir tous les champs avant de continuer.");
            errorAlert.showAndWait();
        } else {
            // Si les champs sont remplis, procéder à l'insertion
            equipment eqp = new equipment();
            String result = eqp.insert(nomValue, frequenceValue, zoneValue);

            // Fermeture de l'alerte d'attente
            waitAlert.close();

            // Vérification du résultat de l'insertion
            if ("valider".equals(result)) {
                // Alerte de succès
                Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
                successAlert.setTitle("Succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Insertion réussie !");
                successAlert.showAndWait();
            } else {
                // Alerte d'erreur si problème de connexion
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Erreur de connexion, veuillez vérifier votre réseau.");
                errorAlert.showAndWait();
            }

            // Fermeture de la fenêtre après traitement
            if (stage != null) {
                stage.close();  // Close the stage explicitly
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisation si nécessaire
    }
}
