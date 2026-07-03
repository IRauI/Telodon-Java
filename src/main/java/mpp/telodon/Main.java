package mpp.telodon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mpp.telodon.controller.LoginController;
import mpp.telodon.repository.*;
import mpp.telodon.service.VoluntarService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static javafx.application.Application.launch;

public class Main extends Application{
    public void start(Stage stage) throws IOException {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("bd.config")) {
            props.load(in);
        }

        VoluntarJdbcRepository voluntarRepo = new VoluntarJdbcRepository(props);
        VoluntarService voluntarService = new VoluntarService(voluntarRepo);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/controller/login-view.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setService(voluntarService);

        stage.setTitle("Telodon - Login");
        stage.setScene(new Scene(root));
        stage.show();
    }
    static void main(String[] args) {
        launch(args);
    }
}
