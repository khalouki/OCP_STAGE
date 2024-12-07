package login2.controller;
import  java.io.IOException;
import  java.net.URL;
import  java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import  javafx.animation.FadeTransition;
import  javafx.fxml.*;
import  javafx.scene.Parent;
import  javafx.scene.Scene;
import  javafx.scene.control.*;
import javafx.scene.image.ImageView;
import  javafx.scene.layout.Pane;
import  javafx.stage.Stage;
import javafx.stage.StageStyle;
import  javafx.util.Duration;
import  org.json.JSONObject;
import  login2.api.connection;
import  login2.api.insert;
import login2.controller.*;
public class LoginController implements Initializable {  
    @FXML
    private Pane con;
    @FXML
    private Pane log;
    @FXML
    private TextField gm;
    @FXML
    private TextField gmail;
    @FXML
    private Pane logo;
    @FXML
    private TextField nom;
    @FXML
    private TextField pass;
    @FXML
    private TextField prenom;
    @FXML
    private PasswordField password;
    @FXML
    void connection()  {
          try{
            Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
            waitAlert.setTitle("Veuillez patienter");
            waitAlert.setHeaderText(null);
            waitAlert.setContentText("Veuillez patienter pendant la création du compte...");
            waitAlert.show();
            connection ab = new connection();
            JSONObject res=ab.check(gmail.getText().toString(), password.getText().toString());
            waitAlert.close();
            if(res.getString("nom").equals("failed")){
            Alert successAlert = new Alert(Alert.AlertType.ERROR);
            successAlert.setTitle("404");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Mot de pass ou gmail incorrect");
            successAlert.showAndWait();
          }
            else{
              try {
               Stage stage = (Stage)logo.getScene().getWindow();
               Stage cc=new Stage();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/Dashboard.fxml"));
               Parent root;
                root = loader.load();
               DashboardController cnt = loader.getController();
               String tt= res.getString("nom")+" "+res.getString("prenom");
               cnt.setUsername(tt);
               Scene scene = new Scene(root);
               scene.setFill(null);
               cc.setScene(scene);
               cc.initStyle(StageStyle.TRANSPARENT);
               cc.show();
               stage.close();
                }catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          }catch(Exception E){
            Alert waitAlert = new Alert(Alert.AlertType.ERROR);
            waitAlert.setTitle("Erreur");
            waitAlert.setHeaderText(null);
            waitAlert.setContentText("Verifier la connection Internet");
            waitAlert.showAndWait();
          }
    }
    @FXML
    void save_in_database() {
          try{
            String n,p,g,pa;
            Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
            waitAlert.setTitle("Veuillez patienter");
            waitAlert.setHeaderText(null);
            waitAlert.setContentText("Veuillez patienter pendant la création du compte...");
            waitAlert.show();
            n=nom.getText();p=prenom.getText();g=gm.getText();pa=pass.getText();
            insert ab =new insert();
            String mm=ab.insert(n, p, g, pa);
            waitAlert.close();
            if(mm.equals("valider")){
             Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
            successAlert.setTitle("Compte créé");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Le compte a été créé avec succès.");
            successAlert.showAndWait();
            }
            else{
            Alert successAlert = new Alert(Alert.AlertType.ERROR);
            successAlert.setTitle("404");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Erreur de base donné");
            successAlert.showAndWait(); 
            }
          }catch(Exception E){
              Alert waitAlert = new Alert(Alert.AlertType.ERROR);
            waitAlert.setTitle("Erreur");
            waitAlert.setHeaderText(null);
            waitAlert.setContentText("la connection tré faible");
            waitAlert.showAndWait();
          }
    }
    @FXML     
    void retour() {
           log.setStyle("visibility: hidden;");
           con.setStyle("visibility: visible;");
           FadeTransition fade = new FadeTransition(Duration.seconds(1), con);
           fade.setFromValue(0.0);
           fade.setToValue(1.0);
           fade.setAutoReverse(true);
           fade.play();
    }
    @FXML
    void sign() {
           con.setStyle("visibility: hidden;");
           log.setStyle("visibility: visible;");
           FadeTransition fade = new FadeTransition(Duration.seconds(1), log);
           fade.setFromValue(0.0);
           fade.setToValue(1.0);
           fade.setAutoReverse(true);
           fade.play();
    } 
    @FXML 
    private Pane root;
    @FXML
    void close() {
         Stage stage = (Stage) root.getScene().getWindow();
         stage.close();
    }
    @FXML
    void minimise() {
         Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);
    }
      private double xOffset;
    private double yOffset;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
          root.setOnMousePressed(event -> {
            Stage primaryStage = (Stage) root.getScene().getWindow();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }
    
    
}   
     
    

