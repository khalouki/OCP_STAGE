package login2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class Login2 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("controller/layout/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
         //URL url = getClass().getResource("controller/layout/description_eqp.fxml");
         //System.out.println("URL: " + url);
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
