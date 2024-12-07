package login2.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import login2.api.equipment;
import login2.api.remplir_table;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
public class DashboardController implements Initializable {
     @FXML
     private Pane root;
    @FXML
    private Label username;
    public void setUsername(String nn) {
        username.setText(nn);
    }
    @FXML
    private Pane table_visite;
    @FXML
    private Pane planification;
    @FXML
    private TextField fre;
    @FXML
    private TextField nom;
    @FXML
    private TextField zone;
    @FXML
    private TableColumn< remplir_table, String> freq;
    @FXML
    private TableColumn<remplir_table, String> id;
    @FXML
    private TableColumn< remplir_table, String> name_eqp;
    @FXML
    private TableColumn< remplir_table, Void> qrcode;
    @FXML
    private TableView< remplir_table> table_eqp;
    @FXML
    private TableColumn< remplir_table, String> zone_eqp;
    @FXML
    private TableColumn<remplir_table,Void> editer;
    @FXML
    private Pane container;
    @FXML
    private Pane equip;
    @FXML
    void rm() {
        table_eqp.getItems().clear();
    }
    @FXML
    void logout() throws IOException {
           Stage stage = (Stage)username.getScene().getWindow();
           Stage cc=new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("layout/Login.fxml"));
           Scene scene = new Scene(root); 
           cc.setScene(scene);
           cc.initStyle(StageStyle.TRANSPARENT);
           cc.show();
           stage.close();
    }
    @FXML       
    void insert_equipment() throws IOException {
          Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
            waitAlert.setTitle("Veuillez patienter");
            waitAlert.setHeaderText(null);
            waitAlert.setContentText("Veuillez patienter pendant la création du compte...");
            waitAlert.show();
           Stage stg =new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("layout/ajouter_eqp.fxml"));
           Scene scene = new Scene(root);
           stg.setScene(scene);
           stg.show();
           waitAlert.close();
    }
    @FXML
    void remplir(){
             Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
           waitAlert.setTitle("Veuillez patienter");
           waitAlert.setHeaderText(null);
           waitAlert.setContentText("Veuillez patienter pendant modification...");
           waitAlert.show();
            container.getChildren().removeAll();
            container.getChildren().setAll(equip); 
            table_eqp.getItems().clear();
              try {
                  remplir_table bb = new remplir_table();
                  JSONArray Array=bb.getdata();
                 for (int i = 0; i < Array.length(); i++) {
                     JSONObject jsonObject = Array.getJSONObject(i);
                      String id = jsonObject.getString("id");
                      String name = jsonObject.getString("nom");
                      String fr = jsonObject.getString("fre");
                      String zo = jsonObject.getString("zone");
                      remplir_table ab= new remplir_table(name,fr,zo,id);
                      //remplir_table ab= new remplir_table("essaid","34","OUEDZEM","2");
                      table_eqp.getItems().add(ab);
                      }
                  } catch (Exception e) {
                   e.printStackTrace();
           }
           waitAlert.close();
      }  
    @FXML
    void plan_layout() {
            Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
            waitAlert.setTitle("Veuillez patienter");
            waitAlert.setHeaderText(null);
            waitAlert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/Planification.fxml"));
            Pane myPane;
        try {
            myPane = loader.load();
            container.getChildren().removeAll();
            container.getChildren().setAll(myPane);
        } catch (IOException ex) {
             Alert waitAler = new Alert(Alert.AlertType.ERROR);
            waitAler.setTitle("Erreur");
            waitAler.setHeaderText(null);
            waitAler.setContentText("Verifier la connection Internet");
            waitAler.showAndWait();
        } 
        waitAlert.close();
    }
     @FXML
    void table_visite() {
            Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
            waitAlert.setTitle("Veuillez patienter");
            waitAlert.setHeaderText(null);
            waitAlert.show();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/visite.fxml"));
            Pane myPane;
        try {
            myPane = loader.load();
            container.getChildren().removeAll();
            container.getChildren().setAll(myPane);
        } catch (IOException ex) {
            Alert waitAler = new Alert(Alert.AlertType.ERROR);
            waitAler.setTitle("Erreur");
            waitAler.setHeaderText(null);
            waitAler.setContentText("Verifier la connection Internet");
            waitAler.showAndWait();
        }
        waitAlert.close();
    }
     @FXML
    void histo_main() {
        Alert waitAlert = new Alert(Alert.AlertType.INFORMATION);
            waitAlert.setTitle("Veuillez patienter");
            waitAlert.setHeaderText(null);
            waitAlert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/historique_main.fxml"));
            Pane myPane;
        try {
            myPane = loader.load();
            container.getChildren().removeAll();
            container.getChildren().setAll(myPane);
        } catch (IOException ex) {
             Alert waitAler = new Alert(Alert.AlertType.ERROR);
            waitAler.setTitle("Erreur");
            waitAler.setHeaderText(null);
            waitAler.setContentText("Verifier la connection Internet");
            waitAler.showAndWait();
        }
        waitAlert.close();
    }
    private double xOffset;
    private double yOffset;
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
    @FXML
    void fresh() {
            remplir();
    }

   @FXML
    private TableColumn< remplir_table, Void> update;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        freq.setCellValueFactory(new PropertyValueFactory< remplir_table, String>("frequence"));
        name_eqp.setCellValueFactory(new PropertyValueFactory< remplir_table, String>("nom"));
        zone_eqp.setCellValueFactory(new PropertyValueFactory< remplir_table, String>("zone"));
        id.setCellValueFactory(new PropertyValueFactory<remplir_table, String>("id"));
        qrcode.setCellFactory((TableColumn<remplir_table, Void> column) -> {
                final TableCell<remplir_table, Void> cell = new TableCell< remplir_table, Void>() {
                    private final Button btn = new Button("Afficher");
            
                {
                     btn.setStyle("-fx-background-color:linear-gradient(to right,#247a4d,#2F4F4F,#243748);-fx-text-fill:white;-fx-pref-width:94;-fx-font-weight: bold;");
                     btn.setOnAction(event -> {
                       Stage stg =new Stage();
                       remplir_table item = getTableView().getItems().get(getIndex());
                       String qr= item.getId();
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/qrcode.fxml"));
                         try {
                             Parent root = loader.load();
                             qrcode qrc =loader.getController();
                             qrc.setLien(qr);
                             qrc.setimage();
                             Scene scene = new Scene(root); 
                             stg.setScene(scene); 
                             stg.show();
                         } catch (IOException ex) {
                             
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
        editer.setCellFactory((TableColumn<remplir_table, Void> column) -> {
                final TableCell<remplir_table, Void> cell = new TableCell< remplir_table, Void>() {
                    private final Button btn1 = new Button("Supprimer");
                    
                   
                {
                     btn1.setStyle("-fx-background-color:linear-gradient(to right,#247a4d,#2F4F4F,#243748);-fx-text-fill:white;-fx-pref-width:94;-fx-font-weight: bold;");
                     btn1.setOnAction(event -> {
                       remplir_table item = getTableView().getItems().get(getIndex());
                       String i= item.getId();
                       equipment requ=new equipment();
                       requ.suprimer(i);
                       remplir();
                      });
                }        
                 @Override
                 protected void updateItem(Void item, boolean empty) {
                     super.updateItem(item, empty);
                     if (empty) {
                         setGraphic(null);
                     } else {
                         setGraphic(btn1);
                         
                     }
                 }
             };
             return cell;
          });
       update.setCellFactory((TableColumn<remplir_table, Void> column) -> {
                final TableCell<remplir_table, Void> cell = new TableCell< remplir_table, Void>() {
                    private final Button btn2 = new Button("modifier");
                {
                     btn2.setStyle("-fx-background-color:linear-gradient(to right,#247a4d,#2F4F4F,#243748);-fx-text-fill:white;-fx-pref-width:94;-fx-font-weight: bold;");
                       btn2.setOnAction(event -> {
                       remplir_table item = getTableView().getItems().get(getIndex());
                     
                       Stage stg =new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/modifer_eqp.fxml"));
                         try {
                             Parent root = loader.load();
                             Modifer_eqpController up =loader.getController();
                             up.get(item.getNom(), item.getZone(), item.getFrequence(),item.getId());
                             Scene scene = new Scene(root); 
                             stg.setScene(scene); 
                             stg.show();
                         } catch (IOException ex) {
                             ex.printStackTrace();
                         }
                       
                      });
                }        
                 @Override
                 protected void updateItem(Void item, boolean empty) {
                     super.updateItem(item, empty);
                     if (empty) {
                         setGraphic(null);
                     } else {
                         setGraphic(btn2);
                         
                     }
                 }
             };
             return cell;
          });
        remplir();
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
